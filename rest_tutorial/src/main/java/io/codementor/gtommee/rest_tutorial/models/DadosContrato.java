package io.codementor.gtommee.rest_tutorial.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DadosContrato {
	
//	@JsonProperty
	private List<DadoContrato> dadoContrato;
	
	public void setDadoContrato(List<DadoContrato> dadoContrato) {
		this.dadoContrato = dadoContrato;
	}
	
	public List<DadoContrato> getDadoContrato() {
		return dadoContrato;
	}
}
