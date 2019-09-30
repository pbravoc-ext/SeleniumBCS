package cl.bcs.facturacion;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import cl.bcs.application.constantes.util.ConstantesFacturacion;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.SpotUtiles;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.plataforma.CerrarVentana;
import cl.bcs.plataforma.SeleccionarMenu;

public class Facturacion extends SpotUtiles{
	private static WebDriver webDriver = null;

	public Facturacion(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(webDriver, this);
	}

	private static final Logger LOGGER = Log4jFactory.getLogger(Facturacion.class);

	public static boolean gestionFacturacion(SpotExcel datos) {
		String cliente = datos.getRut() + " " + datos.getNombre() + " (" + datos.getPortafolio() + ")";

		// Datos Movimientos a Facturar
		Session.getConfigDriver().waitForLoad();
		UtilesExtentReport.captura("Ingreso Gestión de Facturación - Movimientos a Facturar");

		try {

			// Instanciar Datos
			String abono = null;
			String cargo = null;
			String comprobante = null;
			String comprobanteVenta = null;
			String movimientoEgreso = null;
			String movimientoIngreso = null;
			String abonoOp = null;
			String cargoOp = null;
			String comprobanteOp = null;
			String comprobanteVentaOp = null;
			String movimientoIngresoOp = null;
			String movimientoEgresoOp = null;
			String grilla = null;

			// Movimientos a Facturar
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_CLIENTEMOV)).sendKeys(cliente);
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_CLIENTEMOV)).sendKeys(Keys.ENTER);
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_BUSCARMOV)).click();
			Session.getConfigDriver().waitForLoad();

			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_FOLIOINPUT))
					.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getFolio(), Keys.ENTER);
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Buscar en Grilla Movimientos por Folio " + Session.getFolio());
			Session.getConfigDriver().waitForLoad();

			if (datos.getInstrumento().equals("ARBITRAJE INTERBANCARIO")) {

				if (datos.getCuentaInversion().equals("NO")) {
					UtilesSelenium.findElement(By.xpath(
							"//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.AccionTipoOperacion' and contains(text(),"
									+ ConstantesFacturacion.COMPRA + ")]"))
							.click();
					Session.getConfigDriver().waitForLoad();
					
					UtilesExtentReport.captura("Buscar en Grilla COMPRA MESA SPOT ");
					
					//Validación en Grilla Montos
					grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']//span[@bcs-bind='dataItem.Monto']")).getText();
					System.out.println(grilla);
					if(SpotUtiles.validacionValorGrilla(datos.getMontoPrincipal(), grilla)) {
						//Validacion correcta 
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
					}else {
						//error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
									}
					
					// Generar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_GENERARMOV)).click();
					Session.getConfigDriver().waitForLoad();

					UtilesExtentReport.captura("Desea facturar movimiento seleccionado");

					// Confirmar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARMOV)).click();
					Session.getConfigDriver().waitForLoad();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Confirmar Movimiento - Generación Comprobante");

					// Abono
					abono = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_ABONO_ARB)).getText();
					movimientoEgreso = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_EGRESO_ARB_SCI)).getText();
					comprobanteVenta = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_ARB_SCI)).getText();
					Session.getConfigDriver().waitForLoad();

					abonoOp = SpotUtiles.folio(abono);
					movimientoEgresoOp = SpotUtiles.folio(movimientoEgreso);
					comprobanteVentaOp = SpotUtiles.folio(comprobanteVenta);
					if (datos.getOperacion().toUpperCase().equals("COMPRA")) {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Cargo" , abonoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Compra" , comprobanteVentaOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Ingreso" , movimientoEgresoOp);
					}else {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Abono" , abonoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Venta" , comprobanteVentaOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Egreso" , movimientoEgresoOp);
					}

					LOGGER.info("Abono: " + abonoOp);
					LOGGER.info("Egreso: " + movimientoEgresoOp);
					LOGGER.info("Comprobante Venta: " + comprobanteVentaOp);

					// Aceptar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARINFO)).click();
					Session.getConfigDriver().waitForLoad();
					UtilesSelenium.findElement(By.xpath(
							"//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.AccionTipoOperacion' and contains(text(),"
									+ ConstantesFacturacion.VENTA + ")]"))
							.click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Buscar en Grilla VENTA MESA SPOT ");
					//Validación en Grilla Montos
					grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']//span[@bcs-bind='dataItem.Monto']")).getText();
					System.out.println(grilla);
					if(SpotUtiles.validacionValorGrilla(datos.getMontoPrincipal(), grilla)) {
						//Validacion correcta 
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
					}else {
						//error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
									}

					// Generar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_GENERARMOV)).click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("¿Desea facturar movimiento(s) seleccionado(s)?");

					// Confirmar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARMOV)).click();
					Session.getConfigDriver().waitForLoad();

					UtilesExtentReport.captura("Confirmar Movimiento - Generación Comprobante");

					// Cargo
					cargo = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_CARGO_ARB)).getText();
					movimientoIngreso = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_INGRESO_ARB_SCI)).getText();
					comprobante = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_ARB_SCI)).getText();
					Session.getConfigDriver().waitForLoad();

					cargoOp = SpotUtiles.folio(abono);
					movimientoIngresoOp = SpotUtiles.folio(movimientoIngreso);
					comprobanteOp = SpotUtiles.folio(comprobante);
					
					if (datos.getOperacion().toUpperCase().equals("COMPRA")) {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Abono" , cargoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Venta" , comprobanteOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Egreso" , movimientoIngresoOp);
					}else {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Cargo" , cargoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Compra" , comprobanteOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Ingreso" , movimientoIngresoOp);
					}
					LOGGER.info("Cargo: " + cargoOp);
					LOGGER.info("Ingreso: " + movimientoIngresoOp);
					LOGGER.info("Comprobante Compra: " + comprobanteOp);

					// Aceptar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARINFO)).click();
					Session.getConfigDriver().waitForLoad();

				} else {     
					// con cuenta inversion  y con arbitraje
					UtilesSelenium.findElement(By.xpath(
							"//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.AccionTipoOperacion' and contains(text(),"
									+ ConstantesFacturacion.COMPRA + ")]"))
							.click();
					Session.getConfigDriver().waitForLoad();
					
					UtilesExtentReport.captura("Buscar en Grilla COMPRA MESA SPOT ");
					grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']//span[@bcs-bind='dataItem.Monto']")).getText();
					System.out.println(grilla);
					
					if(SpotUtiles.validacionValorGrilla(Session.getMontoSecundario(), grilla)) {
						//Validacion correcta 
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
					}else {
						//error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
									}

					// Generar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_GENERARMOV)).click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Desea facturar movimiento seleccionado");

					// Confirmar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARMOV)).click();
					Session.getConfigDriver().waitForLoad();

					UtilesExtentReport.captura("Confirmar Movimiento - Generación Comprobante");

					// Abono
					abono = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_ABONO_ARB)).getText();
					comprobanteVenta = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_ARB)).getText();
					Session.getConfigDriver().waitForLoad();

					abonoOp = SpotUtiles.folio(abono);
					comprobanteVentaOp = SpotUtiles.folio(comprobanteVenta);
					
					if (datos.getOperacion().toUpperCase().equals("COMPRA")) {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Abono" , abonoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Compra" , comprobanteVentaOp);
						//Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Ingreso" , movimientoEgresoOp);
					}else {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Cargo" , abonoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Venta" , comprobanteVentaOp);
						//Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Egreso" , movimientoEgresoOp);
					}
					LOGGER.info("Abono: " + abonoOp);
					LOGGER.info("Comprobante Venta: " + comprobanteVentaOp);

					// Aceptar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARINFO)).click();
					Session.getConfigDriver().waitForLoad();

					UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.AccionTipoOperacion' and contains(text(),"
									+ ConstantesFacturacion.VENTA + ")]"))
							.click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Buscar en Grilla VENTA MESA SPOT ");
					grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']//span[@bcs-bind='dataItem.Monto']")).getText();
					System.out.println(grilla);
					if(SpotUtiles.validacionValorGrilla(datos.getMontoPrincipal(), grilla)) {
						//Validacion correcta 
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
					}else {
						//error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
									}
					
					// Generar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_GENERARMOV)).click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("¿Desea facturar movimiento(s) seleccionado(s)?");

					// Confirmar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARMOV)).click();
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Confirmar Movimiento - Generación Comprobante");

					// Cargo
					cargo = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_CARGO_ARB)).getText();
					comprobante = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_ARB)).getText();
					Session.getConfigDriver().waitForLoad();

					cargoOp = SpotUtiles.folio(cargo);
					comprobanteOp = SpotUtiles.folio(comprobante);
					
					
					if (datos.getOperacion().toUpperCase().equals("COMPRA")) {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Cargo" , cargoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Venta" , comprobanteOp);
						//Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Egreso" , movimientoIngresoOp);
					}else {
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Abono" , cargoOp);
						Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante Compra" , comprobanteOp);
						//Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Ingreso" , movimientoIngresoOp);
					}
					LOGGER.info("Cargo: " + cargoOp);
					LOGGER.info("Comprobante Compra: " + comprobanteOp);

					// Aceptar
					UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARINFO)).click();
					Session.getConfigDriver().waitForLoad();

				}

				Session.setAbono(abonoOp);
				Session.setCargo(cargoOp);
				Session.setComprobante(comprobanteOp);
				Session.setComprobanteVenta(comprobanteVentaOp);
				Session.setMovimientoEgreso(movimientoEgresoOp);
				Session.setMovimientoIngreso(movimientoIngresoOp);
				Session.getConfigDriver().waitForLoad();

			} else {
				//Sin Arbitraje
				UtilesSelenium.findElement(By.xpath(
						"//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.FolioTransaccion' and contains(text(),"
								+ Session.getFolio() + ")]"))
						.click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Buscar en Grilla folio - " + Session.getFolio());
				grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-movimientos-facturar']//span[@bcs-bind='dataItem.Monto']")).getText();
				System.out.println(grilla);
				
				LOGGER.info("Monto Ingresado: " + datos.getMontoSecundario());
				LOGGER.info("Monto Grilla " + grilla);
				
				if(SpotUtiles.validacionValorGrilla(datos.getMontoSecundario(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+ formatoBigDecimal(datos.getMontoSecundario())+ " Es Igual a " + formatoBigDecimal2(grilla));
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+formatoBigDecimal(datos.getMontoSecundario())+ " Es Distinto a " +formatoBigDecimal2(grilla));
								}

				// Generar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_GENERARMOV)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Desea facturar movimiento seleccionado");

				// Confirmar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARMOV)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Confirmar Movimiento - Generación Comprobante");
				
				// Rescatar Datos
				abono = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_ABONO)).getText();
				cargo = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_CARGO)).getText();
				comprobante = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_CCI))
						.getText();
				abonoOp = SpotUtiles.folio(abono);
				cargoOp = SpotUtiles.folio(cargo);
				comprobanteOp = SpotUtiles.folio(comprobante);
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Folio Abono" , abonoOp);
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Folio Cargo" , cargoOp);
				LOGGER.info("Abono: " + abonoOp);
				LOGGER.info("Cargo: " + cargoOp);
				if (datos.getCuentaInversion().equals("NO")) {
					movimientoIngreso = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_INGRESO))
							.getText();
					movimientoEgreso = UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_EGRESO))
							.getText();
					comprobante = UtilesSelenium
							.findElement(By.xpath(ConstantesFacturacion.XPATH_LABEL_COMPROBANTE_SCI)).getText();
					comprobanteOp = SpotUtiles.folio(comprobante);
					movimientoIngresoOp = SpotUtiles.folio(movimientoIngreso);
					movimientoEgresoOp = SpotUtiles.folio(movimientoEgreso);
					
					Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Ingreso" , movimientoIngresoOp);
					Session.getConfigDriver().logger.log(LogStatus.INFO, "Movimiento Egreso" , movimientoEgresoOp);
					LOGGER.info("Movimiento Ingreso: " + movimientoIngresoOp);
					LOGGER.info("Movimiento Egreso: " + movimientoEgresoOp);
				}
				
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Comprobante" , comprobanteOp);
				
				LOGGER.info("Comprobante: " + comprobanteOp);
				Session.setAbono(abonoOp);
				Session.setCargo(cargoOp);
				Session.setComprobante(comprobanteOp);
				Session.setMovimientoEgreso(movimientoEgresoOp);
				Session.setMovimientoIngreso(movimientoIngresoOp);
				Session.getConfigDriver().waitForLoad();

				// Aceptar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARINFO)).click();
				Session.getConfigDriver().waitForLoad();

			}

			// Comprobantes de Facturación
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_TABCOMPROBANTEFACTURACION)).click();
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Ingreso Comprobante Facturación");
			
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_CLIENTEFAC)).sendKeys(cliente, Keys.ENTER);
			Session.getConfigDriver().waitForLoad();


			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_BUSCARFAC)).click();
			Session.getConfigDriver().waitForLoad();
			if (datos.getInstrumento().equals("ARBITRAJE INTERBANCARIO")) {

				// buscar comprobante Venta
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_SECUENCIA))
						.sendKeys(ConstantesSpot.SUB_ZEROS + comprobanteOp + Keys.ENTER);
				Session.getConfigDriver().waitForLoad();

				
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_COMPARARFOLIOFAC + comprobanteOp
						+ ConstantesFacturacion.XPATH_COMPARARFOLIOFA2C)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Busqueda por comprobante" + comprobanteOp);
				
				grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-comprobantes-facturacion']//span[@bcs-bind='dataItem.Monto']")).getText();
				System.out.println(grilla);
				if(SpotUtiles.validacionValorGrilla(datos.getMontoPrincipal(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
								}

				// Enviar a DTE.
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ENVIARFAC)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Desea enviar comprobante seleccionado al DTE");

				// Confirmar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARFAC)).click();
				Session.getConfigDriver().waitForLoad(6000);
				UtilesExtentReport.captura("El comprobante " + comprobanteOp+ " fue enviado con éxito");
				// Aceptar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARFAC)).click();
				Session.getConfigDriver().waitForLoad();

				// buscar comprobante Compra
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_LIMPIAR_SEC)).click();
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_SECUENCIA))
						.sendKeys(ConstantesSpot.SUB_ZEROS + comprobanteVentaOp + Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				

				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_COMPARARFOLIOFAC + comprobanteVentaOp
						+ ConstantesFacturacion.XPATH_COMPARARFOLIOFA2C)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Busqueda por comprobante" + comprobanteOp);
				
				grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-comprobantes-facturacion']//span[@bcs-bind='dataItem.Monto']")).getText();
				System.out.println(grilla);
				if(SpotUtiles.validacionValorGrilla(datos.getMontoPrincipal(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
								}
				

				// Enviar a DTE.
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ENVIARFAC)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Desea enviar comprobante seleccionado al DTE");

				// Confirmar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARFAC)).click();
				Session.getConfigDriver().waitForLoad(6000);
				UtilesExtentReport.captura("El comprobante " + comprobanteOp+ " fue enviado con éxito");

				// Aceptar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARFAC)).click();
				Session.getConfigDriver().waitForLoad();

			} else {

				// buscar comprobante Venta
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_SECUENCIA))
						.sendKeys(ConstantesSpot.SUB_ZEROS + comprobanteOp + Keys.ENTER);
				Session.getConfigDriver().waitForLoad();

				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_COMPARARFOLIOFAC + comprobanteOp
						+ ConstantesFacturacion.XPATH_COMPARARFOLIOFA2C)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Busqueda por comprobante" + comprobanteOp);
				
				grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-comprobantes-facturacion']//span[@bcs-bind='dataItem.Monto']")).getText();
				System.out.println(grilla);
				if(SpotUtiles.validacionValorGrilla(Session.getMontoSecundario(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Distinto a " + grilla);
								}

				// Enviar a DTE.
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ENVIARFAC)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Desea enviar comprobante seleccionado al DTE");
				
				// Confirmar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_CONFIRMARFAC)).click();
				Session.getConfigDriver().waitForLoad(6000);
				UtilesExtentReport.captura("El comprobante " + comprobanteOp+ " fue enviado con éxito");

				// Aceptar
				UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ACEPTARFAC)).click();
				Session.getConfigDriver().waitForLoad();

			}

			Session.getConfigDriver().waitForLoad();
			LOGGER.info("Gestion de facturacion - Datos enviados a DTE");
			Session.getConfigDriver().logger.log(LogStatus.INFO, "Gestion de facturacion", "Datos enviados a DTE");
			UtilesExtentReport.captura("Gestion de facturacion - Datos enviados a DTE");
			CerrarVentana.init();
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilesExtentReport.capturaError("Error Gestion de Facturacion - Datos facturacion - Spot");
			Session.getConfigDriver().logger.log(LogStatus.ERROR,
					"Error: Gestion de Facturacion - Datos facturacion - Spot", "Datos: " + e.getMessage());
			UtilesSelenium.findElement(By.xpath(ConstantesFacturacion.XPATH_BTN_ERROR)).click();
			CerrarVentana.init();
			SeleccionarMenu.seleccionarMenuFacturacion();
			return false;
		}
	}

}
