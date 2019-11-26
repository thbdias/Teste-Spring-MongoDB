package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SituacaoContrato {
    @JsonProperty("diaVenc") //chave dentro do json
    private int diaVencimento;
    @JsonProperty("dtPriAber") //chave dentro do json
    private String dataPrimeiraPrestacaoAberta; //yyyyMM

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public String getDataPrimeiraPrestacaoAberta() {
        return dataPrimeiraPrestacaoAberta;
    }

    public void setDataPrimeiraPrestacaoAberta(String dataPrimeiraPrestacaoAberta) {
        this.dataPrimeiraPrestacaoAberta = dataPrimeiraPrestacaoAberta;
    }
}
