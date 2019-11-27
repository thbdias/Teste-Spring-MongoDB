package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SituacaoContrato {
    @JsonProperty("diaVenc") //chave dentro do json
    private int diaVencimento;
    @JsonProperty("dtPriAber") //chave dentro do json
    private String dataPrimeiraPrestacaoAberta; //yyyyMM
    @JsonProperty("qtdPreAtraso") //chave dentro do json
    private int quantPrestAtraso;
    @JsonProperty("diasAtraso") //chave dentro do json
    private int diasAtraso;
    @JsonProperty("VlrPreAtr") //chave dentro do json
    private Double valorUltimaPrestacaoAtraso;
    @JsonProperty("DiasAtrUltPrePg") //chave dentro do json
    private String diasAtrUltPrePg;
    @JsonProperty("PercDivPg") //chave dentro do json
    private String percDividaPaga;
    @JsonProperty("vlrDiv") //chave dentro do json
    private String valorDividaEmAtraso;
    @JsonProperty("garAtu") //chave dentro do json
    private String valorGarantiaAtualizada;


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

    public int getQuantPrestAtraso() {
        return quantPrestAtraso;
    }

    public void setQuantPrestAtraso(int quantPrestAtraso) {
        this.quantPrestAtraso = quantPrestAtraso;
    }

    public int getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public Double getValorUltimaPrestacaoAtraso() {
        return valorUltimaPrestacaoAtraso;
    }

    public void setValorUltimaPrestacaoAtraso(Double valorUltimaPrestacaoAtraso) {
        this.valorUltimaPrestacaoAtraso = valorUltimaPrestacaoAtraso;
    }

    public String getDiasAtrUltPrePg() {
        return diasAtrUltPrePg;
    }

    public void setDiasAtrUltPrePg(String diasAtrUltPrePg) {
        diasAtrUltPrePg = diasAtrUltPrePg;
    }

    public String getPercDividaPaga() {
        return percDividaPaga;
    }

    public void setPercDividaPaga(String percDividaPaga) {
        this.percDividaPaga = percDividaPaga;
    }

    public String getValorDividaEmAtraso() {
        return valorDividaEmAtraso;
    }

    public void setValorDividaEmAtraso(String valorDividaEmAtraso) {
        this.valorDividaEmAtraso = valorDividaEmAtraso;
    }

    public String getValorGarantiaAtualizada() {
        return valorGarantiaAtualizada;
    }

    public void setValorGarantiaAtualizada(String valorGarantiaAtualizada) {
        this.valorGarantiaAtualizada = valorGarantiaAtualizada;
    }
}
