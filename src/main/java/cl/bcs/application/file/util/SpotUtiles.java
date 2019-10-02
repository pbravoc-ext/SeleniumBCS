package cl.bcs.application.file.util;

import java.math.BigDecimal;

public class SpotUtiles {
	/**
	 * 
	 * @param label
	 * @return
	 */
	public static String onlyNumbers(String label) {
		return label.replaceAll("[^0-9]", "");
	}
	public static String formatoBigDecimal(String valor) {
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("[a-zA-Z]", "");
		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		return valor;
	}
	public static String formatoMontos(String valor) {
		String valorFinal = valor.replace(".", "");
		valorFinal = valorFinal.replace(",", ".");
		return valorFinal;
	}

	
	public static boolean validacionValorGrilla2(String valor, String valorGrilla) {
		BigDecimal valorFinal = new BigDecimal(formatoBigDecimal(valor));
		BigDecimal valorFinalGrilla = new BigDecimal(formatoBigDecimal(valorGrilla));
		if (valorFinal.compareTo(valorFinalGrilla) == 0) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean validacionValorGrilla3(String valor, String valorGrilla) {
		BigDecimal valorFinal = new BigDecimal(formatoMontos(valor));
		BigDecimal valorFinalGrilla = new BigDecimal(formatoMontos(valorGrilla));
		if (valorFinal.compareTo(valorFinalGrilla) == 0) {
			return true;
		}else {
			return false;
		}
	}
	public static String formatoBigDecimal2(String valor) {
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("[a-zA-Z]", "");
		valor = valor.replace(",", ".");
		return valor;
	}
	
	public static boolean validacionValorGrilla(String valor, String valorGrilla) {
		BigDecimal valorFinal = new BigDecimal(formatoBigDecimal2(valor));
		BigDecimal valorFinalGrilla = new BigDecimal(formatoBigDecimal2(valorGrilla));
		if (valorFinal.compareTo(valorFinalGrilla) == 0) {
			return true;
		}else {
			return false;
		}
	}

		
	
}


