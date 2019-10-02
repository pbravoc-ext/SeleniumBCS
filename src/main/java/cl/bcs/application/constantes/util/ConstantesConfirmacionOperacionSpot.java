package cl.bcs.application.constantes.util;

import cl.bcs.application.factory.util.Session;
/**
 * 
 * @author Narveider
 *
 */
public class ConstantesConfirmacionOperacionSpot {
	
	public static final String XPATH_CLIENTE = "//*[@id='UI_SOCIO_NEGOCIO']/span/span/span/input";
		
	public static final String XPATH_BOTON_CONFIRMAR = "//*[@id='FORM_LiquidarSpot']/div[2]/div/form/div/div[5]/div[1]/bcs-button/button";
	
	public static final String XPATH_BOTON_ACEPTAR = "/html/body/div[36]/div/div/div/div[3]/bcs-button[1]/button";
	
	public static final String XPATH_BOTON_INFO_ACEPTAR = "//*[@id='FORM_VentanaMensajeResultado']/div[2]/div/div[2]/bcs-button/button";
	
	public static final String XPATH_FOLIO_INPUT = "/html/body/div[6]/div[2]/div[2]/div/form/div/div[4]/div/bcs-grid/span/div[2]/div[3]/div/table/thead/tr[2]/th[2]/span/span/span[1]/span/input[1]";

	public static final String XPATH_THERE = "//*[@id='grid-mov']/span/div[2]/div[4]/table//span[@ng-bind='dataItem.FolioOperacion' and contains(text(),";
	
	public static final String XPATH_MONTO_GRILLA = "//*[@id='grid-mov']/span/div[2]/div[4]/table/tbody/tr[1]/td[12]/label";
}
