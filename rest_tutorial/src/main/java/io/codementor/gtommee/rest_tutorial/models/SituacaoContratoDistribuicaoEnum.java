package io.codementor.gtommee.rest_tutorial.models;

public enum SituacaoContratoDistribuicaoEnum {

    NAO_ENQUADRADO(0, "Não Enquadrado", "Nao Enquadrado"),
    EM_COBRANCA(1, "Em Cobrança", "Em Cobranca"),
    NAO_DISTRIBUIDO(2, "Não Distribuido", "Nao Distribuido"),
    ENCERRADO(3, "Encerrado", "Encerrado"),
    EM_ACOMPANHAMENTO(4, "Em Acompanhamento", "Em Acompanhamento"),
    ENQUADRADO_MULTIPLOS_CONVENIOS(5, "Enquadrado em Múltiplos convênios.", "Enquadrado em Multiplos convenios"),
    ENQUADRADO_MULTIPLOS_CONVENIOS_DIF_TIPOS_DISTRIBUICAO(6, "Enquadrado em Múltiplos convênios com diferentes tipos de distribuição",
            "Multiplos convenios com diferentes tipos de distribuicao.");

    private Integer codigo;

    private String descricao;

    private String descricaoMainFrame;

    /**
     * @param codigo
     * @param descricao
     */
    SituacaoContratoDistribuicaoEnum(Integer codigo, String descricao, String descMainFrame) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.descricaoMainFrame = descMainFrame;
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

    public String getDescricaoMainFrame() {
        return descricaoMainFrame;
    }

    /**
     * @param codigo
     * @return
     */
    public static SituacaoContratoDistribuicaoEnum getSituacaoContratoDistribuicaoEnum(Integer codigo) {
        for (SituacaoContratoDistribuicaoEnum situacao : SituacaoContratoDistribuicaoEnum.values()) {
            if (situacao.getCodigo().equals(codigo)) {
                return situacao;
            }
        }
        return null;
    }

    public static SituacaoContratoDistribuicaoEnum getSituacaoContratoDistribuicaoEnum(String descricao) {
        for (SituacaoContratoDistribuicaoEnum situacao : SituacaoContratoDistribuicaoEnum.values()) {
            if (situacao.getDescricao().equals(descricao)) {
                return situacao;
            }
        }
        return null;
    }

    /**
     * @param tipoRegra
     * @return
     */
    public static SituacaoContratoDistribuicaoEnum getSituacaoContratoDistribuicaoEnum(TipoRegraEnum tipoRegra) {
        if (TipoRegraEnum.REGRA_RESTRICAO.equals(tipoRegra)) {
            return SituacaoContratoDistribuicaoEnum.NAO_DISTRIBUIDO;
        } else {
            return SituacaoContratoDistribuicaoEnum.EM_COBRANCA;
        }
    }

    public boolean isEncerrado() {
        return this.equals(SituacaoContratoDistribuicaoEnum.ENCERRADO);
    }

}
