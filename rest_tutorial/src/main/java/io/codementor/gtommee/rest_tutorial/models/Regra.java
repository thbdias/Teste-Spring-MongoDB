package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regra {

//	@JsonProperty
	private String regraId;
//	@JsonProperty
	private DadosContrato dadosContrato;

	public String getRegraId() {
		return regraId;
	}

	public void setRegraId(String regraId) {
		this.regraId = regraId;
	}

	public DadosContrato getDadosContrato() {
		return dadosContrato;
	}

	public void setDadosContrato(DadosContrato dadosContrato) {
		this.dadosContrato = dadosContrato;
	}

}
