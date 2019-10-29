package unisys.thbd.sigma.models;

public class OperadorLogicoOU implements OperadorLogico {

    @Override
    public Boolean avaliarCondicao(Boolean valor1, Boolean valor2) {
        return valor1 || valor2;
    }

}
