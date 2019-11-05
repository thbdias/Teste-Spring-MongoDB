package io.codementor.gtommee.rest_tutorial.models;

import java.util.Date;

public class Convenio extends EntidadeAuditavelCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

    private static final int NUMERO_DIAS_MINIMO_CONVENIO = 30;
    private String nome;
    private Date inicioVigencia;
    private Date fimVigencia;
    private Integer situacaoConvenio;
    private TipoDistribuicao tipoDistribuicao;
    private Date dataSuspensao;
    private String descricaoSuspensao;
    private Date dataReativacao;
    private String descricaoReativacao;
    private Date dataEncerramento;
    private String descricaoEncerramento;
    private EmpresaTerceirizada empresaTerceirizada;
    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;
    private RegraDistribuicao regra;
    private String numeroEdital;
    private String codigoOrcamento;
    private String numeroContrato;
    private Long permanencia;
    private Double remuneracao;
    private Double remuneracaoDemaisCanais;
    private Double meta;
    private Boolean vigenteNoBanco = null;

    //contrutor
    public Convenio(){
        String nome = null;
        Date inicioVigencia = null;
        Date fimVigencia = null;
        Integer situacaoConvenio = null;
        TipoDistribuicao tipoDistribuicao = null;
        Date dataSuspensao = null;
        String descricaoSuspensao = null;
        Date dataReativacao = null;
        String descricaoReativacao = null;
        Date dataEncerramento = null;
        String descricaoEncerramento = null;
        EmpresaTerceirizada empresaTerceirizada = null;
        ClienteCobrancaTerceirizada clienteCobrancaTerceirizada = null;
        RegraDistribuicao regra = null;
        String numeroEdital = null;
        String codigoOrcamento = null;
        String numeroContrato = null;
        Long permanencia = null;
        Double remuneracao = null;
        Double remuneracaoDemaisCanais = null;
        Double meta = null;
        Boolean vigenteNoBanco = null;
    }

    @Override
    public Boolean isAuditoriaComHistorico() {
        return Boolean.TRUE;
    }

    @Override
    protected void prepararParaCriarVersaoParaAuditoria() {
        for (AcaoRegra acaoRegra : getRegra().getAcoesRegra()) {
            Atributo atributo = acaoRegra.getAtributo();
            if (atributo.isAtributoSistemico()) {
                AtributoSistemaEnum atributoSistemico = atributo.getAtributoSistema();
                if (atributoSistemico != null) {
                    switch (atributoSistemico) {
                        case PRAZO_PERMANENCIA:
                            this.setPermanencia(Long.valueOf(acaoRegra.getValor()));
                            break;
                        case META:
                            this.setMeta(Double.valueOf(acaoRegra.getValor()));
                            break;
                        case REMUNERACAO:
                            this.setRemuneracao(Double.valueOf(acaoRegra.getValor()));
                            break;
                        case REMUNERACAO_DEMAIS_CANAIS:
                            this.setRemuneracaoDemaisCanais(Double.valueOf(acaoRegra.getValor()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        super.prepararParaCriarVersaoParaAuditoria();
    }

    @Override
    protected void executarAposCriarVersaoParaAuditoria() {
        if (this.getPermanencia() != null && !this.getPermanencia().equals(((Convenio) this.getVersaoParaAuditoria()).getPermanencia())) {
            throw new AmsfwException("Erro ao preparar convenio para auditoria [permanencia].");
        }
        if (this.getMeta() != null && !this.getMeta().equals(((Convenio) this.getVersaoParaAuditoria()).getMeta())) {
            throw new AmsfwException("Erro ao preparar convenio para auditoria [meta].");
        }
        if (this.getRemuneracao() != null && !this.getRemuneracao().equals(((Convenio) this.getVersaoParaAuditoria()).getRemuneracao())) {
            throw new AmsfwException("Erro ao preparar convenio para auditoria [remuneracao canal empresa].");
        }
        if (this.getRemuneracaoDemaisCanais() != null
                && !this.getRemuneracaoDemaisCanais().equals(((Convenio) this.getVersaoParaAuditoria()).getRemuneracaoDemaisCanais())) {
            throw new AmsfwException("Erro ao preparar convenio para auditoria [remuneracao demais canais].");
        }
        super.executarAposCriarVersaoParaAuditoria();
    }

//    @Override
//    public Long getId() {
//        return this.id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Metodo que verifica se os criterios podem ser editaveis.
     *
     * @return
     */
    public Boolean isCriteriosEditavel() {
        return inicioVigencia == null || !DataVigenciaUtil.isDataInformadaInferiorDiaAtual(inicioVigencia);
    }

    /**
     * Metodo que verifica se a data inicio e' maior ou igual que a data atual.
     *
     * @return
     */
    public boolean isDataInicioMaiorOuIgualDataAtual() {
        return DataVigenciaUtil.isDataInformadaEMaiorIgualDataAtual(getInicioVigencia());
    }

    /**
     * Verificar se as datas estão validas.
     *
     * @return
     */
    public Boolean isDatasValidas() {

        // Fim de vigencia deve ser maior que inicio de vigência
        if (!DataVigenciaUtil.isDataInicioInferiorDataFim(getInicioVigencia(), getFimVigencia())) {
            throw new VigenciaInvalidaException();
        }

        // Fim de vigencia deve ser maior ou igual que 30 dias do inicio de
        // vigência
        if (!DataVigenciaUtil.isQtdeDiasSuperiorAQtdeDiasInformado(getInicioVigencia(), getFimVigencia(), NUMERO_DIAS_MINIMO_CONVENIO)) {
            throw new VigenciaIntervalo30diasException();
        }

        if (getInicioVigencia().before(DateUtil.getDataAtual())) {
            throw new VigenciaInicialRetroativaInvalidaException();
        }

//        if (this.id == null || (this.isAtivo() && !this.vigenteNoBanco)) {
        if (this.isAtivo() && !this.vigenteNoBanco) {
            if (DataVigenciaUtil.isDataInicioInferiorDataFim(getInicioVigencia(), DateUtil.getDataAtualHoraMinuto())
                    && DataVigenciaUtil.isDataInicioInferiorDataFim(getInicioVigencia(), fimVigencia)
                    && DataVigenciaUtil.isDataInicioInferiorDataFim(getFimVigencia(), DateUtil.getDataAtual())) {
                throw new VigenciaInicialRetroativaInvalidaException();
            }
        }

        return Boolean.TRUE;
    }

    /**
     *
     * @return
     */
    public String getCodigoEmpresaVigencia() {

//        if (getId() != null && getEmpresaTerceirizada() != null && getEmpresaTerceirizada().getRazaoSocial() != null
        if (getEmpresaTerceirizada() != null && getEmpresaTerceirizada().getRazaoSocial() != null
                && getInicioVigencia() != null && getFimVigencia() != null) {

//            return getId() + " - " + getEmpresaTerceirizada().getRazaoSocial() + " - " + DateUtil.formataData(getInicioVigencia()) + " - "
//                    + DateUtil.formataData(getFimVigencia());
                return getEmpresaTerceirizada().getRazaoSocial() + " - " + DateUtil.formataData(getInicioVigencia()) + " - "
                        + DateUtil.formataData(getFimVigencia());        }
        return null;
    }

    /**
     * Metodo que recupera a situacao de um convenio.
     *
     * @return
     */
    public SituacaoConvenioEnum getSituacaoConvenioEnum() {
        return SituacaoConvenioEnum.fromValue(this.situacaoConvenio);
    }

    /**
     * @return
     */
    public Boolean isAtivo() {
        return SituacaoConvenioEnum.ATIVO.equals(getSituacaoConvenioEnum());
    }

    /**
     * @return
     */
    public Boolean isSuspenso() {
        return SituacaoConvenioEnum.SUSPENSO.equals(getSituacaoConvenioEnum());
    }

    /**
     * @return
     */
    public Boolean isEncerrado() {
        return SituacaoConvenioEnum.ENCERRADO.equals(getSituacaoConvenioEnum());
    }

    /**
     * @return
     */
    public Boolean isVencido() {
        return !isSuspenso() && !isEncerrado() && (SituacaoConvenioEnum.VENCIDO.equals(getSituacaoConvenioEnum())
                || (getFimVigencia() != null && getFimVigencia().before(DateUtil.getDataAtual())));
    }

    /**
     * @return
     */
    public Boolean isVigente() {

        Boolean condicaoInicioVigencia =
                getInicioVigencia() != null && !DataVigenciaUtil.isDataInformadaEMaiorIgualDataAtual(getInicioVigencia());

        Boolean isVigente = isAtivo() && condicaoInicioVigencia && !isVencido() && regra.isCriteriosEditavel();
//        if (getId() != null && vigenteNoBanco == null) {
        if (vigenteNoBanco == null) {
            vigenteNoBanco = isVigente;
        }
        return isVigente;
    }

    /**
     * Metodo que insere a situacao do convenio por um enum.
     *
     * @param situacao
     */
    public void setSituacaoConvenioEnum(SituacaoConvenioEnum situacao) {
        if (situacao != null) {
            this.situacaoConvenio = situacao.getCodigo();
        } else {
            this.situacaoConvenio = null;
        }
    }

    public RegraDistribuicao getRegra() {
        return regra;
    }

    public void setRegra(RegraDistribuicao regra) {
        this.regra = regra;
    }

    /**
     * @return the inicioVigencia
     */
    public Date getInicioVigencia() {
        return DateUtil.duplicaObjetoData(inicioVigencia);
    }

    /**
     * @param inicioVigencia
     *            the inicioVigencia to set
     */
    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = DateUtil.duplicaObjetoData(inicioVigencia);
    }

    /**
     * @return the fimVigencia
     */
    public Date getFimVigencia() {
        return DateUtil.duplicaObjetoData(fimVigencia);
    }

    /**
     * @param fimVigencia
     *            the fimVigencia to set
     */
    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = DateUtil.duplicaObjetoData(fimVigencia);
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

    /**
     * @return the dataSuspensao
     */
    public Date getDataSuspensao() {
        return DateUtil.duplicaObjetoData(dataSuspensao);
    }

    /**
     * @param dataSuspensao
     *            the dataSuspensao to set
     */
    public void setDataSuspensao(Date dataSuspensao) {
        this.dataSuspensao = DateUtil.duplicaObjetoData(dataSuspensao);
    }

    /**
     * @return the descricaoSuspensao
     */
    public String getDescricaoSuspensao() {
        return descricaoSuspensao;
    }

    /**
     * @param descricaoSuspensao
     *            the descricaoSuspensao to set
     */
    public void setDescricaoSuspensao(String descricaoSuspensao) {
        this.descricaoSuspensao = descricaoSuspensao;
    }

    /**
     * @return the dataSuspensao
     */
    public Date getDataReativacao() {
        return DateUtil.duplicaObjetoData(dataReativacao);
    }

    /**
     * Metodo que atribui uma data para a data de remuneração.
     *
     * @param dataReativacao
     */
    public void setDataReativacao(Date dataReativacao) {
        this.dataReativacao = DateUtil.duplicaObjetoData(dataReativacao);
    }

    /**
     * @return the descricaoSuspensao
     */
    public String getDescricaoReativacao() {
        return descricaoReativacao;
    }

    /**
     *
     * @param descricaoReativacao
     */
    public void setDescricaoReativacao(String descricaoReativacao) {
        this.descricaoReativacao = descricaoReativacao;
    }

    /**
     * @return the dataEncerramento
     */
    public Date getDataEncerramento() {
        return DateUtil.duplicaObjetoData(dataEncerramento);
    }

    /**
     * @param dataEncerramento
     *            the dataEncerramento to set
     */
    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = DateUtil.duplicaObjetoData(dataEncerramento);
    }

    /**
     * @return the descricaoEncerramento
     */
    public String getDescricaoEncerramento() {
        return descricaoEncerramento;
    }

    /**
     * @param descricaoEncerramento
     *            the descricaoEncerramento to set
     */
    public void setDescricaoEncerramento(String descricaoEncerramento) {
        this.descricaoEncerramento = descricaoEncerramento;
    }

    /**
     * @return the empresaTerceirizada
     */
    public EmpresaTerceirizada getEmpresaTerceirizada() {
        return empresaTerceirizada;
    }

    /**
     * @param empresaTerceirizada
     *            the empresaTerceirizada to set
     */
    public void setEmpresaTerceirizada(EmpresaTerceirizada empresaTerceirizada) {
        this.empresaTerceirizada = empresaTerceirizada;
    }

    public String getNumeroEdital() {
        return numeroEdital;
    }

    public void setNumeroEdital(String numeroEdital) {
        this.numeroEdital = numeroEdital;
    }

    /**
     * @return the cliente
     */
    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }

    /**
     * @param cliente
     *            the cliente to set
     */
    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada cliente) {
        this.clienteCobrancaTerceirizada = cliente;
    }

    /**
     * @return the permanencia
     */
    public Long getPermanencia() {
        return permanencia;
    }

    /**
     * @param permanencia
     *            the permanencia to set
     */
    public void setPermanencia(Long permanencia) {
        this.permanencia = permanencia;
    }

    /**
     * @return the remuneracao
     */
    public Double getRemuneracao() {
        return remuneracao;
    }

    /**
     * @param remuneracao
     *            the remuneracao to set
     */
    public void setRemuneracao(Double remuneracao) {
        this.remuneracao = remuneracao;
    }

    /**
     * @return the meta
     */
    public Double getMeta() {
        return meta;
    }

    /**
     * @param meta
     *            the meta to set
     */
    public void setMeta(Double meta) {
        this.meta = meta;
    }

    /**
     * @return the vigenteNoBanco
     */
    public Boolean getVigenteNoBanco() {
        return vigenteNoBanco;
    }

    /**
     * @param vigenteNoBanco
     *            the vigenteNoBanco to set
     */
    public void setVigenteNoBanco(Boolean vigenteNoBanco) {
        this.vigenteNoBanco = vigenteNoBanco;
    }

    public String getCodigoOrcamento() {
        return codigoOrcamento;
    }

    public void setCodigoOrcamento(String codigoOrcamento) {
        this.codigoOrcamento = codigoOrcamento;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Double getRemuneracaoDemaisCanais() {
        return remuneracaoDemaisCanais;
    }

    public void setRemuneracaoDemaisCanais(Double remuneracaoDemaisCanais) {
        this.remuneracaoDemaisCanais = remuneracaoDemaisCanais;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Convenio [id=");
//        builder.append(id);
        builder.append(", inicioVigencia=");
        builder.append(inicioVigencia);
        builder.append(", fimVigencia=");
        builder.append(fimVigencia);
        builder.append(", situacaoConvenio=");
        builder.append(situacaoConvenio);
        builder.append(", tipoDistribuicao=");
        builder.append(tipoDistribuicao);
        builder.append(", dataEncerramento=");
        builder.append(dataEncerramento);
        builder.append(", descricaoEncerramento=");
        builder.append(descricaoEncerramento);
        builder.append(", empresaTerceirizada=");
        builder.append(empresaTerceirizada);
        builder.append(", regra=");
        builder.append(regra);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Convenio other = (Convenio) obj;
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}