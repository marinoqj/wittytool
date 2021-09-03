package es.golemdr.wittytool.domain.form;

import java.io.Serializable;
import java.sql.Date;

public class EventoForm implements Serializable{

	private Long idEvento;
	private Long tipo;
	private String descripcion;
	private String palabrasClave;
	private String observaciones;
	private Date fecha;
	private Long idProyecto;



public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
public String getPalabrasClave() {
		return palabrasClave;
	}
	public void setPalabrasClave(String palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
