package es.golemdr.wittytool.domain.form;

import java.io.Serializable;
import java.sql.Date;

public class AnotacionForm implements Serializable{

	private Long idAnotacion;
	private String contenido;
	private Long tipo;
	private Date fecha;
	private Long idBitacora;



public Long getIdAnotacion() {
		return idAnotacion;
	}
	public void setIdAnotacion(Long idAnotacion) {
		this.idAnotacion = idAnotacion;
	}
public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
public Long getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}

}
