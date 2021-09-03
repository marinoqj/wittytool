package es.golemdr.wittytool.domain.form;

import java.io.Serializable;

public class ConstanteAuxiliarForm implements Serializable{

	private Long idConstante;
	private String familia;
	private String atributo;
	private String literal;
	private Long valor;
	private String activa;



public Long getIdConstante() {
		return idConstante;
	}
	public void setIdConstante(Long idConstante) {
		this.idConstante = idConstante;
	}
public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
public String getLiteral() {
		return literal;
	}
	public void setLiteral(String literal) {
		this.literal = literal;
	}
public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
public String getActiva() {
		return activa;
	}
	public void setActiva(String activa) {
		this.activa = activa;
	}

}
