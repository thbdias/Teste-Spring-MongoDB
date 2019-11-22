package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SituacaoEspecial {

    @JsonProperty("codigo") //chave dentro do json
    private Integer codigoSituacaoEspecial;

    public SituacaoEspecial(){}

    public void setCodigoSituacaoEspecial(Integer codigoSituacaoEspecial) {
        this.codigoSituacaoEspecial = codigoSituacaoEspecial;
    }

    public Integer getCodigoSituacaoEspecial() {
        return codigoSituacaoEspecial;
    }
}
