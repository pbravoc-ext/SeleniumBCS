package cl.bcs.application.suite;



import org.apache.log4j.Logger;

import cl.bcs.application.factory.util.Session;
import cl.bcs.application.factory.util.SpotExcel;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.cuenta.inversion.SelecionarCuentaInversion;
import cl.bcs.facturacion.Facturacion;
import cl.bcs.plataforma.SeleccionarMenu;
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
		result = SeleccionarMenu.init();
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarMantenedorPuntas();
		if(result == false) {
			return result;
		}
		result = MantenedorPuntas.mantenedorPuntas(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarIngresoOperacionSpot();
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
		result = SeleccionarMenu.seleccionarMenuConfirmacionOperaciones();
		if(result == false) {
			return result;
		}
		result = ConfirmacionOperacionesSpot.confirmarSpot(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.init();
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarMenuFacturacion();
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarFacturacion();
		if(result == false) {
			return result;
		}
		result = Facturacion.gestionFacturacion(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarMenuFacturacion();
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarMenuCuentaInversion();
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarCuentaInversionCliente();
		if(result == false) {
			return result;
		}
		result = SelecionarCuentaInversion.cuentaInversionCliente(usu);
		if(result == false) {
			return result;
		}
		result = SeleccionarMenu.seleccionarMenuCuentaInversion();
		if(result == false) {
			return result;
		}
		if(usu.getCuentaInversion().equals("NO")) {
			result = SeleccionarMenu.seleccionarMenuTesoreria();
			if(result == false) {
				return result;
			}
			result = SeleccionarMenu.seleccionarGestionTesoreria();
			if(result == false) {
				return result;
			}
			result = Tesoreria.gestionTesoreria(usu);
			if(result == false) {
				return result;
			}
			result = SeleccionarMenu.seleccionarMenuTesoreria();
			if(result == false) {
				return result;
			}
		}
		Session.setVariacion(Session.getVariacion()+1);
		return result;
	}

}
