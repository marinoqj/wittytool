package es.golemdr.wittytool.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="eventos")
public class Evento{

	private Long idEvento;
	private Long tipo;
	private String descripcion;
	private String palabrasClave;
	private String observaciones;
	private Date fecha;
	private Long idProyecto;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_EVENTO")
public Long getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}
@Column(name="TIPO")
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
@Column(name="DESCRIPCION")
public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
@Column(name="PALABRAS_CLAVE")
public String getPalabrasClave() {
		return palabrasClave;
	}
	public void setPalabrasClave(String palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
@Column(name="OBSERVACIONES")
public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
@Column(name="FECHA")
public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
@Column(name="ID_PROYECTO")
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
