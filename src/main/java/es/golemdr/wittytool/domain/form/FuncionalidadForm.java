package es.golemdr.wittytool.domain.form;


import java.io.Serializable;

public class FuncionalidadForm implements Serializable{

	private Long idFuncionalidad;
	private Long categoria;
	private Long subcategoria;
	private String descripci�n;
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
public String getDescripci�n() {
		return descripci�n;
	}
	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}
public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

}
