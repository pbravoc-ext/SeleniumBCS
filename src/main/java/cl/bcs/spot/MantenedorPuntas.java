package cl.bcs.spot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.constantes.util.ConstantesMantenedorPuntas;
import cl.bcs.application.constantes.util.ConstantesSpotTags;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.application.spot.util.MantenedorUtil;
import cl.bcs.application.spot.util.VariablesUtil;
import cl.bcs.plataforma.CerrarVentana;

public class MantenedorPuntas extends MantenedorUtil {
	private static final Logger LOGGER = Log4jFactory.getLogger(MantenedorPuntas.class);

	public static boolean mantenedorPuntas(SpotExcel datos) {
		LOGGER.info("Mantenedor de puntas -  Spot");
		Session.getConfigDriver().waitForLoad(8000);
		VariablesUtil m = new VariablesUtil();
		try {
			WebElement input = UtilesSelenium
					.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_SELECCIONAR_MONEDA));
			switch (datos.getMonedaPrincipal()) {
			case "USD":
				input.clear();
				input.sendKeys(datos.getMonedaPrincipal());
				input.sendKeys(Keys.TAB);
				m.setCompra(
						UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_COMPRA_USD)).getText());
				m.setMonto(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_MONTO_USD)).getText());
				m.setPuntaCompra(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_PUNTA_COMPRA))
						.getAttribute(ConstantesSpotTags.TAG_TITLE));
				m.setPuntaVenta(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_PUNTA_VENTA))
						.getAttribute(ConstantesSpotTags.TAG_TITLE));
				m.setVenta(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_VENTA_USD)).getText());

				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Mantenedor Puntas " + datos.getMonedaPrincipal()+ " - Datos");
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Valor Compra ", m.getPuntaCompra());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Valor Venta ", m.getPuntaVenta());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Monto USD ", m.getMonto());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Compra USD ", m.getCompra());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Venta USD ", m.getVenta());
				Session.setVariables(m);
				CerrarVentana.init();
				return true;
			case "EUR":
				input.clear();
				input.sendKeys(datos.getMonedaPrincipal());
				input.sendKeys(Keys.TAB);
				m.setCompra(
						UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_COMPRA_EUR)).getText());
				m.setMonto(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_MONTO_EUR)).getText());
				m.setPuntaCompra(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_PUNTA_COMPRA))
						.getAttribute(ConstantesSpotTags.TAG_TITLE));
				m.setPuntaVenta(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_PUNTA_VENTA))
						.getAttribute(ConstantesSpotTags.TAG_TITLE));
				m.setVenta(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_VENTA_EUR)).getText());
				m.setMontoMil(
						UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_MONTO_EUR_MIL)).getText());
				m.setCompraMil(UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_COMPRA_EUR_MIL))
						.getText());
				m.setVentaMil(
						UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_VENTA_EUR_MIL)).getText());

				Session.setVariables(m);
				Session.getConfigDriver().waitForLoad();
				UtilesExtentReport.captura("Mantenedor Puntas "+ datos.getMonedaPrincipal()+" - Datos");

				Session.getConfigDriver().logger.log(LogStatus.INFO, "Valor Compra ", m.getPuntaCompra());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Valor Venta ", m.getPuntaVenta());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Monto EUR ", m.getMonto());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Compra EUR ", m.getCompra());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Venta EUR ", m.getVenta());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Monto EUR 1000 ", m.getMonto());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Compra EUR 1000 ", m.getCompra());
				Session.getConfigDriver().logger.log(LogStatus.INFO, "Venta EUR 1000 ", m.getVenta());

				CerrarVentana.init();
				return true;
				
			default:
				LOGGER.error("Moneda - Fuera de rango");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
			return false;
		}
	}

}
