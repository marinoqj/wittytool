package es.golemdr.wittytool.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="constantes_auxiliares")
public class ConstanteAuxiliar{

	private Long idConstante;
	private String familia;
	private String atributo;
	private String literal;
	private Long valor;
	private String activa;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_CONSTANTE")
public Long getIdConstante() {
		return idConstante;
	}
	public void setIdConstante(Long idConstante) {
		this.idConstante = idConstante;
	}
@Column(name="FAMILIA")
public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
@Column(name="ATRIBUTO")
public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
@Column(name="LITERAL")
public String getLiteral() {
		return literal;
	}
	public void setLiteral(String literal) {
		this.literal = literal;
	}
@Column(name="VALOR")
public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}
@Column(name="ACTIVA")
public String getActiva() {
		return activa;
	}
	public void setActiva(String activa) {
		this.activa = activa;
	}

}
