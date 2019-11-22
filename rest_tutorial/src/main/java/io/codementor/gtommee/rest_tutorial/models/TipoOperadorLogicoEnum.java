package io.codementor.gtommee.rest_tutorial.models;

public enum TipoOperadorLogicoEnum {

    E("E", "Conjunção lógica", new OperadorLogicoE()),
    OU("OU", "Disjunção lógica", new OperadorLogicoOU());

    private String operador;

    private String descricao;

    private OperadorLogico operadorLogico;

    /**
     * @param operador
     * @param descricao
     */
    TipoOperadorLogicoEnum(String operador, String descricao, OperadorLogico operadorLogico) {
        this.operador = operador;
        this.descricao = descricao;
        this.operadorLogico = operadorLogico;
    }

    /**
     * @return the operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the operadorLogico
     */
    public OperadorLogico getOperadorLogico() {
        return operadorLogico;
    }

    /**
     * @param operador
     * @return
     */
    public static TipoOperadorLogicoEnum getTipoOperadorCondicaoEnum(String operador) {
        if (operador != null) {
            for (TipoOperadorLogicoEnum tipo : TipoOperadorLogicoEnum.values()) {
                if (tipo.getOperador().equals(operador.trim())) {
                    return tipo;
                }
            }
        }
        return null;
    }

}
