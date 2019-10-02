package cl.bcs.application.constantes.util;

/**
 * 
 * @author dnarvaez_EXT
 *
 */
public class ConstantesIngresoOperacionSpot {
		
//	public static final String INSTRUMENTO_INTER = "INTERBANCARIO";	
//	public static final String INSTRUMENTO_ARB_INTER = "ARBITRAJE INTERBANCARIO";	
//	public static final String INSTRUMENTO_DIS = "DISTRIBUCION";	
//	public static final String INSTRUMENTO_ARB_DIS = "ARBITRAJE DISTRIBUCION";

	public static final String XPATH_CLIENTE_PORTAFOLIO = "//*[@id='UI_SOCIO_NEGOCIO']/span/span/span/input";
	
	public static final String XPATH_CLIENTE_PORTAFOLIO_ARROW = "//*[@id='UI_SOCIO_NEGOCIO']/span/span/span/span";

	public static final String XPATH_CLIENTE_PORTAFOLIO_SELECT = "/html/body/div[8]/div/div[2]/ul/li/span";

	public static final String XPATH_BTN_LIMPIAR = "//*[@id='FORM_MesaSpot']//bcs-button[@bcs-clic='data.limpiar()']//button";

	public static final String XPATH_OPERACION = "//*[@id='UI_OPERACION']/span/span/span/input";

	public static final String XPATH_INSTRUMENTO = "//*[@id='UI_TIPO_INSTRUMENTO']/span/span/span/input";

	public static final String XPATH_MONEDA_PRINCIPAL_MONTO = "//*[@id='UI_MONTO_PRINCIPAL']/span/span/input[1]";

	public static final String XPATH_TC_CIERRE = "//*[@id='UI_TC_CIERRE']/span/span/input[1]";

	public static final String XPATH_SELECCIONAR_FORMA_DE_PAGO = "//a[contains(text(),'Formas de Pago')]";

	public static final String XPATH_SELECCIONAR_OTROS = "//a[contains(text(),'Otros')]";

	public static final String XPATH_AGENTE = "//*[@id='UI_AGENTE']/input";

	public static final String XPATH_PARIDAD_CIERRE = "//*[@id='UI_PARIDAD_CIERRE']/span/span/input[1]";
	
	public static final String XPATH_PARIDAD_COSTO = "//*[@id='UI_PARIDAD_COSTO']/span/span/input[1]";

	public static final String XPATH_TC_COSTO = "//*[@id='UI_TC_COSTO']/span/span/input[1]";

	public static final String XPATH_MONTO_EQUIVALENTE = "//*[@id='UI_MONTO_EQUIVALENTE']/span/span/input[1]";

	public static final String XPATH_PUNTA_COMPRA = "//*[@id='UI_PUNTA_COMPRA']/span/span/input[1]";

	public static final String XPATH_PUNTA_VENTA = "//*[@id='UI_PUNTA_VENTA']/span/span/input[1]";

	public static final String XPATH_MARGEN = "//*[@id='UI_MARGEN']/span/span/input[1]";

	public static final String ID_BCSCOMBO_MONEDAPRINCIPAL = "UI_MONEDA_PRINCIPAL";

	public static final String ID_BCSCOMBO_MONEDASECUNDARIA = "UI_MONEDA_SECUNDARIA";

	public static final String XPATH_MONEDAPRINCIPAL = "//*[@id='UI_MONEDA_PRINCIPAL']/span/span/span/input";

	public static final String XPATH_MONEDASECUNDARIA = "//*[@id='UI_MONEDA_SECUNDARIA']/span/span/span/input";

	public static final String XPATH_INPUT_PAGAMOS = "//*[@id='txtEnt1']/span/span/input[1]";

	public static final String XPATH_INPUT_RECIBIMOS = "//*[@id='txtRec1']/span/span/input[1]";

	public static final String XPATH_BCSCOMBOBOX_INGRESORECIBIMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[2]/bcs-combobox-base[1]";

	public static final String XPATH_BCSCOMBOBOX_INGRESOPAGAMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[2]/bcs-combobox-base[2]";

	public static final String XPATH_INPUT_SELECCIONRECIBIMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[2]/bcs-combobox-base[1]/span/span/span/input";

	public static final String XPATH_INPUT_SELECCIONPAGAMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[2]/bcs-combobox-base[2]/span/span/span/input";

	public static final String XPATH_TIPOCOMPROBANTE = "//*[@id='UI_TIPO_COMPROBANTE']/span/span/span/input";

	public static final String XPATH_LABEL_FOLIO = "//*[@id='FORM_VentanaMensajeResultado']/div[2]/div/div[1]/div[1]/div[1]/label";

	public static final String XPATH_BTN_ACEPTAR = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[2]/bcs-button[1]/button";

	public static final String XPATH_BTN_ACEPTARINFO = "//*[@id='FORM_VentanaMensajeResultado']/div[2]/div/div[2]/bcs-button/button";

	public static final String XPATH_FECHA_ENTREGAMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[3]/table/tbody/tr/td[3]";

	public static final String XPATH_FECHA_RECIBIMOS = "//*[@id='FORM_MesaSpot']/div[2]/div/form/div[1]/div[3]/div/div[2]/div/div/div/bcs-forma-pago-mesa/div/div[4]/table/tbody/tr/td[3]";

	public static final String XPATH_MONEDA_SECUNDARIO_MONTO = "//*[@id='UI_MONTO_SECUNDARIA']/span/span/input[1]";
}
