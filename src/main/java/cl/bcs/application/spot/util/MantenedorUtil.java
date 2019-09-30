package cl.bcs.application.spot.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import cl.bcs.application.constantes.util.ConstantesMantenedorPuntas;
import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesSelenium;
import cl.bcs.spot.MantenedorPuntas;
/**
 * 
 * @author Narveider
 *
 */
public class MantenedorUtil {
	
	private static final Logger LOGGER = Log4jFactory.getLogger(MantenedorPuntas.class);

	/**
	 * 
	 * @return
	 */
	protected static String validarmoneda() {
		String moneda = "usd";
		if (moneda.equalsIgnoreCase(ConstantesSpot.MONEDA_EUR)) {
			return ConstantesSpot.MONEDA_EUR;
		}else if (moneda.equalsIgnoreCase(ConstantesSpot.MONEDA_USD)) {
			return ConstantesSpot.MONEDA_USD;
		}else {
			LOGGER.error("Error en la moneda seleccionada - Spot");
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	protected static boolean findEUR() {
		boolean result = false;
		try {
			WebElement input = UtilesSelenium.findElement(By.xpath(ConstantesMantenedorPuntas.XPATH_SELECCIONAR_MONEDA));
			input.clear();
			input.sendKeys(validarmoneda());
			input.sendKeys(Keys.ENTER);
			return result = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return result;
	}
}
