package es.golemdr.wittytool.domain.form;


import java.io.Serializable;

public class FuncionalidadForm implements Serializable{

	private Long idFuncionalidad;
	private Long categoria;
	private Long subcategoria;
	private String descripción;
	private Long idProyecto;



public Long getIdFuncionalidad() {
		return idFuncionalidad;
	}
	public void setIdFuncionalidad(Long idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}
public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
public Long getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(Long subcategoria) {
		this.subcategoria = subcategoria;
	}
public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
