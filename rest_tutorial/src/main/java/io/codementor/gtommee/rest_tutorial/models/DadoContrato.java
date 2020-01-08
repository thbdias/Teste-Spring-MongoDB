package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DadoContrato {

	@JsonProperty("id")
	private Integer _id;
	@JsonProperty
	private String atributo;
	@JsonProperty
	private String valor;
	@JsonProperty
	private String atributoPai;
	

	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	public Integer get_id() {
		return _id;
	}
	
	public void setAtributoPai(String atributoPai) {
		this.atributoPai = atributoPai;
	}
	
	public String getAtributoPai() {
		return atributoPai;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
