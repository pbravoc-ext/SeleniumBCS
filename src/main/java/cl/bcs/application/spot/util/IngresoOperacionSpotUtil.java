package cl.bcs.application.spot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.relevantcodes.extentreports.LogStatus;
import cl.bcs.application.constantes.util.ConstantesIngresoOperacionSpot;
import cl.bcs.application.constantes.util.ConstantesSpotTags;
import cl.bcs.application.factory.util.Session;
import cl.bcs.application.file.util.Log4jFactory;
import cl.bcs.application.file.util.UtilesExtentReport;
import cl.bcs.application.file.util.UtilesSelenium;

/**
 * 
 * @author Narveider
 *
 */
public class IngresoOperacionSpotUtil {

	private static final Logger LOGGER = Log4jFactory.getLogger(IngresoOperacionSpotUtil.class);

	/**
	 * 
	 * @param cantidad
	 * @return
	 */
	protected static Float formato(String cantidad) {
		cantidad = cantidad.replace(".", "");
		cantidad = cantidad.replace(",", ".");
		return Float.parseFloat(cantidad);
	}

	protected static String formatoBigDeciaml(String valor) {
		valor = valor.replaceAll(" ", "");
		valor = valor.replaceAll("[a-zA-Z]", "");
		valor = valor.replace(".", "");
		valor = valor.replace(",", ".");
		return valor;
	}
	
	protected static void ingresoInstrumento(String instrumento) {
		UtilesSelenium.findElement(By.xpath(ConstantesIngresoOperacionSpot.XPATH_INSTRUMENTO)).click();
		Session.getConfigDriver().waitForLoad();
		UtilesSelenium.findElement(By.xpath(ConstantesIngresoOperacionSpot.XPATH_INSTRUMENTO)).clear();
		Session.getConfigDriver().waitForLoad();
		UtilesSelenium.findElement(By.xpath(ConstantesIngresoOperacionSpot.XPATH_INSTRUMENTO))
				.sendKeys(instrumento + Keys.TAB);
		Session.getConfigDriver().logger.log(LogStatus.INFO, "Ingreso instrumento", "Datos: " + instrumento);
		LOGGER.info("Instrumento Seleccionado: " + instrumento);
		Session.getConfigDriver().waitForLoad();
	}
	/**
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return
	 */
	// modificar
	protected static String comparar(String fecha1, String fecha2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date1 = sdf.parse(fecha1);
			Date date2 = sdf.parse(fecha2);
			int valor = date1.compareTo(date2);
			if (valor >= 0) {
				UtilesExtentReport.captura("Validacion de fecha es correcta");
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Comparación", "Datos: "+ fecha1+" Menor o Igual a: "+ fecha2);
				LOGGER.info("Fecha Correcta ");
			} else {
				UtilesExtentReport.capturaError("Fecha forma de pago 'Recibimos' es mayor a 'Entregamos'");
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Comparación", "Datos: "+ fecha1+ " Mayor a: "+ fecha2);
				LOGGER.error("Fecha Incorrecta");
			}
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
		return "";
	}

	/**
	 * 
	 * @param puntaCompra
	 * @param puntaVenta
	 * @param iosPuntaCompra
	 * @param iosPuntaVenta
	 */
	protected static void validacionValoresPunta(String puntaCompra, String puntaVenta, String iosPuntaCompra,
			String iosPuntaVenta) {
		if (Float.compare(formato(iosPuntaCompra), formato(puntaCompra)) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion Existosa",
					"Comparacion valores Punta Compra: " + puntaCompra);
			LOGGER.info("IOSPuntaCompra OK");
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validacion Fallida",
					"Comparacion valores Punta Compra: "+ puntaCompra);
			LOGGER.info("IOSPuntaCompra NO");
		}
		Session.getConfigDriver().waitForLoad();

		if (Float.compare(formato(iosPuntaVenta), formato(puntaVenta)) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion Existosa",
					"Comparacion valores Punta Venta: "+ puntaVenta);
			LOGGER.info("IOSPuntaVenta OK");
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validacion Fallida",
					"Comparacion valores Punta Venta: " + puntaVenta);
			LOGGER.info("IOSPuntaVenta NO");
		}
		Session.getConfigDriver().waitForLoad();
	}

	protected static float formatoMontoMargen(String margen) {
		margen = margen.replaceAll(" ", "");
		margen = margen.replaceAll("[a-zA-Z]", "");
		return formato(margen);
	}
	
	protected static boolean validacionInputCosto(String Dato) {		
		if(Dato.equals("true")) {
//			Session.getConfigDriver().logger.log(LogStatus.INFO, "Validacion de campo T/C Costo",
//					"T/C Costo no esta disponible");
			LOGGER.info("T/C Costo no esta disponible");
			return true;
		}else {
//			Session.getConfigDriver().logger.log(LogStatus.INFO, "Validacion de campo T/C Costo",
//					"T/C Costo esta disponible");
			LOGGER.info("T/C Costo esta disponible");
			return false;
		}
	}

	protected static void validacionMargen_NA_Compra(String montoFinal, String margen, String valotTcCosto,
			String valorTcCierre) {
		java.math.BigDecimal MontoBD = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal Costo = new java.math.BigDecimal(formatoBigDeciaml(valotTcCosto));
		java.math.BigDecimal Cierre = new java.math.BigDecimal(formatoBigDeciaml(valorTcCierre));
		java.math.BigDecimal Resta = Costo.subtract(Cierre);
		java.math.BigDecimal MargenCalc = MontoBD.multiply(Resta);
		java.math.BigDecimal MARGEN = new java.math.BigDecimal(formatoBigDeciaml(margen));
		if (MargenCalc.compareTo(MARGEN) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de Margen para no arbitraje", "Dato: " + MARGEN);
			LOGGER.info("Validacion exitosa de Margen para no arbitraje: " + MARGEN);
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Margen no Valido para no arbitraje CLP: " + MargenCalc,
					  " Debe ser igual a: " + MARGEN);
			LOGGER.info("Margen no valido para no arbitraje a margen calculado: " + MARGEN);
		}
	}
	protected static void validacionMargen_NA_Venta(String montoFinal, String margen, String valotTcCosto,
			String valorTcCierre) {
		java.math.BigDecimal MontoBD = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal Costo = new java.math.BigDecimal(formatoBigDeciaml(valotTcCosto));
		java.math.BigDecimal Cierre = new java.math.BigDecimal(formatoBigDeciaml(valorTcCierre));
		java.math.BigDecimal Resta = Cierre.subtract(Costo);
		java.math.BigDecimal MargenCalc = MontoBD.multiply(Resta);
		java.math.BigDecimal MARGEN = new java.math.BigDecimal(formatoBigDeciaml(margen));
		if (MargenCalc.compareTo(MARGEN) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de Margen para no arbitraje",
					"Datos: " + MARGEN);
			LOGGER.info("Validacion exitosa de Margen para no arbitraje: " + MARGEN);
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Margen no Valido para no arbitraje CLP: " + MargenCalc,
					 "Debe ser igual a: " + MARGEN);
			LOGGER.info("Margen no valido para no arbitraje a margen calculado: " + MARGEN);
		}
	}

	protected static void validacionMargen_A_Compra(String montoFinal, String margen, String valorParidadCosto,
			String valorParidadCierre) {
		java.math.BigDecimal MontoBD = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal Costo = new java.math.BigDecimal(formatoBigDeciaml(valorParidadCosto));
		java.math.BigDecimal Cierre = new java.math.BigDecimal(formatoBigDeciaml(valorParidadCierre));
		java.math.BigDecimal Resta = Costo.subtract(Cierre);
		java.math.BigDecimal MargenCalc = MontoBD.multiply(Resta);
		java.math.BigDecimal MARGEN = new java.math.BigDecimal(formatoBigDeciaml(margen));
		if (MargenCalc.compareTo(MARGEN) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de Margen para arbitraje",
					"Datos: " + MARGEN);
			LOGGER.info("Validacion exitosa de Margen para arbitraje: " + MARGEN);
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Margen no Valido para arbitraje CLP: " + MargenCalc,
					 "Debe ser igual a: " + MARGEN);
			LOGGER.info("Margen no valido para arbitraje a margen calculado: " + MARGEN);
		}
	}

	protected static void validacionMargen_A_Venta(String montoFinal, String margen, String valorParidadCosto,
			String valorParidadCierre) {
		java.math.BigDecimal MontoBD = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal Costo = new java.math.BigDecimal(formatoBigDeciaml(valorParidadCosto));
		java.math.BigDecimal Cierre = new java.math.BigDecimal(formatoBigDeciaml(valorParidadCierre));
		java.math.BigDecimal Resta = Cierre.subtract(Costo);
		java.math.BigDecimal MargenCalc = MontoBD.multiply(Resta);
		java.math.BigDecimal MARGEN = new java.math.BigDecimal(formatoBigDeciaml(margen));
		if (MargenCalc.compareTo(MARGEN) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de Margen para arbitraje",
					"Datos: " + MARGEN);
			LOGGER.info("Validacion exitosa de Margen para arbitraje: " + MARGEN);
		} else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Margen no Valido para arbitraje CLP:" + MargenCalc,
					 "Debe ser igual a: " + MARGEN);
			LOGGER.info("Margen no valido para arbitraje a margen calculado: " + MARGEN);
		}
	}

	protected static void validacionMontoEquivalente_NA(String montoEquivalente, String montoFinal,
			String valorTcCierre) {
		java.math.BigDecimal nuevoMontoEquivalente = new java.math.BigDecimal(formatoBigDeciaml(montoEquivalente));
		java.math.BigDecimal nuevoMontoFinal = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal nuevoValorTcCierre = new java.math.BigDecimal(formatoBigDeciaml(valorTcCierre));
		java.math.BigDecimal montoCalculado = nuevoMontoFinal.multiply(nuevoValorTcCierre);
		Session.setMontoEq(formatoBigDeciaml(montoEquivalente));
		if (montoCalculado.compareTo(nuevoMontoEquivalente) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Monto Equivalente Valido para no arbitraje",
					"Datos: " + nuevoMontoEquivalente);
			LOGGER.info("Validacion exitosa de Monto Equivalente para no arbitraje: " + nuevoMontoEquivalente);
		}else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Monto Equivalente no Valido para no arbitraje CLP: " + montoCalculado,
					"Debe ser igual a: " + nuevoMontoEquivalente);
			LOGGER.info("Monto equivalente no valido para no arbitraje a monto calculado: " + nuevoMontoEquivalente);
		}

	}

	protected static void validacionMontoEquivalente_A(String montoEquivalente, String montoFinal,
			String valorParidadCierre) {
		java.math.BigDecimal nuevoMontoEquivalente = new java.math.BigDecimal(formatoBigDeciaml(montoEquivalente));
		java.math.BigDecimal nuevoMontoFinal = new java.math.BigDecimal(formatoBigDeciaml(montoFinal));
		java.math.BigDecimal nuevoValorParidadCierre = new java.math.BigDecimal(formatoBigDeciaml(valorParidadCierre));
		java.math.BigDecimal montoCalculado = nuevoMontoFinal.multiply(nuevoValorParidadCierre);
		Session.setMontoEq(formatoBigDeciaml(montoEquivalente));
		if (montoCalculado.compareTo(nuevoMontoEquivalente) == 0) {
			Session.getConfigDriver().logger.log(LogStatus.PASS, "Monto Equivalente Valido para arbitraje",
					"Datos: " + nuevoMontoEquivalente);
			LOGGER.info("Validacion exitosa de Monto Equivalente para arbitraje: " + nuevoMontoEquivalente);
		}else {
			Session.getConfigDriver().logger.log(LogStatus.WARNING, "Monto Equivalente no Valido para arbitraje CLP: " + montoCalculado ,
					"Debe ser igual a: " + nuevoMontoEquivalente);
			LOGGER.info("Monto equivalente no valido para arbitraje a monto calculado: " + nuevoMontoEquivalente);
		}
	}
	protected static void validarTC(String instrumento) {
		String validacionTcCosto = UtilesSelenium.findElement(By.xpath(ConstantesIngresoOperacionSpot.XPATH_TC_COSTO))
				.getAttribute(ConstantesSpotTags.TAG_DISABLE);
		if (instrumento.equals(ConstantesIngresoOperacionSpot.INSTRUMENTO_DIS)) {
			if (!validacionInputCosto(validacionTcCosto)) {
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de campo T/C Costo",
						"T/C Costo esta disponible");
			} else {
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validacion erronea de campo T/C Costo",
						"T/C Costo debe estar disponible");
			}
		} else {
			if (validacionInputCosto(validacionTcCosto)) {
				Session.getConfigDriver().logger.log(LogStatus.PASS, "Validacion exitosa de campo T/C Costo",
						"T/C Costo no esta disponible");
			} else {
				Session.getConfigDriver().logger.log(LogStatus.WARNING, "Validacion erronea de campo T/C Costo",
						"T/C Costo no debe estar disponible");
			}
		}
	}

}
