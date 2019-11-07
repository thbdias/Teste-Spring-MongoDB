package io.codementor.gtommee.rest_tutorial.models;

import java.util.List;

public class Contratos {

    private List<Contrato> listContrato;

    public Contratos(){}

    public void setListContrato (List<Contrato> listContrato){
        this.listContrato = listContrato;
    }

    public List<Contrato> getListContrato (){
        return this.listContrato;
    }
}
