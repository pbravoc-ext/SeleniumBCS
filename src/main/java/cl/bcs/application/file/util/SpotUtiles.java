package cl.bcs.application.file.util;



public class SpotUtiles {
	/**
	 * 
	 * @param label
	 * @return
	 */
	public static String folio(String label) {
		return label.replaceAll("[^0-9]", "");
	}
	public static String formatoBigDecimal(String valor) {
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("[a-zA-Z]", "");
		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		return valor;
	}
	
	public static boolean validacionValorGrilla2(String valor, String valorGrilla) {
		java.math.BigDecimal valorFinal = new java.math.BigDecimal(formatoBigDecimal(valor));
		java.math.BigDecimal valorFinalGrilla = new java.math.BigDecimal(formatoBigDecimal(valorGrilla));
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
		java.math.BigDecimal valorFinal = new java.math.BigDecimal(formatoBigDecimal2(valor));
		java.math.BigDecimal valorFinalGrilla = new java.math.BigDecimal(formatoBigDecimal2(valorGrilla));
		if (valorFinal.compareTo(valorFinalGrilla) == 0) {
			return true;
		}else {
			return false;
		}
	}

		
	
}


