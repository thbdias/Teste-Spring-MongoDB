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
    @JsonProperty("ses") //chave dentro do json
    private List<SituacaoEspecial> listSituacaoEspecial;
    @JsonProperty("coobrigados") //chave dentro do json
    private List<Coobrigado> listCoobrigado;
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

    public List<SituacaoEspecial> getListSituacaoEspecial() {
        return listSituacaoEspecial;
    }

    public void setListSituacaoEspecial(List<SituacaoEspecial> listSituacaoEspecial) {
        this.listSituacaoEspecial = listSituacaoEspecial;
    }

    public List<Coobrigado> getListCoobrigado() {
        return listCoobrigado;
    }

    public void setListCoobrigado(List<Coobrigado> listCoobrigado) {
        this.listCoobrigado = listCoobrigado;
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
