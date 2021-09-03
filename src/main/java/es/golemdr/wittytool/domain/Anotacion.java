package es.golemdr.wittytool.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anotaciones")
public class Anotacion{

	private Long idAnotacion;
	private String contenido;
	private Long tipo;
	private Date fecha;
	private Long idBitacora;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_ANOTACION")
public Long getIdAnotacion() {
		return idAnotacion;
	}
	public void setIdAnotacion(Long idAnotacion) {
		this.idAnotacion = idAnotacion;
	}
@Column(name="CONTENIDO")
public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
@Column(name="TIPO")
public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
@Column(name="FECHA")
public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
@Column(name="ID_BITACORA")
public Long getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}

}
