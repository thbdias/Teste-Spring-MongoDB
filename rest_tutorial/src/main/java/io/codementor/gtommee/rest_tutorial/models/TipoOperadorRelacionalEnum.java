package io.codementor.gtommee.rest_tutorial.models;


public enum TipoOperadorRelacionalEnum {
    IGUAL("EQ", "=", "Igual", new OperadorRelacionalIgual()), // IG
    DIFERENTE("DIF", "≠", "Diferente", new OperadorRelacionalDiferente()), // DIF
    MAIOR("GT", ">", "Maior que", new OperadorRelacionalMaiorQue()), // MQ
    MAIOR_IGUAL("GEQ", ">=", "Maior ou igual que", new OperadorRelacionalMaiorOuIgual()), //MIQ
    MENOR("LT", "<", "Menor que", new OperadorRelacionalMenorQue()),
    MENOR_IGUAL("LEQ", "<=", "Menor ou igual que", new OperadorRelacionalMenorOuIgual());

    private String simbolo;

    private String operador;

    private String descricao;

    private OperadorRelacional operadorRelacional;

    /**
     * Construtor.
     *
     * @param operador
     * @param descricao
     */
    TipoOperadorRelacionalEnum(String simbolo, String operador, String descricao, OperadorRelacional operadorRelacional) {
        this.simbolo = simbolo;
        this.operador = operador;
        this.descricao = descricao;
        this.operadorRelacional = operadorRelacional;
    }

    /**
     * Obter o operador.
     * @return the operador
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Obter a descrição.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the operadorRelacional
     */
    public OperadorRelacional getOperadorRelacional() {
        return operadorRelacional;
    }

    /**
     * Obter o Tipo Operador pelo operador.
     *
     * @param operador
     * @return
     */
    public static TipoOperadorRelacionalEnum getTipoOperadorRelacionalEnum(String operador) {
        if (operador != null) {
            for (TipoOperadorRelacionalEnum tipo : TipoOperadorRelacionalEnum.values()) {
                if (tipo.simbolo.equals(operador.trim())) {
                    return tipo;
                }
            }
        }
        return null;
    }

    /**
     * @return the simbolo
     */
    public String getSimbolo() {
        return simbolo;
    }

}
