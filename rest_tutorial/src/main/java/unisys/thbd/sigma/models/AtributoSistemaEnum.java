package unisys.thbd.sigma.models;

import java.util.ArrayList;
import java.util.List;

public enum AtributoSistemaEnum {

    CONTRATO(0, "contrato", "Contrato"),
    DIAS_ATRASO(1, "diasAtraso", "Dias em Atraso"),
    VALOR_DIVIDA(2, "valorDivida", "Valor da Divida"),
    CONVENIO(3, "convenio", "Convenio"),
    SITUACAO(4, "situacao", "Situação do Contrato"),
    REMUNERACAO(5, "remuneracao", "Remuneração"),
    META(6, "meta", "Meta"),
    CAMPANHA(7, "campanha", "Campanha"),
    ATRIBUTO_COMPOSTO(8, "atributoComposto", "Atributo Composto"),
    DATA_ATUAL(9, "dataAtual", "Data Atual"),
    ACAO_COBRANCA(10, "acaoCobranca", "Ação de Cobrança"),
    RESTRICAO(11, "restricao", "Regra de Restrição"),
    DISTRIBUICAO(12, "distribuicao", "Regra de Distribuição"),
    PRAZO_PERMANENCIA(13, "prazoPermanencia", "Prazo de Permanência"),
    DATA_INICIO_RECEBIMENTO_PAGAMENTO(14, "datIniRecPag", "Data Inicio de Recebimento de Pagamento"),
    DATA_FIM_RECEBIMENTO_PAGAMENTO(15, "datFimRecPag", "Data Fim de Recebimento de Pagamento"),
    REMUNERACAO_DEMAIS_CANAIS(16, "remuneracaoDemaisCanais", "Remuneração");

    private Integer codigo;
    private String atributo;
    private String descricao;

    AtributoSistemaEnum(Integer codigo, String atributo, String descricao) {
        this.codigo = codigo;
        this.atributo = atributo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getAtributo() {
        return atributo;
    }

    public String getDescricao() {
        return descricao;
    }


    public static AtributoSistemaEnum getAtributoSistemaEnum(Integer codigo) {

        if (codigo != null) {
            for (AtributoSistemaEnum atributoSistema : AtributoSistemaEnum.values()) {
                if (atributoSistema.getCodigo().equals(codigo)) {
                    return atributoSistema;
                }
            }
        }
        return null;
    }


    public static List<Integer> getCodigosEnumAsList() {

        List<Integer> list = new ArrayList<Integer>();

        for (AtributoSistemaEnum item : AtributoSistemaEnum.values()) {
            list.add(item.getCodigo());
        }

        return list;
    }
}
