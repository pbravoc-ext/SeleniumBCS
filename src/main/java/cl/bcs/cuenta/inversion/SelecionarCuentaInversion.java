package cl.bcs.cuenta.inversion;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import cl.bcs.application.constantes.util.ConstantesCuentaInversion;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.SpotUtiles;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.plataforma.CerrarVentana;



public class SelecionarCuentaInversion extends SpotUtiles{
	private WebDriver webDriver = null;	
	
	public SelecionarCuentaInversion(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(webDriver, this);
	}

	private static final Logger LOGGER = Log4jFactory.getLogger(SelecionarCuentaInversion.class);
	
	public static boolean cuentaInversionCliente(SpotExcel datos){
		try {
			String grilla = null;
			
			//Datos Generales
			String cliente = datos.getRut()+" "+datos.getNombre()+" ("+datos.getPortafolio()+")";			
			Session.getConfigDriver().waitForLoad();
			
			//Ir a pestañana Certificar / Anular Movimientos
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TAB_CERT_ANULAR)).click();
			Session.getConfigDriver().waitForLoad();
			
			UtilesExtentReport.captura("Ingreso Cuenta Inversión Cliente - Pestañana Certificar - Anular Movimientos");

			//Buscar Cliente
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CLIENTE_INVERSION)).clear();
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_CLIENTE_INVERSION)).sendKeys(cliente+Keys.ENTER);
			Session.getConfigDriver().waitForLoad();
			
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_BOTON_BUSCAR)).click();
			Session.getConfigDriver().waitForLoad();

			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_TIPO_COMPROBANTE)).sendKeys(Keys.TAB);
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_FOLIO_COMPROBANTE)).sendKeys(ConstantesSpot.SUB_ZEROS+Session.getComprobante()+Keys.ENTER);
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Busqueda por Folio Comprobante "+ Session.getComprobante());
			Session.getConfigDriver().waitForLoad();
			//Validación Monto Cargo
			grilla = UtilesSelenium.findElement(By.xpath("//*[@id='frmCtaCliente_gridMovimientosCuentaInversion']/span/div[2]/div[4]/table/tbody/tr/td[7]/label")).getText();
			System.out.println(grilla);
			if(SpotUtiles.validacionValorGrilla(datos.getMontoSecundario(), grilla)) {
				//Validacion correcta 
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+ formatoBigDecimal(datos.getMontoSecundario())+ " Es Igual a " + formatoBigDecimal2(grilla));
			}else {
				//error
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+formatoBigDecimal(datos.getMontoSecundario())+ " Es Distinto a " +formatoBigDecimal2(grilla));
							}
			Session.getConfigDriver().waitForLoad();
			
			UtilesSelenium.findElement(By.xpath(ConstantesCuentaInversion.XPATH_ESTADO)).click();
			Session.getConfigDriver().waitForLoad();
			UtilesExtentReport.captura("Concepto- " + datos.getOperacion());
			Session.getConfigDriver().waitForLoad();
			// Validación Monto Abono
			grilla = UtilesSelenium.findElement(By.xpath("//*[@id='frmCtaCliente_gridMovimientosCuentaInversion']/span/div[2]/div[4]/table/tbody/tr[2]/td[6]/label")).getText();
			System.out.println(grilla);

			if(SpotUtiles.validacionValorGrilla2(datos.getMontoPrincipal(), grilla)) {
				//Validacion correcta 
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Igual a " + grilla);
			}else {
				//error
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validación de Monto Ingresado " , "Monto "+datos.getMontoPrincipal()+ " Es Distinto a " + grilla);
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
