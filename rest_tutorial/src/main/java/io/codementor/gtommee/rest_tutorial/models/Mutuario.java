package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mutuario {
    @JsonProperty("nome") //chave dentro do json
    private String nome;
    @JsonProperty("TipPes") //chave dentro do json
    private Integer tipoPessoa;

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
}
