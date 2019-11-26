package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mutuario {
    @JsonProperty("nome") //chave dentro do json
    private String nome;
    @JsonProperty("TipPes") //chave dentro do json
    private Integer tipoPessoa;
    @JsonProperty("cpf") //chave dentro do json
    private String cpf;
    @JsonProperty("dddRes") //chave dentro do json
    private String dddResidencia;
    @JsonProperty("telRes") //chave dentro do json
    private String telResidencia;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
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
}
