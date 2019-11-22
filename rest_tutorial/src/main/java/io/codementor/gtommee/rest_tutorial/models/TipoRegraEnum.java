package io.codementor.gtommee.rest_tutorial.models;

public enum TipoRegraEnum {
    REGRA_DISTRIBUICAO(1, "Regra de Distribuição"),
    REGRA_RESTRICAO(2, "Regra de Restrição"),
    REGRA_CAMPANHA(3, "Regra de Campanha"),
    REGRA_COMPOSICAO(4, "Regra de Composição"),
    REGRA_ACAO_COBRANCA(5, "Regra de Ação de Cobrança");

    private Integer codigo;

    private String descricao;

    /**
     * @param codigo
     * @param descricao
     */
    TipoRegraEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param codigo
     * @return
     */
    public static TipoRegraEnum getTipoRegraEnum(Integer codigo) {
        for (TipoRegraEnum tipo : TipoRegraEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }

}
