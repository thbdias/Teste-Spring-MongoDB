package unisys.thbd.sigma.models;

        import java.util.Date;
        import java.util.List;

public class Pagamento extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

    private ContratoDistribuicaoModel contratoDistribuicao;

    private Date dataProcessamento;

    private Date dataPagamento;

    private Date dataRemessa;

    private String codigoPagamento;

    private Double valorPagamento;

    private String matriculaUsuarioSolicitante;

    private Long idExecucaoPagamento;

    private Long idExecucaoApuracao;

    private Regra regraDistribuicao;

    private RegraCampanha regraCampanha;

    private Date dataApuracaoRemuneracao;

    private String mesAnoReferenciaRemuneracao;

    private Double percentualRemuneracao;

    private Double valorPagamentoCanal;

    private Double valorRemuneracaoCanal;

    private Double valorPagamentoOutroCanal;

    private Double valorRemuneracaoOutroCanal;

    private Double percentualRemuneracaoDemaisCanais;

    private Double valorRemuneracaoCampanha;

    private Double percentualRemuneracaoCampanha;

    private Double valorPagamentoCampanha;

    private Date dataApuracaoRemuneracaoCampanha;

    private List<Long> idContratoDistribuicao;

//    @Override
//    public Long getId() {
//        return id;
//    }


//    public void setId(Long id) {
//        this.id = id;
//    }


    public ContratoDistribuicaoModel getContratoDistribuicao() {
        return contratoDistribuicao;
    }

    /**
     * Atribuir o Contrato DistribuiÃ§Ã£o do vinculado ao pagamento.
     *
     * @param contratoDistribuicao
     *            the contratoDistribuicao to set
     */
    public void setContratoDistribuicao(ContratoDistribuicaoModel contratoDistribuicao) {
        this.contratoDistribuicao = contratoDistribuicao;
    }

    /**
     * Obter a data de processamento.
     *
     * @return the dataProcessamento
     */
    public Date getDataProcessamento() {
        return dataProcessamento != null ? new Date(dataProcessamento.getTime()) : null;
    }

    /**
     * Atribuir a data de processamento.
     *
     * @param dataProcessamento
     *            the dataProcessamento to set
     */
    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento != null ? new Date(dataProcessamento.getTime()) : null;
    }

    /**
     * Atribuir a data de pagamento.
     *
     * @return the dataPagamento
     */
    public Date getDataPagamento() {
        return dataPagamento != null ? new Date(dataPagamento.getTime()) : null;
    }

    /**
     * Atribuir a data de pagamento.
     *
     * @param dataPagamento
     *            the dataPagamento to set
     */
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento != null ? new Date(dataPagamento.getTime()) : null;
    }

    /**
     * Obter a data de remessa.
     *
     * @return the dataRemessa
     */
    public Date getDataRemessa() {
        return dataRemessa != null ? new Date(dataRemessa.getTime()) : null;
    }

    /**
     * Atribuir a data de remessa.
     *
     * @param dataRemessa
     *            the dataRemessa to set
     */
    public void setDataRemessa(Date dataRemessa) {
        this.dataRemessa = dataRemessa != null ? new Date(dataRemessa.getTime()) : null;
    }

    /**
     * Obter o codigo de pagamento.
     *
     * @return the codigoPagamento
     */
    public CodigoPagamentoEnum getCodigoPagamento() {
        if (this.codigoPagamento == null) {
            return null;
        }
        return CodigoPagamentoEnum.getByCodigoPagamento(this.codigoPagamento);
    }

    /**
     * Atribuir o codigo de pagamento.
     *
     * @param codigoPagamento
     *            the codigoPagamento to set
     */
    public void setCodigoPagamento(CodigoPagamentoEnum codigoPagamento) {
        if (codigoPagamento == null) {
            this.codigoPagamento = null;
        } else {
            this.codigoPagamento = codigoPagamento.getCodigo();
        }
    }

    /**
     * Obter o o valor do pagamento.
     *
     * @return the valorPagamento
     */
    public Double getValorPagamento() {
        return valorPagamento;
    }

    /**
     * Atribuir o o valor do pagamento.
     *
     * @param valorPagamento
     *            the valorPagamento to set
     */
    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    /**
     * Obter a matricula do usuÃ¡rio so solicitante.
     *
     * @return the usuarioResponsavel
     */
    public String getMatriculaUsuarioSolicitante() {
        return matriculaUsuarioSolicitante;
    }

    /**
     * Atribuir a matricula do usuÃ¡rio so solicitante.
     *
     * @param matriculaUsuarioSolicitante
     *            the usuarioResponsavel to set
     */
    public void setMatriculaUsuarioSolicitante(String matriculaUsuarioSolicitante) {
        this.matriculaUsuarioSolicitante = matriculaUsuarioSolicitante;
    }

    /**
     * Obter o identificador da execuÃ§Ã£o pagamento.
     *
     * @return the execucaoSelecao
     */
    public Long getIdExecucaoPagamento() {
        return idExecucaoPagamento;
    }

    /**
     * Atribuir o identificador da execuÃ§Ã£o pagamento.
     *
     * @param idExecucaoPagamento
     *            the execucaoSelecao to set
     */
    public void setIdExecucaoPagamento(Long idExecucaoPagamento) {
        this.idExecucaoPagamento = idExecucaoPagamento;
    }

    /**
     * Obter o identificador da execuÃ§Ã£o ApuraÃ§Ã£o.
     *
     * @return the execucaoDistribuicao
     */
    public Long getIdExecucaoApuracao() {
        return idExecucaoApuracao;
    }

    /**
     * Atribuir o identificador da execuÃ§Ã£o ApuraÃ§Ã£o.
     *
     * @param idExecucaoApuracao
     *            the execucaoDistribuicao to set
     */
    public void setIdExecucaoApuracao(Long idExecucaoApuracao) {
        this.idExecucaoApuracao = idExecucaoApuracao;
    }

    /**
     * @return
     */
    public Regra getRegraDistribuicao() {
        return regraDistribuicao;
    }

    /**
     * @param regraDistribuicao
     */
    public void setRegraDistribuicao(Regra regraDistribuicao) {
        this.regraDistribuicao = regraDistribuicao;
    }

    /**
     * @return
     */
    public Regra getRegraCampanha() {
        return regraCampanha;
    }

    /**
     * @param regraCampanha
     */
    public void setRegraCampanha(RegraCampanha regraCampanha) {
        this.regraCampanha = regraCampanha;
    }

    /**
     * @return
     */
    public Date getDataApuracaoRemuneracao() {
        return DateUtil.duplicaObjetoData(dataApuracaoRemuneracao);
    }

    /**
     * @param dataApuracaoRemuneracao
     */
    public void setDataApuracaoRemuneracao(Date dataApuracaoRemuneracao) {
        this.dataApuracaoRemuneracao = DateUtil.duplicaObjetoData(dataApuracaoRemuneracao);
    }

    /**
     * @return
     */
    public String getMesAnoReferenciaRemuneracao() {
        return mesAnoReferenciaRemuneracao;
    }

    /**
     * @param mesAnoReferenciaRemuneracao
     */
    public void setMesAnoReferenciaRemuneracao(String mesAnoReferenciaRemuneracao) {
        this.mesAnoReferenciaRemuneracao = mesAnoReferenciaRemuneracao;
    }

    /**
     * @return
     */
    public Double getPercentualRemuneracao() {
        return percentualRemuneracao;
    }

    /**
     * @param percentualRemuneracao
     */
    public void setPercentualRemuneracao(Double percentualRemuneracao) {
        this.percentualRemuneracao = percentualRemuneracao;
    }

    /**
     * @return
     */
    public Double getValorPagamentoCanal() {
        return valorPagamentoCanal;
    }

    /**
     * @param valorPagamentoCanal
     */
    public void setValorPagamentoCanal(Double valorPagamentoCanal) {
        this.valorPagamentoCanal = valorPagamentoCanal;
    }

    /**
     * @return
     */
    public Double getValorRemuneracaoCanal() {
        return valorRemuneracaoCanal;
    }

    /**
     * @param valorRemuneracaoCanal
     */
    public void setValorRemuneracaoCanal(Double valorRemuneracaoCanal) {
        this.valorRemuneracaoCanal = valorRemuneracaoCanal;
    }

    /**
     * @return
     */
    public Double getValorPagamentoOutroCanal() {
        return valorPagamentoOutroCanal;
    }

    /**
     * @param valorPagamentoOutroCanal
     */
    public void setValorPagamentoOutroCanal(Double valorPagamentoOutroCanal) {
        this.valorPagamentoOutroCanal = valorPagamentoOutroCanal;
    }

    /**
     * @return
     */
    public Double getValorRemuneracaoOutroCanal() {
        return valorRemuneracaoOutroCanal;
    }

    /**
     * @param valorRemuneracaoOutroCanal
     */
    public void setValorRemuneracaoOutroCanal(Double valorRemuneracaoOutroCanal) {
        this.valorRemuneracaoOutroCanal = valorRemuneracaoOutroCanal;
    }

    public Double getPercentualRemuneracaoDemaisCanais() {
        return percentualRemuneracaoDemaisCanais;
    }

    public void setPercentualRemuneracaoDemaisCanais(Double percentualRemuneracaoDemaisCanais) {
        this.percentualRemuneracaoDemaisCanais = percentualRemuneracaoDemaisCanais;
    }

    /**
     * @return the valorRemuneracaoCampanha
     */
    public Double getValorRemuneracaoCampanha() {
        return valorRemuneracaoCampanha;
    }

    /**
     * @param valorRemuneracaoCampanha
     *            the valorRemuneracaoCampanha to set
     */
    public void setValorRemuneracaoCampanha(Double valorRemuneracaoCampanha) {
        this.valorRemuneracaoCampanha = valorRemuneracaoCampanha;
    }

    /**
     * @return the percentualRemuneracaoCampanha
     */
    public Double getPercentualRemuneracaoCampanha() {
        return percentualRemuneracaoCampanha;
    }

    /**
     * @param percentualRemuneracaoCampanha
     *            the percentualRemuneracaoCampanha to set
     */
    public void setPercentualRemuneracaoCampanha(Double percentualRemuneracaoCampanha) {
        this.percentualRemuneracaoCampanha = percentualRemuneracaoCampanha;
    }

    /**
     * @return the valorPagamentoCampanha
     */
    public Double getValorPagamentoCampanha() {
        return valorPagamentoCampanha;
    }

    /**
     * @param valorPagamentoCampanha
     *            the valorPagamentoCampanha to set
     */
    public void setValorPagamentoCampanha(Double valorPagamentoCampanha) {
        this.valorPagamentoCampanha = valorPagamentoCampanha;
    }

    /**
     * @return the dataApuracaoRemuneracaoCampanha
     */
    public Date getDataApuracaoRemuneracaoCampanha() {
        return DateUtil.duplicaObjetoData(dataApuracaoRemuneracaoCampanha);
    }

    /**
     * @param dataApuracaoRemuneracaoCampanha
     *            the dataApuracaoRemuneracaoCampanha to set
     */
    public void setDataApuracaoRemuneracaoCampanha(Date dataApuracaoRemuneracaoCampanha) {
        this.dataApuracaoRemuneracaoCampanha = DateUtil.duplicaObjetoData(dataApuracaoRemuneracaoCampanha);
    }

    public List<Long> getIdContratoDistribuicao() {
        return idContratoDistribuicao;
    }

    public void setIdContratoDistribuicao(List<Long> idContratoDistribuicao) {
        this.idContratoDistribuicao = idContratoDistribuicao;
    }

    public void setCodigoPagamento(String codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

}
