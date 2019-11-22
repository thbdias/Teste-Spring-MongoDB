package io.codementor.gtommee.rest_tutorial.models;

public class DefaultDecorator implements Decorator<Object> {

    /**
     * Metodo que retorno <code>String</code> a partir de <code>Object</code>.
     */
    public String toString(Object field) {
        return field.toString();
    }


    /**
     * Metodo que retorno <code>Object</code> a partir de <code>String</code>.
     */
    public Object fromString(String field) {
        return field;
    }
}