package es.golemdr.wittytool.domain.form;


import java.io.Serializable;

public class ProyectoForm implements Serializable{

	private Long idProyecto;
	private String nombre;
	private String clave;



public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}
