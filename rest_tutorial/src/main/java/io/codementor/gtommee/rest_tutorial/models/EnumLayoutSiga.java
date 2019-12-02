package io.codementor.gtommee.rest_tutorial.models;

public enum EnumLayoutSiga {
    TAM_NUMERO_CONTRATO(12),
    TAM_SES(3), //tamanho máximo de caracteres de uma situacao especial
    QUANT_MAX_SES(10), //quantidade máxima de situações especiais que devem conter no layout SIGA
    TAM_MAX_NOME(36),
    TAM_CPF(14),
    TAM_MAX_DDD(4),
    TAM_MAX_TEL(9),
    TAM_MAX_CODIGO_FASE(20),
    TAM_MAX_UNIDADE_OPERACIONAL(5),
    TAM_MAX_DIA_VENCIMENTO(2),
    TAM_MAX_DATA_PRIM_PREST_ABERTA(6),
    TAM_MAX_VALOR_PREST_ATRASO_INT(14), //parte inteira
    TAM_MAX_VALOR_PREST_ATRASO_DEC(2), //parte decimal
    TAM_MAX_DIAS_ATRASO(3),
    TAM_MAX_QUANT_PREST_ATRASO(3),
    TAM_MAX_VALOR_DIVIDA_ATRASO_INT(14), //parte inteira
    TAM_MAX_VALOR_DIVIDA_ATRASO_DEC(2), //parte decimal
    TAM_MAX_REGENCIA_CRITICA(4),
    TAM_MAX_ORIGEM_RECURSO(3),
    TAM_MAX_LINHA_FINANCIAMENTO(3),
    TAM_MAX_TIPO_FINANCIAMENTO(3),
    TAM_MAX_TIPO_GARANTIA(4),
    TAM_MAX_INDICADOR_PMCMV(1),
    TAM_MAX_GRUPO_HABITACIONAL(5),
    TAM_MAX_PRODUTO_FINANCEIRO(4),
    TAM_MAX_CODIGO_CREDOR(4),
    TAM_MAX_CODIGO_ADMINISTRADOR(4),
    TAM_MAX_UF_CONTRATO(2),
    TAM_MAX_SUBTITULO_CONTABIL(6),
    TAM_MAX_INDICADOR_OBRA(1),
    TAM_MAX_INDICADOR_MATERIAL_CONSTRUCAO(1),
    TAM_MAX_PRAZO_REMANESCENTE(3),
    TAM_MAX_TIPO_ENDERECO(3),
    TAM_MAX_NOME_ENDERECO(50),
    TAM_MAX_NUMERO_ENDERECO(5),
    TAM_MAX_COMPLEMENTO_ENDERECO(11),
    TAM_MAX_BAIRRO(14),
    TAM_MAX_CIDADE(25),
    TAM_MAX_UF_MUTUARIO(2),
    TAM_MAX_CEP(8),
    TAM_MAX_EMAIL(60),
    TAM_MAX_VALOR_GARANTIA_INT(16), //parte inteira
    TAM_MAX_VALOR_GARANTIA_DEC(2); //parte decimal

    private final int valor;

    EnumLayoutSiga(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }
}
