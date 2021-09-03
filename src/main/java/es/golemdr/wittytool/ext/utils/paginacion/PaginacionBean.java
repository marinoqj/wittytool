package es.golemdr.wittytool.ext.utils.paginacion;

import java.io.Serializable;

public class PaginacionBean implements Serializable{

	private static final long serialVersionUID = -7785583790749171641L;
	private int inicio = 0;
	private int elementosXpagina = 5;
	private int totalRegistros;
	private int totalPaginas;

	
	
	public int getElementosXpagina() {
		return elementosXpagina;
	}
	public void setElementosXpagina(int elementosXpagina) {
		this.elementosXpagina = elementosXpagina;
	}
	public int getInicio() {
		return inicio;
	}
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public int getTotalRegistros() {
		return totalRegistros;
	}
	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}
	public int getTotalPaginas() {
		
		double dTotalRegistros = totalRegistros;
		double dElementosXPagina = elementosXpagina;
		
		double resultado = Math.ceil(dTotalRegistros / dElementosXPagina);		
		
		return (int)resultado;
		
	}

	
}
