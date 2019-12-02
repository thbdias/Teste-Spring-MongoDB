package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcaoCobranca {
    @JsonProperty("codigo")
    private int codigoAcaoCobranca;

    public AcaoCobranca(){}

    public int getCodigoAcaoCobranca() {
        return codigoAcaoCobranca;
    }

    public void setCodigoAcaoCobranca(int codigoAcaoCobranca) {
        this.codigoAcaoCobranca = codigoAcaoCobranca;
    }
}
