package cl.bcs.application.file.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SessionBuilder;


/**
 * 
 * @author Narveider
 *
 */

public class UtilesSelenium {
	private static final Logger LOGGER = Log4jFactory
			.getLogger(UtilesSelenium.class);
	private static WebDriver driver;

	/**
	 * 
	 * @param listaElementos
	 * @return
	 */
	public static Map<String, WebElement> getListaFromElements(
			List<WebElement> listaElementos) {
		Map<String, WebElement> hashMap = new HashMap<>();
		if (listaElementos.isEmpty() || listaElementos != null) {
			for (int i = 0; i < listaElementos.size(); i++) {
				LOGGER.debug("Key : " + listaElementos.get(i).getText());
				hashMap.put(listaElementos.get(i).getText(),
						listaElementos.get(i));
			}
		}
		Session.getConfigDriver().waitForLoad();
		return hashMap;

	}

	/**
	 * 
	 * @param tag
	 * @param xpath
	 */
	public static void moveToElement(String tag, String xpath) {
		try {
			driver = Session.getConfigDriver().getWebDriver();
			WebElement webElement = driver.findElement(By.xpath("//" + tag
					+ "[text()='" + xpath + "']"));
			Coordinates coordinates = ((Locatable) webElement).getCoordinates();
			coordinates.inViewPort();
			Session.getConfigDriver().waitForLoad();
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
	}

	public static void scrollDownBytext(String tag, String xpath) {
		driver = Session.getConfigDriver().getWebDriver();
		WebElement element = driver.findElement(By.xpath("//" + tag
				+ "[text()='" + xpath + "']"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
		Session.getConfigDriver().waitForLoad();
	}

	/**
	 * 
	 * @param tag
	 * @param xpath
	 * @throws Exception
	 */
	public static void scrollDown(String tag, String xpath) {
		driver = Session.getConfigDriver().getWebDriver();
		WebElement element = driver.findElement(By.xpath("//" + tag
				+ "[@class='" + xpath + "']"));
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
		Session.getConfigDriver().waitForLoad();
	}

	/**
	 * 
	 * @return
	 */
	public static boolean assertResult() {
		// Error de prueba resultado final si la transacion tiene problemas
		driver = Session.getConfigDriver().getWebDriver();
		List<WebElement> textAlert = driver.findElements(By
				.className("text-alert"));
		LOGGER.debug("******* " + textAlert.toString() + " *******");
		if (!textAlert.isEmpty()) {
			if (textAlert.get(0).getText().contains("Lo sentimos")) {
				LOGGER.debug("Descripcion Error: " + textAlert.get(0).getText());
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param findElements
	 * @param key
	 * @return
	 */
	public static WebElement getWebElement(List<WebElement> findElements,
			String key) {
		WebElement elemento = null;
		Map<String, WebElement> elementos = getListaFromElements(findElements);
		if (elementos.containsKey(key)) {
			elemento = elementos.get(key);
		}
		return elemento;
	}

	/**
	 * 
	 * @return
	 */
	public static String getDateFormatt() {
		Date date = new Date();
		SimpleDateFormat parser = new SimpleDateFormat("yyyyMMdd");
		return parser.format(date);
	}

	public static void moveByElement(WebElement element) {
		try {
			Coordinates cordinates = ((Locatable) element).getCoordinates();
			cordinates.inViewPort();
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public static WebElement getElementoFromIonicItem(
			List<WebElement> listaIonic, String tagName, String key) {
		WebElement elementoEncontrado = null;
		for (WebElement elemento : listaIonic) {
			List<WebElement> divElementos = elemento.findElements(By
					.tagName(tagName));
			for (WebElement elementos : divElementos) {
				if (key.equals(elementos.getText())) {
					elementoEncontrado = elementos;
				}
			}
		}
		return elementoEncontrado;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	public static WebElement findElementById(By id) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		return driver.findElement(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static List<WebElement> findElementsById(By id) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		return driver.findElements(id);
	}

	/**
	 * 
	 * @param tag
	 * @return
	 */
	public static List<WebElement> findElementsByTag(By tag) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		return driver.findElements(tag);
	}

	/**
	 * 
	 * @param tag
	 * @return
	 */

	public static List<WebElement> getElementsByClassName(String tag) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		return driver.findElements(By.className(tag));
	}

	/**
	 * 
	 * @param findElements
	 * @param tag
	 * @return
	 */
	public static WebElement getWebElementByTag(List<WebElement> findElements,
			String tag) {
		WebElement elementoWeb = null;
		Session.getConfigDriver().waitForLoad();
		for (int i = 0; i < findElements.size(); i++) {
			WebElement elemento = findElements.get(i).findElement(
					By.className(tag));
			if (elemento.isDisplayed()) {
				elementoWeb = elemento;
			}
		}
		return elementoWeb;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	/**
	 * 
	 * @param className
	 * @return
	 */
	public static WebElement findElementByClassName(By className) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		return driver.findElement(className);
	}

	/**
	 * 
	 * @param elementoWeb
	 * @param value
	 */
	public static void typeInField(WebElement elementoWeb, String value) {
		String val = value;
		/**
		 * elementoWeb.clear();
		 */
		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
			elementoWeb.sendKeys(s);
		}
		Session.getConfigDriver().waitForLoad();
	}

	/**
	 * 
	 * @param by
	 * @return
	 */
	public static WebElement findElement(By by) {
		if (driver == null) {
			driver = Session.getConfigDriver().getWebDriver();
		}
		try {
			Session.getConfigDriver().waitForLoad(2000);
			return driver.findElement(by);
		} catch (NoSuchElementException no) {
			LOGGER.error(no.getMessage());
		}
		return null;
	}
	/**
	 * 
	 * @param elementBig
	 * @param xpathInput
	 * @return
	 */
	public static WebElement findInputNumber(String elementBig,String xpathInput) {
		UtilesSelenium.findElement(By.xpath(elementBig))
		.click();
		new WebDriverWait((WebDriver) Session.getConfigDriver().getWebDriver(), 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(xpathInput)));
		return UtilesSelenium.findElement(By.xpath(xpathInput));
	}
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement findInputText(String element) {
		new WebDriverWait((WebDriver) Session.getConfigDriver().getWebDriver(), 10).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(element)));
		return UtilesSelenium.findElement(By.xpath(element));
	}
	/**
	 * chrome
	 */
	public static void executeTest() {
		Session session = new SessionBuilder("chrome").build();
		session.getConfig().openPage();
	}

}