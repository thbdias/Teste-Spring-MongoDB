package unisys.thbd.sigma.models;

public enum DadosContratoEnum {
    NOME("nome"),
    DDD_RESIDENCIAL("dddRes"),
    ADMINISTRADOR("adm"),
    CONTRATO("contrato"),
    CREDOR("credor"),
    CPF("cpf"),
    UF("uf"),
    AGENCIA_CONTRATO("uno"),
    CPF_COOBRIGADO("cpfC"),
    DATA_ASSINATURA("dtAss"),
    ETAPA("etapa"),
    ORIGEM_RECUROS("orr"),
    SITUACAO("situacao"),
    DDD_TELEFONE_RESIDENCIAL("dddRes"),
    TELEFONE_RESIDENCIAL("telRes"),
    DDD_TELEFONE_CELULAR("dddCel"),
    TELEFONE_CELULAR("telCel"),
    DDD_TELEFONE_COMERCIAL("dddCom"),
    TELEFONE_COMERCIAL("telCom"),
    BAIRRO_ENDERECO("baiEnd"),
    BAIRRO_CORRESPONDENCIA("baiCor"),
    TIPO_GARANTIA("tg"),
    CEP_ENDERECO("cepEnd"),
    CEP_CORRESPONDENCIA("cepCor"),
    COMPLEMENTO_ENDERECO("cplEnd"),
    COMPLEMENTO_CORRESPONDENCIA("cplCor"),
    COOBRIGADOS("coobrigados"),
    LOGRADOURO_ENDERECO("nomEnd"),
    LOGRADOURO_CORRESPONDENCIA("nomCor"),
    NUMERO_ENDERECO("nroEnd"),
    NUMERO_CORRESPONDENCIA("nroCor"),
    TIPO_LOGRADOURO_ENDERECO("abvEnd"),
    TIPO_LOGRADOURO_CORRESPONDENCIA("abvCor"),
    UF_ENDERECO("ufEnd"),
    UF_CORRESPONDENCIA("ufCor"),
    NOME_MUNICIPIO("cidEnd"),
    NOME_CORRESPONDENCIA("cidCor"),
    ACAO_COBRANCA("acaoCobranca"),
    QUANTIDADE_PRESTACAO_ATRASO("qtdPreAtraso"),
    VALOR_TOTAL_DIVIDA("vlrDiv"),
    DIAS_ATRASO("diasAtraso"),
    DIA_VENCIMENTO("diaVenc"),
    CRITERIO_CRITICA("crtCrit"),
    SITUACAO_ESPECIAL("ses"),
    FASE_PROCESSO_EXECUCAO("codFase"),
    NUMERO_PARCELAS_INCORPORADAS("nroParcInc");

    private String descricao;

    /**
     *
     * @param descricao
     */
    DadosContratoEnum(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the descricao
     */
    public String getCodigo() {
        return descricao;
    }

    /**
     *
     * @param desc
     * @return
     */
    public static DadosContratoEnum getDadosContratoEnum(String desc) {
        for (DadosContratoEnum dado : DadosContratoEnum.values()) {
            if (dado.descricao.equals(desc)) {
                return dado;
            }
        }
        return null;
    }
}
