package cl.bcs.cuenta.inversion;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import cl.bcs.application.constantes.util.Constantes;
import cl.bcs.application.constantes.util.ConstantesCuentaInversion;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.SpotUtiles;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.plataforma.CerrarVentana;

public class CuentaInversion extends SpotUtiles {
	private WebDriver webDriver = null;

	public CuentaInversion(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(webDriver, this);
	}

	private static final Logger LOGGER = Log4jFactory.getLogger(CuentaInversion.class);

	public static boolean cuentaInversionCliente(SpotExcel datos) {
		try {
			String grilla = null;

			// Datos Generales
			String cliente = datos.getRut() + " " + datos.getNombre() + " (" + datos.getPortafolio() + ")";
			Session.getConfigDriver().waitForLoad();

			// Ir a pestañana Certificar / Anular Movimientos
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TAB_CERT_ANULAR)).click();
			Session.getConfigDriver().waitForLoad();

			UtilesExtentReport.captura("Ingreso Cuenta Inversión Cliente - Pestañana Certificar - Anular Movimientos");

			// Buscar Cliente
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CLIENTE_INVERSION)).clear();
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CLIENTE_INVERSION))
					.sendKeys(cliente + Keys.ENTER);
			Session.getConfigDriver().waitForLoad();

			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_BOTON_BUSCAR)).click();
			Session.getConfigDriver().waitForLoad();

			if (datos.getInstrumento().equalsIgnoreCase(Constantes.INSTRUMENTO_ARB_INTER)) {

				if (datos.getOperacion().equalsIgnoreCase(Constantes.COMPRA)) {

					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE))
							.sendKeys(Keys.TAB);
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE))
							.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getComprobante() + Keys.ENTER);
					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Busqueda por Folio Comprobante " + Session.getComprobante());
					Session.getConfigDriver().waitForLoad();

					// Validación Monto CARGO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CARGO_GRILLA_VENTA))
							.getText();
					LOGGER.info(grilla);
					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoPrincipal());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoPrincipal()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoPrincipal()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Cargo",
								"Monto " + Session.getMontoPrincipal() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Cargo",
								"Monto " + Session.getMontoPrincipal() + " Es Distinto a " + grilla);
					}

				} else {

					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE))
							.sendKeys(Keys.TAB);
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE))
							.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getComprobante() + Keys.ENTER);

					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Busqueda por Folio Comprobante " + Session.getComprobante());
					Session.getConfigDriver().waitForLoad();

					// Validación Monto Cargo
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CARGO_GRILLA_VENTA))
							.getText();
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoSecundario()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoSecundario()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado ",
								"Monto " + Session.getMontoSecundario() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado ",
								"Monto " + Session.getMontoSecundario() + " Es Distinto a " + grilla);
					}
				}

				Session.getConfigDriver().waitForLoad();

				UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ESTADO)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Concepto- " + datos.getOperacion());
				Session.getConfigDriver().waitForLoad();

				if (datos.getOperacion().equalsIgnoreCase(Constantes.COMPRA)) {

					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FIND_GRILLA))
							.sendKeys(Keys.LEFT_SHIFT, Keys.TAB);
					Session.getConfigDriver().waitForLoad();
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FIND_GRILLA))
							.sendKeys(Keys.LEFT_SHIFT, Keys.TAB);
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE))
							.sendKeys(Keys.TAB);
					Session.getConfigDriver().waitForLoad();
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_BTN_LIMPIAR_FOLIO)).click();
					Session.getConfigDriver().waitForLoad();
					// UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_FECHA)).click();
					Session.getConfigDriver().waitForLoad();
//					
//					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_FECHA)).sendKeys(Keys.TAB);
//					Session.getConfigDriver().waitForLoad();

					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE))
							.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getComprobanteVenta() + Keys.ENTER);
					Session.getConfigDriver().waitForLoad();

					LOGGER.info("=======================================");
					LOGGER.info(Session.getComprobanteVenta());

					LOGGER.info("=======================================");

					UtilesExtentReport.captura("Busqueda por Folio Comprobante " + Session.getComprobanteVenta());
					Session.getConfigDriver().waitForLoad();

					// Validación Monto ABONO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ABONO_GRILLA_COMPRA))
							.getText();
					LOGGER.info(grilla);

					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoSecundario());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoSecundario()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoSecundario()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Abono ",
								"Monto " + Session.getMontoSecundario() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoSecundario() + " Es Distinto a " + grilla);
					}

				} else {
					// UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_FECHA)).click();
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FIND_GRILLA))
							.sendKeys(Keys.LEFT_SHIFT, Keys.TAB);
					Session.getConfigDriver().waitForLoad();
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FIND_GRILLA))
							.sendKeys(Keys.LEFT_SHIFT, Keys.TAB);
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE))
							.sendKeys(Keys.TAB);
					Session.getConfigDriver().waitForLoad();
					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_BTN_LIMPIAR_FOLIO)).click();
					Session.getConfigDriver().waitForLoad();

					UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE))
							.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getComprobanteVenta() + Keys.ENTER);

					LOGGER.info("=======================================");
					LOGGER.info(Session.getComprobanteVenta());

					LOGGER.info("=======================================");

					Session.getConfigDriver().waitForLoad();
					UtilesExtentReport.captura("Busqueda por Folio Comprobante " + Session.getComprobanteVenta());
					Session.getConfigDriver().waitForLoad();

					// Validación Monto ABONO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ABONO_GRILLA_COMPRA))
							.getText();
					LOGGER.info(grilla);

					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoPrincipal());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoPrincipal()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoPrincipal()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoPrincipal() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoPrincipal() + " Es Distinto a " + grilla);
					}
				}

			} else {

				UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE))
						.sendKeys(Keys.TAB);
				UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE))
						.sendKeys(ConstantesSpot.SUB_ZEROS + Session.getComprobante() + Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Busqueda por Folio Comprobante " + Session.getComprobante());
				Session.getConfigDriver().waitForLoad();

				if (datos.getOperacion().equalsIgnoreCase(Constantes.COMPRA)) {
					// Validación Monto ABONO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ABONO_GRILLA_COMPRA))
							.getText();
					LOGGER.info(grilla);

					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoSecundario());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoSecundario()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoSecundario()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Abono ",
								"Monto " + Session.getMontoSecundario() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoSecundario() + " Es Distinto a " + grilla);
					}

				} else {

					// Validación Monto ABONO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ABONO_GRILLA_VENTA))
							.getText();
					LOGGER.info(grilla);

					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoPrincipal());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoPrincipal()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoPrincipal()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoPrincipal() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Abono",
								"Monto " + Session.getMontoPrincipal() + " Es Distinto a " + grilla);
					}
				}
				Session.getConfigDriver().waitForLoad();

				UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ESTADO)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Concepto- " + datos.getOperacion());
				Session.getConfigDriver().waitForLoad();


				if (datos.getOperacion().equalsIgnoreCase(Constantes.COMPRA)) {
					// Validación Monto CARGO
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CARGO_GRILLA_COMPRA))
							.getText();
					LOGGER.info(grilla);
					LOGGER.info("=======================================");
					LOGGER.info(Session.getMontoPrincipal());
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoPrincipal()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoPrincipal()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado Cargo",
								"Monto " + Session.getMontoPrincipal() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado Cargo",
								"Monto " + Session.getMontoPrincipal() + " Es Distinto a " + grilla);
					}

				} else {

					// Validación Monto Cargo
					grilla = UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CARGO_GRILLA_VENTA))
							.getText();
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(Session.getMontoSecundario()));
					LOGGER.info(SpotUtiles.formatoBigDecimal(Session.getMontoSecundario()));
					LOGGER.info("=======================================");
					LOGGER.info(grilla);
					LOGGER.info(SpotUtiles.formatoMontos(grilla));
					LOGGER.info(SpotUtiles.formatoBigDecimal(grilla));
					LOGGER.info("=======================================");

					if (SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
						// Validacion correcta
						Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado ",
								"Monto " + Session.getMontoSecundario() + " Es Igual a " + grilla);
					} else {
						// error
						Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado ",
								"Monto " + Session.getMontoSecundario() + " Es Distinto a " + grilla);
					}
				}
				Session.getConfigDriver().waitForLoad();

				UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ESTADO)).click();
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Concepto- " + datos.getOperacion());
			}
			Session.getConfigDriver().waitForLoad();
			LOGGER.info("CUENTA INVERSION COMPLETADA");
			CerrarVentana.init();
			return true;

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilesExtentReport.capturaError("Error: Gestion cuenta inversion - Spot");
			Session.getConfigDriver().logger.log(LogStatus.ERROR, "Error: Gestion cuenta inversion",
					"Datos: " + e.getMessage());
			return false;
		}
	}

}
