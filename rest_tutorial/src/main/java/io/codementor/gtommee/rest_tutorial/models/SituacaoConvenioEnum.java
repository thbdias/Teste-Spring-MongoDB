package io.codementor.gtommee.rest_tutorial.models;

public enum SituacaoConvenioEnum {
    ATIVO(1, "Ativo"), VENCIDO(2, "Vencido"), SUSPENSO(3, "Suspenso"), ENCERRADO(4, "Encerrado");

    private Integer codigo;
    private String descricao;

    SituacaoConvenioEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * Metodo que recupera a situacao do convenio apartir da chave.
     *
     * @param valor
     * @return
     */
    public static SituacaoConvenioEnum fromValue(Integer valor) {
        if (valor != null) {
            for (SituacaoConvenioEnum situacao : values()) {
                if (situacao.codigo.equals(valor)) {
                    return situacao;
                }
            }
            throw new IllegalArgumentException("Situação invalida: " + valor);
        }

        return null;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo
     *            the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao
     *            the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ATIVO.equals(SituacaoConvenioEnum.fromValue(this.codigo));
    }
}
