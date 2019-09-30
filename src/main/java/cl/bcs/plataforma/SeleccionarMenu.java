package cl.bcs.plataforma;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cl.bcs.application.constantes.util.ConstantesSpot;
import cl.bcs.application.constantes.util.ConstantesTesoreria;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesSelenium;

/**
 * 
 * @author dnarvaez_EXT
 *
 */
public class SeleccionarMenu {

	private static final Logger LOGGER = Log4jFactory.getLogger(SeleccionarMenu.class);

	/**
	 * 
	 * @return
	 */
	public static boolean init() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_MENU_SPOT)).click();
			LOGGER.info("Seleccion menu Spot - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	

	/**
	 * 
	 * @return
	 */
	
	public static boolean seleccionarMantenedorPuntas() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_MANTENEDOR_PUNTAS)).click();
			LOGGER.info("Seleccion mantenedor de puntas - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarIngresoOperacionSpot() {
		try {
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_INGRESO_OPERACION_SPOT)).click();
			Session.getConfigDriver().waitForLoad();
			LOGGER.info("Seleccion Ingreso Operación Spot - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuConfirmacionOperaciones() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_CONFIRMAR_OPERACION_SPOT)).click();
			LOGGER.info("Seleccion Menu Confirmar Operacion Spot - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarConfirmacionOperaciones() {
		try {
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_FORM_CONFIRMAR_OPERACION_SPOT)).click();
			Session.getConfigDriver().waitForLoad();
			LOGGER.info("Seleccion Confirmar Operacion Spot - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuFacturacion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_MODULO_FACTURACION_SPOT)).click();
			LOGGER.info("Seleccion Menu Facturación - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarFacturacion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_FORM_MODULO_FACTURACION_SPOT)).click();
			LOGGER.info("Seleccion Modulo Gestión Facturación - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuTesoreria() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesTesoreria.ID_MENU_TESORERIA)).click();
			LOGGER.info("Seleccion Menu Tesoreria - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarGestionTesoreria() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesTesoreria.ID_GESTION_TESORERIA)).click();
			LOGGER.info("Seleccion Modulo Gestion de Tesoreria - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuCuentaInversion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_MENU_CUENTA_INVERSION)).click();
			LOGGER.info("Seleccion Menu Cuenta Inversion - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarCuentaInversionCliente() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSpot.ID_SPOT_FORM_CUENTA_INVERSION_CLIENTE)).click();
			LOGGER.info("Seleccion Cuenta Inversion Cliente - Spot");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}

}
