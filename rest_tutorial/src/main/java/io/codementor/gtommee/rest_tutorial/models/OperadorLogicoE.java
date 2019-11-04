package io.codementor.gtommee.rest_tutorial.models;

public class OperadorLogicoE implements OperadorLogico {

    @Override
    public Boolean avaliarCondicao(Boolean valor1, Boolean valor2) {
        return valor1 && valor2;
    }

}
