package io.codementor.gtommee.rest_tutorial.models;

public interface CondicaoRegra {

    Boolean avaliarCondicao(AmbienteAvaliacaoRegra ambiente) ;

    OperadorLogico getOperadorLogico();

    void setOrdem(Integer ordem);

}
