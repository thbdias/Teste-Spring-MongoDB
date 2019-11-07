package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class SituacaoEspecial {

    @JsonProperty("codigo") //chave dentro do json
    private Integer codigoSituacaoEspecial;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SituacaoEspecial(){}

    public void setCodigoSituacaoEspecial(Integer codigoSituacaoEspecial) {
        this.codigoSituacaoEspecial = codigoSituacaoEspecial;
    }

    public Integer getCodigoSituacaoEspecial() {
        return codigoSituacaoEspecial;
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
