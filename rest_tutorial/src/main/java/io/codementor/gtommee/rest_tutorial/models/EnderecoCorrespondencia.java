package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnderecoCorrespondencia {

    @JsonProperty("abvCor")
    private String abreviacao;
    @JsonProperty("nomCor")
    private String logradouro;
    @JsonProperty("nroCor")
    private String numero;
    @JsonProperty("cplCor")
    private String complemento;
    @JsonProperty("baiCor")
    private String bairro;
    @JsonProperty("cidCor")
    private String cidade;
    @JsonProperty("ufCor")
    private String uf;
    @JsonProperty("cepCor")
    private int cep;

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
}
