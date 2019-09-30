package cl.bcs.application.constantes.util;
/**
 * 
 * @author Narveider
 *
 */
public class ConstantesCuentaInversion {

	public static final String ID_MODULOFACTURACION = "MENU_MODULO_FACTURACION";
	public static final String ID_MODULOCUENTAINVERSION = "MENU_MODULO_CUENTA_INVERSION";
	public static final String ID_CUENTAINVERSION = "MENU_CuentaInversionCliente";
	public static final String XPATH_CLIENTE_INVERSION = "//*[@id='frmCtaCliente_clientePortafolio']/span/span/span/input";
	public static final String XPATH_BOTON_BUSCAR = "//*[@id='frmCtaCliente_BUSCAR']/button";
	public static final String XPATH_TAB_CERT_ANULAR = "//*[@id='FORM_CuentaInversionCliente']/div[2]/div/div/div[2]/ul/li[3]/a";
	public static final String XPATH_TIPO_COMPROBANTE = "/html/body/div[6]/div[2]/div[2]/div/div/div[2]/div/div[3]/div/div[4]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[8]/span/span/span[1]/input";
	public static final String XPATH_FOLIO_COMPROBANTE = "/html/body/div[6]/div[2]/div[2]/div/div/div[2]/div/div[3]/div/div[4]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[10]/span/span/span[1]/span/input[1]";
	public static final String XPATH_FECHA = "/html/body/div[6]/div[2]/div[2]/div/div/div[2]/div/div[3]/div/div[4]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[1]/span/span/span[1]/span/input";
	public static final String XPATH_CARGO = "/html/body/div[6]/div[2]/div[2]/div/div/div[2]/div/div[3]/div/div[4]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[6]/span/span/span[1]/span/input[1]";
	public static final String XPATH_TRANSFERENCIA = "/html/body/div[6]/div[2]/div[2]/div/div/div[2]/div/div[3]/div/div[4]/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[5]/span/span/span[1]/input";
	public static final String XPATH_ESTADO = "//*[@id='frmCtaCliente_gridMovimientosCuentaInversion']//span[@ng-bind='dataItem.NombreEstado' and contains(text(),'CERTIFICADO')]";

}
