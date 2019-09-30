package cl.bcs.application.constantes.util;

import cl.bcs.application.factory.util.Session;

/**
 * 
 * @author Narveider
 *
 */
public class ConstantesFacturacion {
	static String monto = "2.222"; // Debe estar en formato 99.999.999

	public static final String COMPRA = "COMPRA";
	public static final String VENTA = "VENTA";

	public static final String XPATH_CLIENTEMOV = "//*[@id='MovimientoFacturarCtrl_UI_SOCIO_NEGOCIO']/span/span/span/input";
	public static final String XPATH_BTN_BUSCARMOV = "//*[@id='gridMovimientoComprobante']/div/div[1]/div[1]/div[2]/div[1]/bcs-button/button";
	public static final String XPATH_BTN_GENERARMOV = "//*[@id='BlotterFacturacion_btnGenerarComprobante']/button";
	public static final String XPATH_BTN_CONFIRMARMOV = "//*[@id='FORM_VentanaAlerta']/div[2]/div/div[2]/bcs-button[1]/button";
	public static final String XPATH_BTN_ACEPTARINFO = "/html/body/div[133]/div[2]/div[2]/div/div[2]/bcs-button/button";
	public static final String XPATH_FOLIOINPUT = "/html/body/div[6]/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div[1]/div[2]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[12]/span/span/span[1]/span/input[1]";

	public static final String XPATH_LABEL_ABONO = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/label";

	public static final String XPATH_LABEL_CARGO = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/label";

	public static final String XPATH_LABEL_COMPROBANTE_CCI = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/label";

	public static final String XPATH_LABEL_CARGO_ARB = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/label";

	public static final String XPATH_LABEL_ABONO_ARB = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[1]/label";
	public static final String XPATH_LABEL_INGRESO_ARB_SCI = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/label";
	public static final String XPATH_LABEL_EGRESO_ARB_SCI = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/label";
	public static final String XPATH_LABEL_COMPROBANTE_ARB_SCI = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/label";

	public static final String XPATH_LABEL_COMPROBANTE_ARB = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/label";

	public static final String XPATH_LABEL_INGRESO = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/label";
	public static final String XPATH_LABEL_EGRESO = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[4]/label";
	public static final String XPATH_LABEL_COMPROBANTE_SCI = "/html/body/div[133]/div[2]/div[2]/div/div[1]/div/div[2]/div[5]/label";

	public static final String XPATH_BTN_ACEPTAR = "//*[@id='FORM_VentanaMensajeResultado']/div[2]/div/div[2]/bcs-button/button";

	public static final String XPATH_TABLA = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table";
//	public static final String XPATH_COMPARARFOLIO = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.FolioTransaccion' and contains(text(),"
//			+ Session.getFolio() + ")]";
//	public static final String XPATH_COMPARARMONTO = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.Monto' and contains(text(),"
//			+ monto + ")]";
	public static final String XPATH_CELDAFOLIO = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table/tbody/tr/td[13]";
	public static final String XPATH_CELDAMONTO = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table/tbody/tr/td[31]";

	public static final String XPATH_MONEDA = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table/tbody/tr/td[33]";
	public static final String XPATH_MONEDALOCAL = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table/tbody/tr/td[35]";
	public static final String XPATH_MONEDALIQUIDACION = "//*[@id='grid-movimientos-facturar']/span/div[2]/div[4]/table/tbody/tr/td[37]";

	public static final String XPATH_TABCOMPROBANTEFACTURACION = "//*[@id='blotterFacturacion']/div/div/div/ul/li[2]";
	public static final String XPATH_TABCOMPROBANTEFACTURACION_ARROW = "//*[@id='ComprobanteFacturacionCtrl_UI_SOCIO_NEGOCIO']/span/span/span/span";
	public static final String XPATH_CLIENTE_PORTAFOLIO_SELECT = "/html/body/div[126]/div/div[2]/ul/li[2]";
	public static final String XPATH_CLIENTEFAC = "//*[@id='ComprobanteFacturacionCtrl_UI_SOCIO_NEGOCIO']/span/span/span/input";
	public static final String XPATH_BTN_BUSCARFAC = "//*[@id='gridComprobante']/div/div[1]/div[1]/div[2]/div[1]/bcs-button/button";
	public static final String XPATH_SECUENCIA = "/html/body/div[6]/div[2]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div[2]/bcs-grid/span/div[2]/div[2]/div/table/thead/tr[2]/th[5]/span/span/span[1]/span/input[1]";
	public static final String XPATH_BTN_ENVIARFAC = "//*[@id='ComprobanteFacturacionCtrl_btnEnviarDTE']/button";
	public static final String XPATH_BTN_CONFIRMARFAC = "//*[@id='FORM_VentanaAlerta']/div[2]/div/div[2]/bcs-button[1]/button";
	public static final String XPATH_BTN_ACEPTARFAC = "//*[@id='FORM_VentanaMensajeResultado']/div[2]/div/div[2]/bcs-button/button";

	public static final String XPATH_COMPARARFOLIOFAC = "//*[@id='grid-comprobantes-facturacion']/span/div[2]/div[3]/table/tbody/tr/td[5]/span[@ng-bind='dataItem.IdComprobante' and contains(text(),";
	public static final String XPATH_COMPARARFOLIOFA2C = ")]";

	public static final String XPATH_BTN_LIMPIAR_SEC = "//*[@id='grid-comprobantes-facturacion']/span/div[2]/div[2]/div/table/thead/tr[2]/th[5]/span/span/button";

	public static final String XPATH_BTN_ERROR = "/html/body/div[133]/div[1]/div/a[2]";

}