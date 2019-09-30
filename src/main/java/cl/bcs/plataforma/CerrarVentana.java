package cl.bcs.plataforma;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesSelenium;

public class CerrarVentana {
	private static final Logger LOGGER = Log4jFactory.getLogger(CerrarVentana.class);
	
	public static boolean init(){
		boolean isLogin = false;
		Session.getConfigDriver().waitForLoad();
		UtilesSelenium.findElement(By.xpath(ConstantesSpot.XPATH_CERRARVENTANA_SPOT)).click();
		LOGGER.info("Cerrar ventana");
		isLogin = true;
	return isLogin;
		
	}
}
