package io.codementor.gtommee.rest_tutorial.models;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class ContratoDistribuicaoRegra extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

//    private Long id;

    private ContratoDistribuicaoModel contratoDistribuicao;
    private Regra regra;
    private List<DadoContratoDistribuicao> dadosContratoDistribuicao;
    private Map<String, DadoContratoDistribuicao> mapDadosContratoDistribuicao = null;

    /**
     * @return the id
     */
//    @Override
//    public Long getId() {
//        return id;
//    }

    /**
     * @param id
     *            the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * @return the contratoDistribuicao
     */
    public ContratoDistribuicaoModel getContratoDistribuicao() {
        return contratoDistribuicao;
    }

    /**
     * Método precisa ser revisto. Suspeito!
     *
     * @param contratoDistribuicao
     *            the contratoDistribuicao to set
     */
    public void atribuiContratoDistribuicaoRegraPersistindo(ContratoDistribuicaoModel contratoDistribuicao) {
        if (this.contratoDistribuicao == null || !this.contratoDistribuicao.equals(contratoDistribuicao)) {
            this.contratoDistribuicao = contratoDistribuicao;
            if (this.contratoDistribuicao != null) {
                this.contratoDistribuicao.adicionaContratoDistribuicaoRegra(this);
            }
        }
    }

    /**
     *
     * @param contratoDistribuicao
     */
    public void setContratoDistribuicao(ContratoDistribuicaoModel contratoDistribuicao) {
        this.contratoDistribuicao = contratoDistribuicao;

    }

    /**
     * @return the regra
     */
    public Regra getRegra() {
        return regra;
    }

    /**
     * @param regra
     *            the regra to set
     */
    public void setRegra(Regra regra) {
        this.regra = regra;
    }

    /**
     * @return the dadosContratoDistribuicao
     */
    public List<DadoContratoDistribuicao> getDadosContratoDistribuicao() {
        this.iniciaDadosContratoDistribuicaoRegra();
        return Collections.unmodifiableList(this.dadosContratoDistribuicao);
    }

    /**
     * @param dadosContratoDistribuicao
     */
    public void setDadosContratoDistribuicao(List<DadoContratoDistribuicao> dadosContratoDistribuicao) {
        this.dadosContratoDistribuicao = null;
        this.iniciaDadosContratoDistribuicaoRegra();
        if (dadosContratoDistribuicao != null) {
            for (DadoContratoDistribuicao dadoContratoDistribuicao : dadosContratoDistribuicao) {
                this.adicionaDadoContratoDistribuicao(dadoContratoDistribuicao);
            }
        }
    }

    /**
     * Inicializa a lista de dados contrato distribuicao regra.
     */
    private void iniciaDadosContratoDistribuicaoRegra() {
        if (this.dadosContratoDistribuicao == null) {
            this.dadosContratoDistribuicao = new ArrayList<DadoContratoDistribuicao>();
        }
    }

    /**
     * @param dadoContratoDistribuicao
     */
    public void adicionaDadoContratoDistribuicao(DadoContratoDistribuicao dadoContratoDistribuicao) {
        this.iniciaDadosContratoDistribuicaoRegra();
        if (dadoContratoDistribuicao != null && !this.dadosContratoDistribuicao.contains(dadoContratoDistribuicao)) {
            this.dadosContratoDistribuicao.add(dadoContratoDistribuicao);
            dadoContratoDistribuicao.setContratoDistribuicaoRegra(this);
        }
    }

    /**
     * @param dadoContratoDistribuicao
     */
    public void removeDadoContratoDistribuicao(DadoContratoDistribuicao dadoContratoDistribuicao) {
        this.iniciaDadosContratoDistribuicaoRegra();
        if (dadoContratoDistribuicao != null && this.dadosContratoDistribuicao.remove(dadoContratoDistribuicao)) {
            dadoContratoDistribuicao.setContratoDistribuicaoRegra(null);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((contratoDistribuicao == null) ? 0 : contratoDistribuicao.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((regra == null) ? 0 : regra.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ContratoDistribuicaoRegra)) {
            return false;
        }
        ContratoDistribuicaoRegra other = (ContratoDistribuicaoRegra) obj;
        if (contratoDistribuicao == null) {
            if (other.contratoDistribuicao != null) {
                return false;
            }
        } else if (!contratoDistribuicao.equals(other.contratoDistribuicao)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (regra == null) {
            if (other.regra != null) {
                return false;
            }
        } else if (!regra.equals(other.regra)) {
            return false;
        }
        return true;
    }

    /**
     * Recupera valor Atributo.
     *
     * @param codigoAtributo
     * @return
     */
    public DadoContratoDistribuicao recuperaDadoContratoDistribuicao(String codigoAtributo) {
        return getMapDadosContratoDistribuicao().get(codigoAtributo) != null ? getMapDadosContratoDistribuicao().get(codigoAtributo)
                : new DadoContratoDistribuicao();
    }

    /**
     * Retorna um map com os dados do contrato.
     *
     * @return
     */
    private Map<String, DadoContratoDistribuicao> getMapDadosContratoDistribuicao() {
        if (mapDadosContratoDistribuicao == null) {
            mapDadosContratoDistribuicao = new HashMap<String, DadoContratoDistribuicao>(dadosContratoDistribuicao.size());
            for (DadoContratoDistribuicao dcd : dadosContratoDistribuicao) {
                mapDadosContratoDistribuicao.put(dcd.getAtributo().getAtributo(), dcd);
            }
        }
        return mapDadosContratoDistribuicao;
    }

}
