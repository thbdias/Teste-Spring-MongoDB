package io.codementor.gtommee.rest_tutorial.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContratoDistribuicao {

//	@JsonProperty("id")
	private String _id;
//	@JsonProperty
	private Long clienteCobrancaTerceirizadaId;
//	@JsonProperty
	private Long convenioId;
//	@JsonProperty
	private Long execucaoSelecaoId;
//	@JsonProperty
	private String dataDistribuicao;
//	@JsonProperty
	private Integer situacao;
//	@JsonProperty
	private String dataEncerramento;
//	@JsonProperty
	private Contrato contrato;
//	@JsonProperty
	private Regras regras;
	
	

	public ContratoDistribuicao() {
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long getClienteCobrancaTerceirizadaId() {
		return clienteCobrancaTerceirizadaId;
	}

	public void setClienteCobrancaTerceirizadaId(Long clienteCobrancaTerceirizadaId) {
		this.clienteCobrancaTerceirizadaId = clienteCobrancaTerceirizadaId;
	}

	public Long getConvenioId() {
		return convenioId;
	}

	public void setConvenioId(Long convenioId) {
		this.convenioId = convenioId;
	}

	public Long getExecucaoSelecaoId() {
		return execucaoSelecaoId;
	}

	public void setExecucaoSelecaoId(Long execucaoSelecaoId) {
		this.execucaoSelecaoId = execucaoSelecaoId;
	}

	public String getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(String dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Regras getRegras() {
		return regras;
	}

	public void setRegras(Regras regras) {
		this.regras = regras;
	}

}
