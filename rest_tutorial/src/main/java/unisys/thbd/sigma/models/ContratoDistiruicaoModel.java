package unisys.thbd.sigma.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContratoDistiruicaoModel {

    @Id
    private ObjectId id;

    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    // Constructors
    public ContratoDistiruicaoModel() {}

//    public ContratoDistiruicaoModel( ) {
//    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.getAdditionalProperties().put(name, value);
    }

    // ObjectId needs to be converted to string
    public String get_id() { return id.toHexString(); }
    public void set_id(ObjectId id) { this.id = id; }

}
