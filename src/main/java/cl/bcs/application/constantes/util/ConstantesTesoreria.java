package cl.bcs.application.constantes.util;

/**
 * 
 * @author rnunez_EXT
 *
 */

public class ConstantesTesoreria {

	public static final String ID_MENU_TESORERIA = "MENU_MODULO_TESORERIA";
	public static final String ID_GESTION_TESORERIA = "MENU_BlotterComprobantesTesoreria";

	public static final String XPATH_TAB_MOVIMIENTOS = "//*[@id='blotterComprobantes']/div/div/div/ul/li[1]/a";
	public static final String XPATH_TAB_COMPROBANTES = "//*[@id='blotterComprobantes']/div/div/div/ul/li[2]/a";
	public static final String XPATH_TAB_FORMA_PAGO = "//*[@id='blotterComprobantes']/div/div/div/ul/li[3]/a";
	public static final String XPATH_TAB_RECEPCION_DOC = "//*[@id='blotterComprobantes']/div/div/div/ul/li[4]/a";

	public static final String XPATH_SOCIO_NEGOCIO_INPUT = "//*[@id='frmConsultarIngresoEgreso_SocioNegocio']/span/span/span/input";
	public static final String XPATH_FECHA_DESDE_INPUT = "//*[@id='frmConsultarIngresoEgreso_fechaLiquidacionDesde']/span/span/span/input";
	public static final String XPATH_FECHA_HASTA_INPUT = "//*[@id='frmConsultarIngresoEgreso_fechaLiquidacionHasta']/span/span/span/input";
	public static final String XPATH_PORTAFOLIO_INTPUT = "//*[@id='frmConsultarIngresoEgreso_Portafolio']/span/span/span/input";
	
	public static final String XPATH_ESTADO_INTPUT = "//*[@id='frmConsultarIngresoEgreso_Estado']/span/span/span/input";
	public static final String XPATH_BTN_BUSCAR = "//*[@id='ConsultarIngresoEgreso']/div[1]/div[1]/div[2]/div[1]/bcs-button/button";
	public static final String XPATH_GRILLA_SECUENCIA_INPUT = "//*[@id='grid-ingreso-egreso']/span/div[2]/div[3]/div/table/thead/tr[3]/th[2]/span/span/span[1]/span/input[1]";
	public static final String XPATH_BTN_SCN = "//*[@id='grid-ingreso-egreso']/span/div[2]/div[3]/div/table/thead/tr[3]/th[2]/span/span/button";
	
	public static final String XPATHERE_INGRESO_EGRESO = "//*[@id='grid-ingreso-egreso']//span[@ng-bind='dataItem.IdIngresoEgreso' and contains(text(),";
	
	public static final String XPATH_INGRESO_EGRESO_GRILLA = "//*[@id='grid-ingreso-egreso']/span/div[2]/div[4]/table/tbody/tr/td[10]/label";
	
	public static final String XPATH_INGRESO_GRILLA_SECUENCIA = "//*[@id='grid-ingreso-egreso']//input";
	
	
}
