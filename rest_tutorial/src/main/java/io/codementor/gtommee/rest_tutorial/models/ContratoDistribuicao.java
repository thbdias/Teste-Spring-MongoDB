package io.codementor.gtommee.rest_tutorial.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ContratoDistribuicaoModel {

    @Id
    private ObjectId id;
    private Contrato contrato;

    // Constructors
    public ContratoDistribuicaoModel() {}

    // ObjectId needs to be converted to string
    public String get_id() { return id.toHexString(); }

    public void set_id(ObjectId id) { this.id = id; }

    public void setContrato (Contrato contrato){
        this.contrato = contrato;
    }

    public Contrato getContrato (){
        return this.contrato;
    }
}
