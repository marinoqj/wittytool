package es.golemdr.wittytool.domain.form;


import java.io.Serializable;

public class RolForm implements Serializable{

	private Long idRol;
	private String nombre;



public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
