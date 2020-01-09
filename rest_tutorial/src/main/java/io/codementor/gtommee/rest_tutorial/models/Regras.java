package io.codementor.gtommee.rest_tutorial.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Regras {
	
//	@JsonProperty
	private List<Regra> regra;
	
	public void setRegra(List<Regra> regra) {
		this.regra = regra;
	}
	
	public List<Regra> getRegra() {
		return regra;
	}
	
}
