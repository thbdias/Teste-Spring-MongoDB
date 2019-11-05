package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class Product {
    @JsonProperty("code") //chave dentro do json
    private String code;
    @JsonProperty("name") //chave dentro do json
    private String name;
    @JsonProperty("details") //chave dentro do json
    private String details;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Product(String code, String name, String details){
        this.code = code;
        this.name = name;
        this.details = details;
    }

    public Product(){}

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDetails(String details){
        this.details = details;
    }

    public String getDetails(){
        return this.details;
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
