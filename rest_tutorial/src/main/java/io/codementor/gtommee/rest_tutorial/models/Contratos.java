package io.codementor.gtommee.rest_tutorial.models;

import java.util.LinkedList;
import java.util.List;

public class Contratos {

    private List<Contrato> contratos;

    public Contratos(){
        contratos = new LinkedList<>();
    }

    public void setContratos (List<Contrato> contratos){
        this.contratos = contratos;
    }


    public List<Contrato> getContratos (){
        return this.contratos;
    }


    public void addContrato(Contrato contrato){
        contratos.add(contrato);
    }
}
