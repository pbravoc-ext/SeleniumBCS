package cl.bcs.application.driver.factory;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.DateUtil;

public class FactoryExtentReport {
	
	protected void configuracionInicialER() {

		Session.getConfigDriver().extent = new ExtentReports(
				System.getProperty("user.dir") + "/test-output/Optimus_Spot-"+DateUtil.fecha()+".html", true);
		Session.getConfigDriver().extent.addSystemInfo("Encoding", "UTF-8");
		Session.getConfigDriver().extent.addSystemInfo("Host Name", "Automatizacion OPTIMUS")
				.addSystemInfo("Environment", "http://bolsa.optimuscb.cl:9045").addSystemInfo("User Name", "ROBOT");
		Session.getConfigDriver().extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
	}

	public static void configuracionFinalER(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			Session.getConfigDriver().logger.log(LogStatus.FAIL, "Caso fallado: " + result.getName());
			Session.getConfigDriver().logger.log(LogStatus.FAIL, "Caso fallado: " + result.getThrowable());
			String screenshotPath = getScreenshot(Session.getConfigDriver().getWebDriver(),
					result.getName());
			Session.getConfigDriver().logger.log(LogStatus.FAIL,
					Session.getConfigDriver().logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			Session.getConfigDriver().logger.log(LogStatus.SKIP, "Test saltado: " + result.getName());
		}
		Session.getConfigDriver().extent.endTest(Session.getConfigDriver().logger);
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/testsScreenshots/" + screenshotName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
