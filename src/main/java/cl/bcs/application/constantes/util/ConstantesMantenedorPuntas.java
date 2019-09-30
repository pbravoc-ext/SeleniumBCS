package cl.bcs.application.constantes.util;
/**
 * 
 * @author dnarvaez_EXT
 *
 */
public class ConstantesMantenedorPuntas {
	/**
	 * GENERAL
	 */
	
	public static final String XPATH_PUNTA_COMPRA = "//*[@id='UI_PUNTA_DEFAULT_COMPRA']/span/span/input[1]";
	public static final String XPATH_PUNTA_VENTA = "//*[@id='UI_PUNTA_DEFAULT_VENTA']/span/span/input[1]";
	public static final String XPATH_SELECCIONAR_MONEDA = "//*[@id='UI_MONEDA_PRINCIPAL']/span/span/span/input";
	
	/**
	 * USD
	 */
	
	public static final String XPATH_MONTO_USD = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[5]/td[1]/span";
	public static final String XPATH_COMPRA_USD = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[5]/td[3]/span";
	public static final String XPATH_VENTA_USD = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[5]/td[4]/span";
	
	/**
	 * EUR 5000
	 */
	
	public static final String XPATH_MONTO_EUR = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[1]/td[1]/span";
	public static final String XPATH_COMPRA_EUR = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[1]/td[3]/span";
	public static final String XPATH_VENTA_EUR = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[1]/td[4]/span";
	
	/**
	 * EUR 1000
	 */
	
	public static final String XPATH_MONTO_EUR_MIL = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[2]/td[1]/span";
	public static final String XPATH_COMPRA_EUR_MIL = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[2]/td[3]/span";
	public static final String XPATH_VENTA_EUR_MIL = "//*[@id='grid-puntas']/span/div[2]/div[3]/table/tbody/tr[2]/td[4]/span";
}
