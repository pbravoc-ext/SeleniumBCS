package cl.bcs.application.factory.util;
import java.io.Serializable;


/**
 * 
 * @author Narveider
 *
 */

public class SpotExcel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String variacion; 
	private String rut;
	private String nombre;
	private String portafolio;
	private String instrumento;
	private String monedaPrincipal;
	private String monedaSecundaria;
	private String montoPrincipal;
	private String montoSecundario;
	private String tipoComprobante;
	private String agente;
	private String paridadCierre;
	private String operacion;
	private String cuentaInversion;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getParidadCierre() {
		return paridadCierre;
	}
	public void setParidadCierre(String paridadCierre) {
		this.paridadCierre = paridadCierre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPortafolio() {
		return portafolio;
	}
	public void setPortafolio(String portafolio) {
		this.portafolio = portafolio;
	}
	public String getVariacion() {
		return variacion;
	}
	public void setVariacion(String variacion) {
		this.variacion = variacion;
	}
	public String getInstrumento() {
		return instrumento;
	}
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}
	public String getMonedaPrincipal() {
		return monedaPrincipal;
	}
	public void setMonedaPrincipal(String monedaPrincipal) {
		this.monedaPrincipal = monedaPrincipal;
	}
	public String getMonedaSecundaria() {
		return monedaSecundaria;
	}
	public void setMonedaSecundaria(String monedaSecundaria) {
		this.monedaSecundaria = monedaSecundaria;
	}
	public String getMontoPrincipal() {
		return montoPrincipal;
	}
	public void setMontoPrincipal(String montoPrincipal) {
		this.montoPrincipal = montoPrincipal;
	}
	public String getMontoSecundario() {
		return montoSecundario;
	}
	public void setMontoSecundario(String montoSecundario) {
		this.montoSecundario = montoSecundario;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getAgente() {
		return agente;
	}
	public void setAgente(String agente) {
		this.agente = agente;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getCuentaInversion() {
		return cuentaInversion;
	}
	public void setCuentaInversion(String cuentaInversion) {
		this.cuentaInversion = cuentaInversion;
	}
}