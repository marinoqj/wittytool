package es.golemdr.wittytool.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Rol{

	private Long idRol;
	private String nombreRol;

	private List<Usuario> usuarios= new ArrayList<>(0); 


@Id
@Column(name="ID_ROL")
public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
@Column(name="NOMBRE_ROL")
public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
@ManyToMany(fetch = FetchType.LAZY, mappedBy="roles")
public List<Usuario> getUsuarios() {
	return usuarios;
}
public void setUsuarios(List<Usuario> usuarios) {
	this.usuarios = usuarios;
}
	

	

}
