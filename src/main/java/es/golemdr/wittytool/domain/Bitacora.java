package es.golemdr.wittytool.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bitacoras")
public class Bitacora{

	private Long idBitacora;
	private Long idProyecto;
	private Long idUsuario;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_BITACORA")
public Long getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}
@Column(name="ID_PROYECTO")
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
@Column(name="ID_USUARIO")
public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
