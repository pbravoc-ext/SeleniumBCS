package cl.bcs.tesoreria;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.constantes.util.Constantes;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.constantes.util.ConstantesTesoreria;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.SpotUtiles;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.plataforma.CerrarVentana;
import cl.bcs.plataforma.SeleccionarMenu;

public class Tesoreria {
	private static WebDriver webDriver = null;

	public Tesoreria(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(webDriver, this);
	}

	private static final Logger LOGGER = Log4jFactory.getLogger(SeleccionarMenu.class);

	public static boolean gestionTesoreria(SpotExcel datos) {

		try {
			Session.getConfigDriver().waitForLoad();
			
			UtilesExtentReport.captura("Ingreso Gestión de Tesorería");
			
			
			
			String grilla = null;
			String socio = datos.getRut() + " " + datos.getNombre();
			String portafolio = "(" + datos.getPortafolio() + ") - PROPGENERALES";
			String Egreso = Session.getMovimientoEgreso();
			String Ingreso = Session.getMovimientoIngreso();
			String buscarEgreso = ConstantesTesoreria.XPATHERE_INGRESO_EGRESO + Egreso + Constantes.XPATHERE_OUT;
			String buscarIngreso = ConstantesTesoreria.XPATHERE_INGRESO_EGRESO + Ingreso + Constantes.XPATHERE_OUT;

			// Ingreso socio
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_SOCIO_NEGOCIO_INPUT)).clear();
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_SOCIO_NEGOCIO_INPUT)).sendKeys(socio);
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_SOCIO_NEGOCIO_INPUT)).sendKeys(Keys.ENTER);

			// Fecha desde
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_DESDE_INPUT)).clear();
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_DESDE_INPUT)).sendKeys(Session.getFechaDesde()); // Fecha
																											// local
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_DESDE_INPUT)).sendKeys(Keys.TAB);

			// Fecha hasta
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_HASTA_INPUT)).clear();
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_HASTA_INPUT)).sendKeys(Session.getFechaHasta()); // fecha local + 1 dia
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_FECHA_HASTA_INPUT)).sendKeys(Keys.TAB);

			// Ingreso portafolio
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_PORTAFOLIO_INTPUT)).clear();
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_PORTAFOLIO_INTPUT)).sendKeys(portafolio);
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_PORTAFOLIO_INTPUT)).sendKeys(Keys.TAB);

			// Limpiar estado
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_ESTADO_INTPUT)).clear();
			Session.getConfigDriver().waitForLoad();

			// Boton buscar
			UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_BTN_BUSCAR)).click();
			Session.getConfigDriver().waitForLoad();

			
			if (datos.getOperacion().equalsIgnoreCase(Constantes.COMPRA)) {
				
				// Busqueda grilla por secuencia Folio1 //Engreso
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_GRILLA_SECUENCIA_INPUT))
						.sendKeys(ConstantesSpot.SUB_ZEROS + Egreso, Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(buscarEgreso)).click();
				LOGGER.info("Seleccionado" + " " + Egreso);
				Session.getConfigDriver().waitForLoad();
				grilla = UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_EGRESO_GRILLA)).getText();
				LOGGER.info(grilla);
				if(SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Distinto a " + grilla);
					
								}
				UtilesExtentReport.captura("Egreso " + Egreso + " seleccionado");
				Session.getConfigDriver().waitForLoad();

				// Boton limpiar secuencia
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_BTN_SCN)).click();

				// Busqueda grilla por secuencia Folio2//Ingreso
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_GRILLA_SECUENCIA))
						.sendKeys(ConstantesSpot.SUB_ZEROS + Ingreso, Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(buscarIngreso)).click();
				LOGGER.info("Seleccionado" + " " + Ingreso);
				Session.getConfigDriver().waitForLoad();
				grilla = UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_EGRESO_GRILLA)).getText();
				LOGGER.info(grilla);
				if(SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+Session.getMontoPrincipal()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+Session.getMontoPrincipal()+ " Es Distinto a " + grilla);
					
								}
				
				UtilesExtentReport.captura("Ingreso " + Ingreso + " seleccionado");
				Session.getConfigDriver().waitForLoad();
				
				
				
			}else {
				// Busqueda grilla por secuencia Folio1 //Engreso
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_GRILLA_SECUENCIA_INPUT))
						.sendKeys(ConstantesSpot.SUB_ZEROS + Egreso, Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(buscarEgreso)).click();
				LOGGER.info("Seleccionado" + " " + Egreso);
				Session.getConfigDriver().waitForLoad();
				grilla = UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_EGRESO_GRILLA)).getText();
				LOGGER.info(grilla);
				if(SpotUtiles.validacionValorGrilla2(Session.getMontoPrincipal(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+Session.getMontoPrincipal()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+Session.getMontoPrincipal()+ " Es Distinto a " + grilla);
					
								}
				UtilesExtentReport.captura("Egreso " + Egreso + " seleccionado");
				Session.getConfigDriver().waitForLoad();

				// Boton limpiar secuencia
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_BTN_SCN)).click();

				// Busqueda grilla por secuencia Folio2//Ingreso
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_GRILLA_SECUENCIA))
						.sendKeys(ConstantesSpot.SUB_ZEROS + Ingreso, Keys.ENTER);
				Session.getConfigDriver().waitForLoad();
				UtilesSelenium.findElement(By.xpath(buscarIngreso)).click();
				LOGGER.info("Seleccionado" + " " + Ingreso);
				Session.getConfigDriver().waitForLoad();
				grilla = UtilesSelenium.findElement(By.xpath(ConstantesTesoreria.XPATH_INGRESO_EGRESO_GRILLA)).getText();
				LOGGER.info(grilla);
				if(SpotUtiles.validacionValorGrilla2(Session.getMontoSecundario(), grilla)) {
					//Validacion correcta 
					Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Igual a " + grilla);
				}else {
					//error
					Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+Session.getMontoSecundario()+ " Es Distinto a " + grilla);
					
								}
				
				UtilesExtentReport.captura("Ingreso " + Ingreso + " seleccionado");
				
			}
			
			
			Session.getConfigDriver().waitForLoad();
			CerrarVentana.init();

			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilesExtentReport.capturaError("Error: Gestion de Tesoreria - Datos tesoreria - Spot");
			Session.getConfigDriver().logger.log(LogStatus.ERROR,
					"Error: Gestion de Tesoreria - Datos tesoreria - Spot", "Datos: " + e.getMessage());
			CerrarVentana.init();
			return false;
		}
	}

}