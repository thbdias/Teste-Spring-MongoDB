package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class Contact {
    @JsonProperty("address") //chave dentro do json
    private String address;
    @JsonProperty("phone") //chave dentro do json
    private String phone;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Contact(String address, String phone){
        this.address = address;
        this.phone = phone;
    }

    public Contact(){}

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return this.phone;
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
