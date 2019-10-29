package unisys.thbd.sigma.models;

public interface CondicaoRegra {

    Boolean avaliarCondicao(AmbienteAvaliacaoRegra ambiente) ;

    OperadorLogico getOperadorLogico();

    void setOrdem(Integer ordem);

}
