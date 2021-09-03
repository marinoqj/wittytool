package es.golemdr.wittytool.domain.form;

import java.io.Serializable;
import java.sql.Date;

public class SprintForm implements Serializable{

	private Long idSprint;
	private String nombre;
	private String meta;
	private Long duracion;
	private Date fechaInicio;
	private Date fechaFin;
	private Long idProyecto;



public Long getIdSprint() {
		return idSprint;
	}
	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
public Long getDuracion() {
		return duracion;
	}
	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}
public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
