package io.codementor.gtommee.rest_tutorial.models;

public class RegraDistribuicao extends Regra {

//    private static final long serialVersionUID = 1L;
    private Convenio convenio;
    private TipoDistribuicao tipoDistribuicao;

    public RegraDistribuicao() {
        super(TipoRegraEnum.REGRA_DISTRIBUICAO);
    }

//    @Override
//    public Boolean avaliaRegra(ContratoDistribuicaoModel contratoDistribuicao, AmbienteDistribuicaoContrato ambienteDistribuicao) {
//        if (contratoDistribuicao.recuperaRegraEnquadrada(TipoRegraEnum.REGRA_RESTRICAO).size() > 0) {
//            return Boolean.FALSE;
//        }
//        contratoDistribuicao.setDataDistribuicao(DateUtil.getDataAtual());
//        contratoDistribuicao.setSituacao(SituacaoContratoDistribuicaoEnum.EM_COBRANCA);
//        return super.avaliaRegra(contratoDistribuicao, ambienteDistribuicao);
//    }



    /**
     * @return the convenio
     */
    public Convenio getConvenio() {
        return convenio;
    }

    /**
     * @param convenio
     *            the convenio to set
     */
    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    /**
     * @return the tipoDistribuicao
     */
    public TipoDistribuicao getTipoDistribuicao() {
        return tipoDistribuicao;
    }

    /**
     * @param tipoDistribuicao
     *            the tipoDistribuicao to set
     */
    public void setTipoDistribuicao(TipoDistribuicao tipoDistribuicao) {
        this.tipoDistribuicao = tipoDistribuicao;
    }

    @Override
    public Boolean isDatasValidas() {
        super.isDatasValidas();
        verificarSeIntervaloDataInicioFimMaiorQue30Dias();
        verificarSeInicioVigenciaMenorDataAtual();
        return Boolean.TRUE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((convenio == null) ? 0 : convenio.hashCode());
        result = prime * result + ((tipoDistribuicao == null) ? 0 : tipoDistribuicao.hashCode());
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
        if (!(obj instanceof RegraDistribuicao)) {
            return false;
        }
        RegraDistribuicao other = (RegraDistribuicao) obj;
        if (convenio == null) {
            if (other.convenio != null) {
                return false;
            }
        } else if (!convenio.equals(other.convenio)) {
            return false;
        }
        if (tipoDistribuicao == null) {
            if (other.tipoDistribuicao != null) {
                return false;
            }
        } else if (!tipoDistribuicao.equals(other.tipoDistribuicao)) {
            return false;
        }
        return true;
    }
}
