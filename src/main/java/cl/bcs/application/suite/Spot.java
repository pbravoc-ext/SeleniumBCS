package cl.bcs.application.suite;



import org.apache.log4j.Logger;

import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.cuenta.inversion.CuentaInversion;
import cl.bcs.facturacion.Facturacion1;
import cl.bcs.plataforma.SeleccionMenu;
import cl.bcs.spot.ConfirmacionOperacionesSpot;
import cl.bcs.spot.IngresoOperacionSpot;
import cl.bcs.spot.MantenedorPuntas;
import cl.bcs.tesoreria.Tesoreria;


public class Spot {
	private static final Logger LOGGER = Log4jFactory.getLogger(Spot.class);
	public static boolean suiteSpot(SpotExcel usu) {
		LOGGER.info("==================================================================================");
		LOGGER.info("VARIACION N° " + Session.getVariacion() + " | " +usu.getVariacion());
		LOGGER.info("==================================================================================");
		boolean result = true;	
		result = SeleccionMenu.seleccionarMenuSpot();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMantenedorPuntas();
		if(result == false) {
			return result;
		}
		result = MantenedorPuntas.mantenedorPuntas(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarIngresoOperacionSpot();
		if(result == false) {
			return result;
		}
		result = IngresoOperacionSpot.datosOperacion(usu);
		if(result == false) {
			return result;
		}
		result = IngresoOperacionSpot.formadePago(usu);
		if(result == false) {
			return result;
		}
		result = IngresoOperacionSpot.otros();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarModuloConfirmacionOperacionesSpot();
		if(result == false) {
			return result;
		}
		result = ConfirmacionOperacionesSpot.confirmarSpot(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMenuSpot();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMenuFacturacion();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarFacturacion();
		if(result == false) {
			return result;
		}
		result = Facturacion1.gestionFacturacion(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMenuFacturacion();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMenuCuentaInversion();
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarCuentaInversionCliente();
		if(result == false) {
			return result;
		}
		result = CuentaInversion.cuentaInversionCliente(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionMenu.seleccionarMenuCuentaInversion();
		if(result == false) {
			return result;
		}
		if(usu.getCuentaInversion().equals("NO")) {
			result = SeleccionMenu.seleccionarMenuTesoreria();
			if(result == false) {
				return result;
			}
			result = SeleccionMenu.seleccionarGestionTesoreria();
			if(result == false) {
				return result;
			}
			result = Tesoreria.gestionTesoreria(usu);
			if(result == false) {
				return result;
			}
			result = SeleccionMenu.seleccionarMenuTesoreria();
			if(result == false) {
				return result;
			}
		}
		Session.setVariacion(Session.getVariacion()+1);
		return result;
	}

}
