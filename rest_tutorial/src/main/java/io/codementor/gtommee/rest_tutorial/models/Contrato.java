package io.codementor.gtommee.rest_tutorial.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contrato {

//	@JsonProperty
	private Long numero;
//	@JsonProperty
	private Integer diasAtraso;
//	@JsonProperty
	private String dataAtualizacaoDivida;
//	@JsonProperty
	private Double valorDivida;
//	@JsonProperty
	private Double valorDividaAtualizada;
//	@JsonProperty
	private DadosContrato dadosContrato;
	
	

	public Contrato() {
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Integer getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(Integer diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public String getDataAtualizacaoDivida() {
		return dataAtualizacaoDivida;
	}

	public void setDataAtualizacaoDivida(String dataAtualizacaoDivida) {
		this.dataAtualizacaoDivida = dataAtualizacaoDivida;
	}

	public Double getValorDivida() {
		return valorDivida;
	}

	public void setValorDivida(Double valorDivida) {
		this.valorDivida = valorDivida;
	}

	public Double getValorDividaAtualizada() {
		return valorDividaAtualizada;
	}

	public void setValorDividaAtualizada(Double valorDividaAtualizada) {
		this.valorDividaAtualizada = valorDividaAtualizada;
	}

	public DadosContrato getDadosContrato() {
		return dadosContrato;
	}

	public void setDadosContrato(DadosContrato dadosContrato) {
		this.dadosContrato = dadosContrato;
	}

}
