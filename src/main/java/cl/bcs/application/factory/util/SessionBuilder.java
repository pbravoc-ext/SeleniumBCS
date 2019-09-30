package cl.bcs.application.factory.util;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * 
 * @author Narveider
 *
 */
public class SessionBuilder {
	protected String navegador;
	protected ExtentReports extent;
	protected ExtentTest logger;
	/**
	 * 
	 * @param browser
	 * @param env
	 * @param usuario
	 */
	public SessionBuilder(String browser) {
		navegador = browser;
	}

	/**
	 * 
	 * @return
	 */
	public Session build() {
		return new Session(this);
	}

}
