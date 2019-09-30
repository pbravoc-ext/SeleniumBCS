package cl.bcs.application.spot.util;

import org.apache.log4j.Logger;

import cl.bcs.application.file.util.Log4jFactory;

public class CuentaInversionUtil {

	private static final Logger LOGGER = Log4jFactory.getLogger(CuentaInversionUtil.class);

	protected static String formatoBigDeciaml(String valor) {
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("[a-zA-Z]", "");
		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		return valor;
	}
	protected static void validarCargo(String cargo, String cargoGrilla) {
		java.math.BigDecimal cargo1 = new java.math.BigDecimal(cargo);
		java.math.BigDecimal cargo2 = new java.math.BigDecimal(cargoGrilla);
		if (cargo1.compareTo(cargo2) == 0) {
			
		}else {
			
		}
	}
	
	protected static void validarAbono(String abono, String abonoGrilla) {
		java.math.BigDecimal abono1 = new java.math.BigDecimal(abono);
		java.math.BigDecimal abono2 = new java.math.BigDecimal(abonoGrilla);
		if (abono1.compareTo(abono2) == 0) {
			
		}else {
			
		}
		
	}

}