package io.codementor.gtommee.rest_tutorial.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection="company")
public class Company {

//    private int id;
    @Id
    public ObjectId _id;
    @JsonProperty("name") //chave dentro do json
    private String name;
    @JsonProperty("products") //chave dentro do json
    private List<Product> products;
    @JsonProperty("contact")  //chave dentro do json
    private Contact contact;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public List<Product> getProducts(){
        return this.products;
    }

    public void setContact(Contact contact){
        this.contact = contact;
    }

    public Contact getContact(){
        return this.contact;
    }

    public Company(ObjectId _id, String name, List<Product> products, Contact contact){
        this._id = _id;
        this.name = name;
        this.products = products;
        this.contact = contact;
    }

    public Company(){}

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonString = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
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
