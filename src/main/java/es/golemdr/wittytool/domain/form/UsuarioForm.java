package es.golemdr.wittytool.domain.form;

import java.io.Serializable;


public class UsuarioForm implements Serializable{

	private Long idUsuario;
	private String login;
	private String password;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String cambiarPassword;



public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
public String getCambiarPassword() {
		return cambiarPassword;
	}
	public void setCambiarPassword(String cambiarPassword) {
		this.cambiarPassword = cambiarPassword;
	}

}
