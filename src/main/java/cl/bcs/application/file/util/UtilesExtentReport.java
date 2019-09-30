package cl.bcs.application.file.util;


import com.relevantcodes.extentreports.LogStatus;

import cl.bcs.application.driver.factory.FactoryExtentReport;
import cl.bcs.application.factory.util.Session;
/**
 * 
 * @author Narveider
 *
 */
public class UtilesExtentReport {

	public static void captura(String descripcion) {
		String screenshotPath;
		try {
			screenshotPath = FactoryExtentReport.getScreenshot(Session.getConfigDriver().getWebDriver(),
					descripcion + "-" + DateUtil.fecha());
			Session.getConfigDriver().logger.log(LogStatus.PASS, descripcion,
					Session.getConfigDriver().logger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void capturaError(String descripcion) {
		String screenshotPath;
		try {
			screenshotPath = FactoryExtentReport.getScreenshot(Session.getConfigDriver().getWebDriver(),
					descripcion + "-" + DateUtil.fecha());
			Session.getConfigDriver().logger.log(LogStatus.ERROR, descripcion,
					Session.getConfigDriver().logger.addScreenCapture(screenshotPath));
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
