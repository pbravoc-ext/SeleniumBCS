package cl.bcs.spot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.constantes.util.ConstantesConfirmacionOperacionSpot;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.SpotUtiles;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.plataforma.CerrarVentana;
import cl.bcs.plataforma.SeleccionarMenu;

/**
 * 
 * @author rnunez_EXT
 *
 */

public class ConfirmacionOperacionesSpot {

	private static final Logger LOGGER = Log4jFactory.getLogger(ConfirmacionOperacionesSpot.class);

	public static boolean confirmarSpot(SpotExcel datos) {

		String cliente = datos.getRut() + " " + datos.getNombre() + " (" + datos.getPortafolio() + ")";
		


		try {
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Ingreso Confirmación Spot");
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_CLIENTE)).sendKeys(cliente);
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_CLIENTE))
					.sendKeys(Keys.ENTER);
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Ingreso Datos cliente " , cliente);
			Session.getConfigDriver().waitForLoad();
			// Buscar
			SeleccionarMenu.seleccionarConfirmacionOperaciones();

			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_FOLIO_INPUT))
					.sendKeys(ConstantesSpot.SUB_ZEROS+Session.getFolio() + Keys.ENTER);
			UtilesSelenium.findElement(By.xpath("//*[@id='grid-mov']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.FolioOperacion' and contains(text(),"+Session.getFolio()+")]")).click();

			UtilesExtentReport.captura("Buscar folio " +Session.getFolio() );
			
			//Validación en Grilla Montos
			String grilla = UtilesSelenium.findElement(By.xpath("//*[@id='grid-mov']/span/div[2]/div[4]/table/tbody/tr[1]/td[12]/label")).getText();
			if(SpotUtiles.validacionValorGrilla2(datos.getMontoPrincipal(), grilla)) {
				//Validacion correcta 
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
			}else {
				//error
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
							}

			// Confirmar
			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_BOTON_CONFIRMAR)).click();
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Se enviará a Facturación la Operación");

			// Aceptar
			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_BOTON_ACEPTAR)).click();
			Session.getConfigDriver().waitForLoad();
			
			UtilesExtentReport.captura("Operación Ejecutada");

			// Aceptar
			

			UtilesSelenium.findElement(By.xpath(ConstantesConfirmacionOperacionSpot.XPATH_BOTON_INFO_ACEPTAR)).click();
			Session.getConfigDriver().waitForLoad();
			Session.getConfigDriver().logger.log(LogStatus.INFO, "Datos confirmados, enviados a facturacion","");
			Session.getConfigDriver().waitForLoad();
			
			Session.getConfigDriver().logger.log(LogStatus.INFO, "Finalización confirmación operación spot","");
			
	
			LOGGER.info("Finalización confirmación operación spot");

			CerrarVentana.init();

			return true;

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			UtilesExtentReport.capturaError("Error: Confirmacion operacion spot - Datos operacion - Spot");
			Session.getConfigDriver().logger.log(LogStatus.ERROR, "Error Confirmacion operacion spot -Datos operacion",
					"Datos: " + e.getMessage());
			return false;
		}
	}
}
