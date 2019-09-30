package cl.bcs.plataforma;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;
/**
 * 
 * @author dnarvaez_EXT
 *
 */
public class Login {
	private static final Logger LOGGER = Log4jFactory.getLogger(Login.class);

	public static boolean login() {
		Session.setVariacion(1);
		boolean isLogin = false;
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_USUARIO)).sendKeys("fmettroz");
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_PASSWORD)).sendKeys("1");
			UtilesExtentReport.captura("Ingreso de Usuario");
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_BTN_LOGIN)).click();
			UtilesExtentReport.captura("Login Exitoso");
			LOGGER.info("Login Exitoso");
			isLogin = true;
		return isLogin;
	}
}
