package cl.bcs.application.driver.factory;

import java.awt.AWTException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cl.bcs.application.factory.util.NavegadorConstantes;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.Resource;

/**
 * 
 * @author Narveider
 *
 */

public class FactoryWebDriver {

	private static final Logger LOGGER = Log4jFactory.getLogger(FactoryWebDriver.class);

	private WebDriver driver;
	private String navegador = "";
	private String urlToTest = "";
	private boolean instanceUrlInit = false;

	public ExtentReports extent = null;
	public ExtentTest logger = null;

	/**
	 * 
	 * @param navegador
	 * @param urlToTest
	 */
	public FactoryWebDriver(String navegador, String urlToTest) {
		this.navegador = navegador;
		this.urlToTest = Resource.getProperty("url.optimus");
		LOGGER.debug(" Cargando --> navegador:  " + navegador + ",  URL: " + urlToTest );

		if (driver == null) {
			try {
				createDriver();
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public WebDriver getWebDriver() {
		if (driver == null) {
			try {
				createDriver();
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}
		return driver;
	}

	/**
	 * @throws MalformedURLException
	 * 
	 */
	private void createDriver() throws MalformedURLException {
		if (this.navegador.equals(NavegadorConstantes.NAVEGADOR_CHROME)) {
			System.setProperty(NavegadorConstantes.DRIVER_CHROME, NavegadorConstantes.UBICACION_DRIVER_CHROME);
			driver = new ChromeDriver();
			try {
				driver.manage().window().maximize();
			} catch (Exception errorMax) {
				LOGGER.error(errorMax);
			}
		}
	}
	/**
	 * @throws AWTException
	 * 
	 */
	public void openPage() {
		if ((this.urlToTest != null || !this.urlToTest.isEmpty()) && instanceUrlInit == false) {
			LOGGER.debug("Redireccionando a URL --> " + this.urlToTest);
			driver.get(this.urlToTest);
			instanceUrlInit = true;
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void waitForLoad() {
		ExpectedCondition<Boolean> expectation = expectedCondition();
		try {
			Thread.sleep(Integer.parseInt(Resource.getProperty("select.config.time")));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Exception error) {
			LOGGER.error(error);
			if (error instanceof WebDriverException) {
				driver.switchTo().defaultContent();
				this.waitForLoad();
			}
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void waitForLoadMedio() {
		ExpectedCondition<Boolean> expectation = expectedCondition();
		try {
			Thread.sleep(Integer.parseInt(Resource.getProperty("select.config.time.mid")));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Exception error) {
			LOGGER.error(error);
			if (error instanceof WebDriverException) {
				driver.switchTo().defaultContent();
				this.waitForLoad();
			}
		}
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void waitForLoadLargo() {
		ExpectedCondition<Boolean> expectation = expectedCondition();
		try {
			Thread.sleep(Integer.parseInt(Resource.getProperty("select.config.time.long")));

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Exception error) {
			LOGGER.error(error);
			if (error instanceof WebDriverException) {
				driver.switchTo().defaultContent();
				this.waitForLoad();
			}
		}
	}

	public void waitForLoad(long time) {
		ExpectedCondition<Boolean> expectation = expectedCondition();
		try {
			Thread.sleep(time);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Exception error) {
			LOGGER.error(error);
			if (error instanceof WebDriverException) {
				driver.switchTo().defaultContent();
				this.waitForLoad();
			}
		}
	}

	private ExpectedCondition<Boolean> expectedCondition() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		return expectation;
	}

	/**
	 * 
	 */
	public void refresh() {
		if (driver != null) {
			driver.navigate().refresh();
			this.waitForLoad();
		}
	}
}
