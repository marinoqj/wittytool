package es.golemdr.wittytool.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario{

	private Long idUsuario;
	private String login;
	private String password;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String cambiarPassword;

	private List<Rol> roles= new ArrayList<>(0);

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_USUARIO")
public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
@Column(name="LOGIN")
public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
@Column(name="PASSWORD")
public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
@Column(name="NOMBRE")
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
@Column(name="APELLIDO1")
public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
@Column(name="APELLIDO2")
public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
@Column(name="CAMBIAR_PASSWORD")
public String getCambiarPassword() {
		return cambiarPassword;
	}
	public void setCambiarPassword(String cambiarPassword) {
		this.cambiarPassword = cambiarPassword;
	}

	
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "roles_usuarios", joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_rol") })	
public List<Rol> getRoles() {
	return roles;
}
public void setRoles(List<Rol> roles) {
	this.roles = roles;
}




}
