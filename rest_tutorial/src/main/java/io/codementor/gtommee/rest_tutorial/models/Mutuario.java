package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mutuario {
    @JsonProperty("nome") //chave dentro do json
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
