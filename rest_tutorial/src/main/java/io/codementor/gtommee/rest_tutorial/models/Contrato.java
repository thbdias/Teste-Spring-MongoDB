package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contrato {

    @JsonProperty("contrato") //chave dentro do json
    private Long numeroContrato;
    @JsonProperty("credor") //chave dentro do json
    private Integer codigoCredor;
    @JsonProperty("adm") //chave dentro do json
    private Integer codigoAdminitrador;
    @JsonProperty("mutuario") //chave dentro do json
    private Mutuario mutuario;
    @JsonProperty("ses") //chave dentro do json
    private List<SituacaoEspecial> situacoesEspeciais;
    @JsonProperty("coobrigados") //chave dentro do json
    private List<Coobrigado> coobrigados;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Contrato(){}

    public void setNumeroContrato(Long numeroContrato){
        this.numeroContrato = numeroContrato;
    }

    public Long getNumeroContrato(){
        return this.numeroContrato;
    }

    public void setCodigoCredor(Integer codigoCredor){
        this.codigoCredor = codigoCredor;
    }

    public Integer getCodigoCredor(){
        return this.codigoCredor;
    }

    public void setCodigoAdminitrador(Integer codigoAdminitrador){
        this.codigoAdminitrador = codigoAdminitrador;
    }

    public Integer getCodigoAdminitrador(){
        return this.codigoAdminitrador;
    }

    public Mutuario getMutuario() {
        return mutuario;
    }

    public void setMutuario(Mutuario mutuario) {
        this.mutuario = mutuario;
    }

    public List<SituacaoEspecial> getSituacoesEspeciais() {
        return situacoesEspeciais;
    }

    public void setSituacoesEspeciais(List<SituacaoEspecial> situacoesEspeciais) {
        this.situacoesEspeciais = situacoesEspeciais;
    }

    public List<Coobrigado> getCoobrigados() {
        return coobrigados;
    }

    public void setCoobrigados(List<Coobrigado> coobrigados) {
        this.coobrigados = coobrigados;
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
