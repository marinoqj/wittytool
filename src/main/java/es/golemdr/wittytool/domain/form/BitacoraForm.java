package es.golemdr.wittytool.domain.form;


import java.io.Serializable;

public class BitacoraForm implements Serializable{

	private Long idBitacora;
	private Long idProyecto;
	private Long idUsuario;



public Long getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
