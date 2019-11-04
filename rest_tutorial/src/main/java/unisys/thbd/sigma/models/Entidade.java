package unisys.thbd.sigma.models;

import java.io.Serializable;

public abstract class Entidade implements Serializable {

//    private static final long serialVersionUID = 1L;

//    public abstract Serializable getId();

    @Override
    public String toString() {
//        return this.getClass().getName() + " : id = " + this.getId();
        return this.getClass().getName();
    }

}