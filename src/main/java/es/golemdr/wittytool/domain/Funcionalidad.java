package es.golemdr.wittytool.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="funcionalidades")
public class Funcionalidad{

	private Long idFuncionalidad;
	private Long categoria;
	private Long subcategoria;
	private String descripción;
	private Long idProyecto;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_FUNCIONALIDAD")
public Long getIdFuncionalidad() {
		return idFuncionalidad;
	}
	public void setIdFuncionalidad(Long idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}
@Column(name="CATEGORIA")
public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
@Column(name="SUBCATEGORIA")
public Long getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Long subcategoria) {
		this.subcategoria = subcategoria;
	}
@Column(name="DESCRIPCIÓN")
public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
@Column(name="ID_PROYECTO")
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
