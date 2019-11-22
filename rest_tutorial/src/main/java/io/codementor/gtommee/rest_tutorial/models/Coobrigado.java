package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class Coobrigado {

    @JsonProperty("TipPesC") //chave dentro do json
    private Integer tipoPessoa;
    @JsonProperty("nomeC") //chave dentro do json
    private String nome;
    @JsonProperty("cpfC") //chave dentro do json
    private String cpf;
    @JsonProperty("dddResC") //chave dentro do json
    private String dddResidencia;
    @JsonProperty("telResC") //chave dentro do json
    private String telResidencia;
    @JsonProperty("dddCelC") //chave dentro do json
    private String dddCelular;
    @JsonProperty("telCelC") //chave dentro do json
    private String telCelular;
    @JsonProperty("dddComC") //chave dentro do json
    private String dddComercial;
    @JsonProperty("telComC") //chave dentro do json
    private String telComercial;
    @JsonProperty("ramComC") //chave dentro do json
    private String ramalComercial;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Coobrigado(){}

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDddResidencia() {
        return dddResidencia;
    }

    public void setDddResidencia(String dddResidencia) {
        this.dddResidencia = dddResidencia;
    }

    public String getTelResidencia() {
        return telResidencia;
    }

    public void setTelResidencia(String telResidencia) {
        this.telResidencia = telResidencia;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(String dddCelular) {
        this.dddCelular = dddCelular;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getDddComercial() {
        return dddComercial;
    }

    public void setDddComercial(String dddComercial) {
        this.dddComercial = dddComercial;
    }

    public String getTelComercial() {
        return telComercial;
    }

    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    public String getRamalComercial() {
        return ramalComercial;
    }

    public void setRamalComercial(String ramalComercial) {
        this.ramalComercial = ramalComercial;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
