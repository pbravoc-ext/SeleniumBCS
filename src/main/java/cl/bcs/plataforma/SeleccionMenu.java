package cl.bcs.plataforma;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import cl.bcs.application.constantes.util.ConstantesSelecionMenu;
import cl.bcs.application.constantes.util.ConstantesSpotTags;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesSelenium;

/**
 * 
 * @author dnarvaez_EXT
 *
 */
public class SeleccionMenu {

	private static final Logger LOGGER = Log4jFactory.getLogger(SeleccionMenu.class);

	private static final String operacionRueda = "//*[@id='navbar']/div/ul[3]/li";
	private static final String cuentaInversion = "//*[@id='navbar']/div/ul[5]/li";
	private static final String custodia = "//*[@id='navbar']/div/ul[6]/li";
	private static final String facturacion = "//*[@id='navbar']/div/ul[7]/li";
	private static final String tesoreria = "//*[@id='navbar']/div/ul[8]/li";
	private static final String spot = "//*[@id='navbar']/div/ul[14]/li";

	/**
	 * 
	 * @return
	 */
	public static boolean seleccionarMenuSpot() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(spot)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MENU_SPOT)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");				
				LOGGER.info("Cerrar Menú Spot");		
				LOGGER.info("===================================");					
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Spot");
				LOGGER.info("===================================");			
			}
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
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_MANTENEDOR_PUNTAS)).click();
			LOGGER.info("***************");
			LOGGER.info("* Selección Módulo mantenedor de puntas *");
			LOGGER.info("***************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarIngresoOperacionSpot() {
		try {
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_INGRESO_OPERACION_SPOT)).click();
			Session.getConfigDriver().waitForLoad();
			LOGGER.info("***************");
			LOGGER.info("* Selección Módulo Ingreso Operación Spot *");
			LOGGER.info("***************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
//	
//	public static boolean seleccionarMenuConfirmacionOperaciones() {
//		try {
//			Session.getConfigDriver().waitForLoad();
//			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_CONFIRMAR_OPERACION_SPOT)).click();
//			LOGGER.info("***************");
//			LOGGER.info("* Selección Módulo Confirmar Operacion Spot *");
//			LOGGER.info("***************");
//			return true;
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage() + " - Spot");
//		}
//		return false;
//	}
	
	public static boolean seleccionarModuloConfirmacionOperacionesSpot() {
		try {
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_FORM_CONFIRMAR_OPERACION_SPOT)).click();
			Session.getConfigDriver().waitForLoad();
			LOGGER.info("*****************");
			LOGGER.info("* Selección Módulo Confirmar Operaciones Spot *");
			LOGGER.info("*****************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuFacturacion() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(facturacion)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_MODULO_FACTURACION_SPOT)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");			
				LOGGER.info("Cerrar Menú Facturación");		
				LOGGER.info("===================================");					
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Facturación");
				LOGGER.info("===================================");			
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarFacturacion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_FORM_MODULO_FACTURACION_SPOT)).click();
			LOGGER.info("**************");
			LOGGER.info("* Selección Módulo Gestión Facturación *");
			LOGGER.info("**************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuTesoreria() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(tesoreria)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MENU_TESORERIA)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");			
				LOGGER.info("Cerrar Menú Tesorería");		
				LOGGER.info("===================================");					
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Tesorería");
				LOGGER.info("===================================");			
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarGestionTesoreria() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_GESTION_TESORERIA)).click();
			LOGGER.info("***************");
			LOGGER.info("* Selección Módulo Gestion de Tesoreria *");
			LOGGER.info("***************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuCuentaInversion() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(cuentaInversion)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_MENU_CUENTA_INVERSION)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");			
				LOGGER.info("Cerrar Menú Cuenta Inversión");	
				LOGGER.info("===================================");						
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Cuenta Inversión");
				LOGGER.info("===================================");			
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarCuentaInversionCliente() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_SPOT_FORM_CUENTA_INVERSION_CLIENTE)).click();
			LOGGER.info("***************");
			LOGGER.info("* Selección Módulo Cuenta Inversion Cliente *");
			LOGGER.info("***************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() + " - Spot");
		}
		return false;
	}
	
	public static boolean seleccionarMenuOperacionesRueda() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(operacionRueda)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MENU_OPERACIONESRUEDA)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");			
				LOGGER.info("Cerrar Menú Operaciones en Rueda");	
				LOGGER.info("===================================");						
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Operaciones en Rueda");
				LOGGER.info("===================================");			
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean seleccionarOR_IngresarOrden() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_OP_INGRESARORDEN)).click();
			LOGGER.info("*************");
			LOGGER.info("* Selección Módulo Ingresar orden *");
			LOGGER.info("*************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean seleccionarOR_IngresarTransaccion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_OP_INGRESARTRANSACCION)).click();
			LOGGER.info("***************");
			LOGGER.info("* Selección Módulo Ingresar transaccion *");
			LOGGER.info("***************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	public static boolean seleccionarOR_NegociacionOrdenes() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_OP_NEGOCIACIONORDENES)).click();
			LOGGER.info("**************");
			LOGGER.info("* Selección Módulo Negociacion Ordenes *");
			LOGGER.info("**************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	public static boolean seleccionarOR_InterfazAsignacion() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_OP_INTERFAZASIGNACION)).click();
			LOGGER.info("**************");
			LOGGER.info("* Selección Módulo Interfaz Asignacion *");
			LOGGER.info("**************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	public static boolean seleccionarOR_GestionOrdenes() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_OP_GESTIONORDENES)).click();
			LOGGER.info("************");
			LOGGER.info("* Selección Módulo Gestión Ordenes *");
			LOGGER.info("************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean seleccionarMenuCustodia() {
		try {
			Session.getConfigDriver().waitForLoad();
			String condicion = UtilesSelenium.findElement(By.xpath(custodia)).getAttribute(ConstantesSpotTags.TAG_CLASS);
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MENU_CUSTODIA)).click();
			if(condicion.equalsIgnoreCase("dropdown keep-open ng-scope open")) {
				LOGGER.info("===================================");			
				LOGGER.info("Cerrar Menú Custodia");		
				LOGGER.info("===================================");					
			}else {
				LOGGER.info("===================================");			
				LOGGER.info("Abrir Menú Custodia");
				LOGGER.info("===================================");			
			}
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean seleccionarModuloConsultarCustodia() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MODULO_CONSULTAR_CUSTODIA)).click();
			LOGGER.info("*************");
			LOGGER.info("* Selección Módulo Consultar Custodia *");
			LOGGER.info("*************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}
	
	public static boolean seleccionarModuloConsultarCarteras() {
		try {
			Session.getConfigDriver().waitForLoad();
			UtilesSelenium.findElement(By.id(ConstantesSelecionMenu.ID_MODULO_CONSULTAR_CARTERAS)).click();
			LOGGER.info("*************");
			LOGGER.info("* Selección Módulo Consultar Carteras *");
			LOGGER.info("*************");
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

}