package io.codementor.gtommee.rest_tutorial.models;

        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;

public class RegraCampanha extends Regra {

//    private static final long serialVersionUID = 1L;

    /**
     * Construtor da classe.
     */
    public RegraCampanha() {
        super(TipoRegraEnum.REGRA_CAMPANHA);
    }

    private String motivoExclusao;

    private Date inicioRecebimentoPagamento;

    private Date fimRecebimentoPagamento;

    private Double remuneracao;

    private Double remuneracaoDemaisCanais;

    private Double meta;

    private String situacaoCampanha;

    private static final String FORMATO_RECEBIMENTO_PAGAMENTO = "yyyyMMdd";

//    @Override
//    public Boolean avaliaRegra(ContratoDistribuicaoModel contratoDistribuicao, AmbienteDistribuicaoContrato ambienteDistribuicao) {
//        return contratoDistribuicao.recuperaRegraEnquadrada(TipoRegraEnum.REGRA_DISTRIBUICAO).size() > 0
//                && contratoDistribuicao.recuperaRegraEnquadrada(TipoRegraEnum.REGRA_CAMPANHA).size() <= 0
//                && super.avaliaRegra(contratoDistribuicao, ambienteDistribuicao);
//    }

    @Override
    public Boolean isVigente() {
        return isVigente(inicioRecebimentoPagamento, fimRecebimentoPagamento);
    }

    /**
     * Metodo que verifica se as datas passadas como parametro sao vigentes.
     *
     * @param inicio
     * @param fim
     * @return
     */
    public Boolean isVigente(Date inicio, Date fim) {
        if (inicio != null && fim != null) {
            return DataVigenciaUtil.isDataInformaEntreDatasInicioEFim(new Date(), inicio, fim);
        }
        return false;
    }

    @Override
    void verificarSeInicioInferiorAoFim() {
        if (getInicioVigencia() != null && getFimVigencia() != null
                && !DataVigenciaUtil.isDatasIguaisSemMilissegundo(getInicioVigencia(), getFimVigencia())
                && !DataVigenciaUtil.isDataInicioInferiorDataFim(getInicioVigencia(), getFimVigencia())) {
            throw new VigenciaInvalidaException("excecao.fim.antes.inicio.data.limite.campanha.distribuicao");
        }

        if (inicioRecebimentoPagamento != null && fimRecebimentoPagamento != null
                && !DataVigenciaUtil.isDataInicioInferiorDataFim(inicioRecebimentoPagamento, fimRecebimentoPagamento)) {
            throw new VigenciaInvalidaException("excecao.fim.antes.inicio.data.limite.campanha.pagamento");
        }
    };

    /**
     * Metodo que verifica se a data inicio de distribuicao e' menor que a data
     * inicio de pagamento.
     *
     * @return
     */
    private Boolean verificarInicioDistribuicaoMenorIgualInicioDataPagamento() {
        if (DataVigenciaUtil.isDataInicioInferiorDataFim(getInicioRecebimentoPagamento(), getInicioVigencia())) {
            throw new DataInicioDistribuicaoMaiorDataInicioPagamentoException();
        }
        return true;
    }

    /**
     * Metodo que verifica se o fim da data de distribuicao deve e' menor que
     * adata fim de pagamento.
     *
     * @return
     */
    private Boolean verificarFimDistribuicaoMenorIgualFimDataPagamento() {
        if (DataVigenciaUtil.isDataInicioInferiorDataFim(getFimRecebimentoPagamento(), getFimVigencia())) {
            throw new DataFimDistribuicaoMaiorDataFimPagamentoException();
        }
        return true;
    }

    private boolean verificarDataIniciofimDistribuicaoSaoIguais() {
        if (DataVigenciaUtil.isDatasIguaisSemMilissegundo(getInicioVigencia(), getFimVigencia())) {
            throw new DataInicioFimDistribuicaoIguaisException();
        }
        return true;
    }

    /**
     * Retorna a meta da campanha.
     * @return
     */
    public Double recuperaValorMeta() {
        String retorno = "";
        for (AcaoRegra acao : this.getAcoesRegra()) {
            if (AtributoSistemaEnum.META.equals(acao.getAtributo().getAtributoSistema())) {
                retorno = acao.getValor();
            }
        }
        return Double.valueOf(retorno);
    }

    @Override
    public Boolean isDatasValidas() {

        Boolean retorno = super.isDatasValidas();

        retorno = retorno && verificarDataIniciofimDistribuicaoSaoIguais();
        retorno = retorno && verificarInicioDistribuicaoMenorIgualInicioDataPagamento();
        retorno = retorno && inicioFimDiff5dias();
        retorno = retorno && validarRetroatividade();
        retorno = retorno && verificarFimDistribuicaoMenorIgualFimDataPagamento();
        retorno = retorno && isDatasEstaMesInteiro();

//        if (getId() != null) {
            if (!dataPagamentoFutura()) {
                throw new DataFimRetroativaAoEditarCampanhaException();
            }
            retorno = retorno && dataPagamentoFutura();
//        }

        return retorno;
    }

    /**
     * Verifica se a data final de recebimento do pagamento é no futuro.
     *
     * @return
     */
    public boolean dataPagamentoFutura() {
        boolean retorno = false;
        if (inicioRecebimentoPagamento != null && fimRecebimentoPagamento != null) {
            if (DataVigenciaUtil.isDataInformadaEMaiorIgualDataAtual(fimRecebimentoPagamento)) {
                retorno = true;
            }
        }
        return retorno;
    }

    /**
     * Metodo que verifica se as datas são futuras.
     *
     * @return
     */
    public boolean isDatasFuturas() {
        return this.isDatasFuturas(inicioRecebimentoPagamento, fimRecebimentoPagamento);
    }

    /**
     * Metodo que verifica se as datas passadas como parametro sao futuras.
     *
     * @param inicio
     * @param fim
     * @return
     */
    public boolean isDatasFuturas(Date inicio, Date fim) {
        if (inicio != null && fim != null) {
            return DataVigenciaUtil.isDataInformadaEMaiorDataAtual(inicio) && DataVigenciaUtil.isDataInformadaEMaiorDataAtual(fim);
        } else {
            return false;
        }
    }

    /**
     * Metodo que verifica se as datas são retroativas.
     *
     * @return
     */
    public Boolean validarRetroatividade() {
        if (inicioRecebimentoPagamento != null && fimRecebimentoPagamento != null) {

            if (DataVigenciaUtil.isDataInformadaEMaiorDataAtual(inicioRecebimentoPagamento)
                    || DataVigenciaUtil.isDataInformadaEMaiorDataAtual(fimRecebimentoPagamento)) {
                return true;
            }

            int limiteMes = 3;

            Calendar dataAtualCalendar = GregorianCalendar.getInstance();
            dataAtualCalendar.setTime(new Date());

            int mesAtual = DateUtil.getMesDaData(dataAtualCalendar.getTime()) - 1;
            int mesDataRetroativa = DateUtil.subtrairMeses(mesAtual, limiteMes) - 1;
            int anoDataRetroativa = dataAtualCalendar.get(Calendar.YEAR);

            if (mesDataRetroativa > mesAtual) {
                anoDataRetroativa = dataAtualCalendar.get(Calendar.YEAR) - 1;
            }

            boolean temExcessao = false;

            int mesDataInicioRecebimento = DateUtil.getMesDaData(inicioRecebimentoPagamento) - 1;
            int mesDataFimRecebimento = DateUtil.getMesDaData(fimRecebimentoPagamento) - 1;

            if (dataAtualCalendar.get(Calendar.YEAR) == anoDataRetroativa) {
                temExcessao = mesDataRetroativa >= mesDataInicioRecebimento && mesDataRetroativa >= mesDataFimRecebimento;
            } else {
                Calendar dataRetroativaCalendar = GregorianCalendar.getInstance();
                dataRetroativaCalendar.set(Calendar.YEAR, anoDataRetroativa);
                dataRetroativaCalendar.set(Calendar.MONTH, mesDataRetroativa);

                dataRetroativaCalendar.set(anoDataRetroativa, mesDataRetroativa,
                        DateUtil.getUltimoDiaMes(dataRetroativaCalendar.getTime()));

                temExcessao =
                        DataVigenciaUtil.isDataInicioInferiorDataFim(inicioRecebimentoPagamento, dataRetroativaCalendar.getTime())
                                || mesDataInicioRecebimento == mesDataRetroativa;

                temExcessao =
                        temExcessao
                                && (DataVigenciaUtil.isDataInicioInferiorDataFim(fimRecebimentoPagamento, dataRetroativaCalendar.getTime())
                                || mesDataFimRecebimento == mesDataRetroativa);
            }

            if (temExcessao) {
                throw new DatasRetroativaCampanhaException();
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Metodo que verifica se as datas de inicio e fim de recebimento de
     * pagemento tem uma diferenca menor que 5 dias.
     *
     * @return
     */
    public Boolean inicioFimDiff5dias() {
        if (inicioRecebimentoPagamento != null && fimRecebimentoPagamento != null) {
            int dif = DateUtil.dateDiffDays(fimRecebimentoPagamento, inicioRecebimentoPagamento);
            dif = dif < 0 ? dif * -1 : dif;

            if (dif < 4) {
                throw new DiferencaFimInicioMenor5diasException();
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Metodo que verifica se as datas estão dentro de um unico mes.
     *
     * @return
     */
    public Boolean isDatasEstaMesInteiro() {
        if (inicioRecebimentoPagamento != null && fimRecebimentoPagamento != null) {
            if (DateUtil.isDatasSaoMesesOuAnoDiferentes(inicioRecebimentoPagamento, fimRecebimentoPagamento)) {
                throw new DatasEmMesesDiferentesException();
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean necessitaRedistribuicao() {
        if (this.getFimVigencia() != null && getInicioVigencia() != null) {

            // DATA INICIO MAIOR QUE O DIA ATUAL PARA UMA INSERCAO.
            if (getDataInicioAnterior() == null && DataVigenciaUtil.isDataInformadaEMaiorIgualDataAtual(getInicioVigencia())) {
                return false;
            }

            // AMBAS AS DATAS SAO FUTURAS EM UMA EDICAO.
            if (getDataInicioAnterior() != null && getDataFimAnterior() != null
                    && DataVigenciaUtil.isDataInformadaEMaiorDataAtual(getDataInicioAnterior())
                    && DataVigenciaUtil.isDataInformadaEMaiorDataAtual(getInicioVigencia())
                    && DataVigenciaUtil.isDataInformadaEMaiorDataAtual(getDataFimAnterior())
                    && DataVigenciaUtil.isDataInformadaEMaiorDataAtual(getFimVigencia())) {
                return false;
            }

            Date dataAte = null;

            if (getDataFimAnterior() != null) {
                dataAte = getDataFimAnterior();
            } else {
                dataAte = DateUtil.adicionaDias(getFimVigencia(), -30);
            }

            return DateUtil.ultimoMilissegundoDeData(getFimVigencia()).after(DateUtil.ultimoMilissegundoDeData(dataAte));
        }
        return super.necessitaRedistribuicao();
    }

    /**
     *
     * @return
     */
    public Date getInicioRecebimentoPagamento() {
        return inicioRecebimentoPagamento != null ? new Date(inicioRecebimentoPagamento.getTime()) : null;
    }

    public Boolean isInicioRecebimentoFuturo() {
        return getInicioRecebimentoPagamento().compareTo(new Date()) > 0;
    }

    /**
     *
     * @return
     */
    public Date getInicioRecebimentoPagamentoDeAcaoRegra() {
//        return DateUtil.converteStringParaDate(
//                this.recuperarAtributoAcaoRegra(AtributoSistemaEnum.DATA_INICIO_RECEBIMENTO_PAGAMENTO.getAtributo()),
//                FORMATO_RECEBIMENTO_PAGAMENTO, I18nUtil.getLocale(I18nUtil.PT_BR));
        return DateUtil.converteStringParaDate(
                this.recuperarAtributoAcaoRegra(AtributoSistemaEnum.DATA_INICIO_RECEBIMENTO_PAGAMENTO.getAtributo())
                );
    }

    public Date getFimRecebimentoPagamentoDeAcaoRegra() {
//        return DateUtil.converteStringParaDate(
//                this.recuperarAtributoAcaoRegra(AtributoSistemaEnum.DATA_FIM_RECEBIMENTO_PAGAMENTO.getAtributo()),
//                FORMATO_RECEBIMENTO_PAGAMENTO, I18nUtil.getLocale(I18nUtil.PT_BR));
        return DateUtil.converteStringParaDate(
                this.recuperarAtributoAcaoRegra(AtributoSistemaEnum.DATA_FIM_RECEBIMENTO_PAGAMENTO.getAtributo())
                );
    }

    /**
     *
     * @param inicioRecebimentoPagamento
     */
    public void setInicioRecebimentoPagamento(Date inicioRecebimentoPagamento) {
        this.inicioRecebimentoPagamento = inicioRecebimentoPagamento != null ? new Date(inicioRecebimentoPagamento.getTime()) : null;
    }

    public Date getFimRecebimentoPagamento() {
        return fimRecebimentoPagamento != null ? new Date(fimRecebimentoPagamento.getTime()) : null;
    }

    /**
     *
     * @param fimRecebimentoPagamento
     */
    public void setFimRecebimentoPagamento(Date fimRecebimentoPagamento) {
        this.fimRecebimentoPagamento = fimRecebimentoPagamento != null ? new Date(fimRecebimentoPagamento.getTime()) : null;
    }

    public String getMotivoExclusao() {
        return motivoExclusao;
    }

    public void setMotivoExclusao(String motivoExclusao) {
        this.motivoExclusao = motivoExclusao;
    }

    public String getSituacaoCampanha() {
        return isAtivo() ? "Ativo" : "Cancelado";
    }

    public void setSituacaoCampanha(String situacaoCampanha) {
        this.situacaoCampanha = situacaoCampanha;
    };

    public Double getRemuneracaoDemaisCanais() {
        return remuneracaoDemaisCanais;
    }

    public void setRemuneracaoDemaisCanais(Double remuneracaoDemaisCanais) {
        this.remuneracaoDemaisCanais = remuneracaoDemaisCanais;
    }

    @Override
    public Boolean isCriteriosEditavel() {
        return false;
    }

    public Double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

}
