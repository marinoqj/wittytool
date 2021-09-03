package es.golemdr.wittytool.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="incidencias")
public class Incidencia{

	private Long idIncidencia;
	private Long tipo;
	private String resumen;
	private String descripcion;
	private Long prioridad;
	private Long idSprint;
	private Long idProyecto;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_INCIDENCIA")
public Long getIdIncidencia() {
		return idIncidencia;
	}
	public void setIdIncidencia(Long idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
@Column(name="TIPO")
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
@Column(name="RESUMEN")
public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
@Column(name="DESCRIPCION")
public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
@Column(name="PRIORIDAD")
public Long getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Long prioridad) {
		this.prioridad = prioridad;
	}
@Column(name="ID_SPRINT")
public Long getIdSprint() {
		return idSprint;
	}
	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}
@Column(name="ID_PROYECTO")
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
