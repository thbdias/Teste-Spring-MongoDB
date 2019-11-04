package unisys.thbd.sigma.models;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.Date;
        import java.util.List;

public abstract class Regra extends EntidadeAuditavelCobrancaTerceirizada implements ParametroProcessamento {

//    private static final long serialVersionUID = 1L;

    private static final int NUMERO_DIAS_MINIMO_REGRA_DISTRIBUICAO = 30;

    protected Regra(TipoRegraEnum tipoRegra) {
        this.setTipoRegraEnum(tipoRegra);
    }

//    private Long id;

    private String nome;
    private Date inicioVigencia;
    private Date fimVigencia;
    private Integer tipoRegra;
    private List<CondicaoCompostaRegra> condicoesComposta;
    private List<AcaoRegra> acoesRegra;
    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;

    private Boolean ativo;

    private Boolean vigenteNoBanco = null;

    private Date inicioVigenciaNoBanco;

    private Date dataInicioAnterior;

    private Date dataFimAnterior;

    private Date fimVigenciaNoBanco;

    /**
     * <h3>DISTRIBUICAO DE CONTRATOS</h3> Avalia passo de distribuicao da regra
     * apos casamento com contrato.
     *
     * Este metodo deve ser sobrescrito para especializar distribuicao por
     * regra.
     */
    public Boolean avaliaRegra(ContratoDistribuicaoModel contratoDistribuicao, AmbienteDistribuicaoContrato ambienteDistribuicao) {
        return Boolean.TRUE;
    }

    /**
     * Metodo que recupera atributos de acao regra.
     *
     * @param codigo
     * @return
     */
    public String recuperarAtributoAcaoRegra(String codigo) {

        if (acoesRegra != null && codigo != null && !"".equals(codigo)) {
            for (AcaoRegra acaoRegraAtributo : acoesRegra) {
                if (acaoRegraAtributo.getAtributo().getAtributo().equals(codigo)) {
                    return acaoRegraAtributo.getValor();
                }
            }
        }

        return null;
    }

    /**
     * Metodo que verifica se ha' data inicio vigente no banco e se a data
     * inicio do banco e' igual ou maior que a data atual.
     *
     * @return
     */
    public Boolean isCriteriosEditavel() {
        return getInicioVigenciaNoBanco() == null || !DataVigenciaUtil.isDataInformadaInferiorDiaAtual(getInicioVigenciaNoBanco());
    }

    // **************************************************************************************************************
    // Inicio Metodos templates para validacao de regras de vigencia.
    // **************************************************************************************************************

    /**
     * Metodo utilizado para verificar a possibilidade de edicao na tela de
     * cadastro.
     *
     * @return
     */
//    public Boolean readOnlyDatas() {
//        return getId() != null;
//    }

    /**
     * Validacao padrão que verifica se a Regra está ativa.
     *
     * Usado em REGRA DE DISTRIBUICAO.
     *
     * @return
     */
    public Boolean isDatasValidas() {
        verificarSeInicioInferiorAoFim();
        return Boolean.TRUE;
    }

    /**
     * Metodo que verifica se ao ser inserido um novo registro a data inicio e
     * menor que a data atual.
     *
     * IMPEDIMENTOS
     */
    void verificarSeDataInicioMenorDataAtual() {
//        if (getId() == null) {
            if (DataVigenciaUtil.isDataInformadaInferiorDiaAtual(inicioVigencia)) {
                throw new DataInicioVigenciaMenorDataAtualException();
            }
//        }
    }

    /**
     * Verificacao se a data inicio que veio do banco e' menor que a data atual
     * e a data inicio for maior ou igual a data que veio do banco.
     *
     * ACAO COBRANCA
     */
    void verificarDatasDeInicioAtualComVigente() {
        if (DataVigenciaUtil.isDataInformadaInferiorDiaAtual(inicioVigenciaNoBanco)
                && DataVigenciaUtil.isDataInicioInferiorDataFim(inicioVigenciaNoBanco, inicioVigencia)) {
            throw new DataInicioVigenteBancoAtualException();
        }
    }

    /**
     * Validacao em que se a data inicio for menor igual que a data atual E
     * inicio maior que uma data cadastrada anteriormente sera um erro.
     *
     * REGRA ACAO DE COBRANCA.
     */
    void verificaSeVigenciaEdicaodataInicio() {
//        if (getId() != null) {
            if (DataVigenciaUtil.isDataInformadaInferiorOuIgualHoje(inicioVigencia)
                    && DataVigenciaUtil.isDataInicioInferiorDataFim(inicioVigenciaNoBanco, inicioVigencia)) {
                throw new DataInicioVigenciaMaiorCadastradaBancoException();
            }
//        }
    }

    /**
     * Validacao em que se a data inicio que veio do banco for menor igual que a
     * data atual E a data fim for menor que a data atual sera um erro.
     *
     * REGRA IMPEDIMENTO, ACAO DE COBRANCA
     */
    void verificaInicioVigenciaBancoFimVigenciaEdicao() {
//        if (getId() != null) {
            if (DataVigenciaUtil.isDataInformadaInferiorDiaAtual(getInicioVigenciaNoBanco())
                    && DataVigenciaUtil.isDataInformadaInferiorDiaAtual(fimVigencia)) {
                throw new DataVigenciaFimMenorDataAtualException();
            }
//        }
    }

    /**
     * Validacao em que se a data inicio que veio do banco for menor igual que a
     * data atual E a data inicio for menor que a data atual.
     *
     * REGRA IMPEDIMENTO.
     */
    void verificaInicioVigenciaMenorQueDiaAtualEdicao() {
//        if (getId() != null && DataVigenciaUtil.isDataInformadaInferiorDiaAtual(inicioVigenciaNoBanco)) {
        if (DataVigenciaUtil.isDataInformadaInferiorDiaAtual(inicioVigenciaNoBanco)) {
            throw new VigenciaInicialRetroativaInvalidaException();
        }
    }

    /**
     * Metodo que verifica se a data final é inferior a data atual.
     *
     * Usado em ACAO DE COBRANCA, IMPEDIMENTOS
     */
    boolean verificaSeDataFimVigenciaNoBancoMenorDataAtual() {
        return DataVigenciaUtil.isDataInformadaInferiorDiaAtual(fimVigenciaNoBanco);
    }

    /**
     * Fim de vigencia deve ser maior que inicio de vigencia.
     *
     * Usado em ACAO DE COBRANCA, REGRA DE DISTRIBUICAO, REGRA RESTRICAO
     *
     */
    void verificarSeInicioInferiorAoFim() {
        if (!DataVigenciaUtil.isDataInicioInferiorDataFim(inicioVigencia, fimVigencia)) {
            throw new VigenciaInvalidaException();
        }
    }

    /**
     * Fim de vigencia deve ser maior ou igual que 30 dias do inicio de
     * vigência.
     *
     * Usado em REGRA DE DISTRIBUICAO
     *
     */
    void verificarSeIntervaloDataInicioFimMaiorQue30Dias() {
        if (!DataVigenciaUtil.isQtdeDiasSuperiorAQtdeDiasInformado(inicioVigencia, fimVigencia, NUMERO_DIAS_MINIMO_REGRA_DISTRIBUICAO)) {
            throw new VigenciaIntervalo30diasException();
        }
    }

    /**
     * Inicio de vigência NÃO deve ser inferior a data atual.
     *
     * Este teste só é chamado quando o registro é novo e não persistido.
     *
     * Usado em REGRA DE DISTRIBUICAO
     *
     */
    void verificarSeInicioVigenciaMenorDataAtual() {
//        if (this.id == null || (!vigenteNoBanco)) {
        if (!vigenteNoBanco) {
            if (DataVigenciaUtil.isDataInicioInferiorDataFim(inicioVigencia, DateUtil.getDataAtual())
                    && DataVigenciaUtil.isDataInicioInferiorDataFim(inicioVigencia, fimVigencia)
                    && DataVigenciaUtil.isDataInicioInferiorDataFim(fimVigencia, DateUtil.getDataAtual())) {
                throw new VigenciaInicialRetroativaInvalidaException();
            }
        }
    }

    // **************************************************************************************************************
    // Fim Metodos templates para validacao de regras de vigencia.
    // **************************************************************************************************************

    /**
     * Metodo que verifica se a data inicio e' maior ou igual que a data atual.
     *
     * @return
     */
    public boolean isDataInicioMaiorOuIgualDataAtual() {
        return DataVigenciaUtil.isDataInformadaEMaiorIgualDataAtual(getInicioVigenciaNoBanco());
    }

    /**
     * Metodo que verifica se o fim da vigencia no banco e' menor que atual.
     *
     * @return
     */
    public boolean isDataFimVigenciaMenorAtual() {
//        if (this.id != null && this.fimVigenciaNoBanco != null) {
        if (this.fimVigenciaNoBanco != null) {
            return DataVigenciaUtil.isDataInformadaInferiorDiaAtual(fimVigenciaNoBanco);
        }
        return false;
    }

    /**
     * Metodo que recupera valores que pertencem a regra.
     *
     * @param campo
     * @return
     */
    public String getValorAcaoRegra(String campo) {

        if (campo != null && !"".equals(campo)) {
            List<AcaoRegra> listaAcoesRegra = this.acoesRegra;
            if (listaAcoesRegra != null) {
                for (AcaoRegra acaoRegra : listaAcoesRegra) {
                    AcaoRegraAtribuicao acaoRegraAtribuicao = (AcaoRegraAtribuicao) acaoRegra;
                    if (acaoRegraAtribuicao.getAtributo().getAtributo().equals(campo)) {
                        return acaoRegraAtribuicao.getValor();
                    }
                }
            }
        }

        return null;
    }

    /**
     * Avaliar a Condições da Regra.
     *
     * @param ambienteAvaliacaoRegra
     * @return
     */
    public Boolean avaliarCondicao(AmbienteAvaliacaoRegra ambienteAvaliacaoRegra) {
        Collections.sort(this.getCondicoesComposta(), new Comparator<CondicaoCompostaRegra>() {
            @Override
            public int compare(CondicaoCompostaRegra c1, CondicaoCompostaRegra c2) {
                return c1.getOrdem().compareTo(c2.getOrdem());
            }
        });
        Boolean resultado = null;
        CondicaoCompostaRegra condicaoAnterior = null;
        for (CondicaoCompostaRegra condicao : this.getCondicoesComposta()) {
            Boolean resultadoParcial = condicao.avaliarCondicao(ambienteAvaliacaoRegra);
            if (condicaoAnterior != null) {
                resultado = condicaoAnterior.getOperadorLogico().avaliarCondicao(resultado, resultadoParcial);
            } else {
                resultado = resultadoParcial;
            }
            condicaoAnterior = condicao;
        }
        return resultado;
    }

    /**
     * Verifica se a Regra está vigente.
     *
     * @return
     */
    public Boolean isVigente() {

        Boolean condicaoInicioVigencia =
                this.inicioVigencia != null && !this.inicioVigencia.after(DateUtil.getDataAtualUltimaHoraMinutoSegundoDoDia());

        Boolean condicaoFimVigencia = this.fimVigencia != null && !this.fimVigencia.before(DateUtil.getDataAtual());

        Boolean isVigente = isAtivo() && condicaoInicioVigencia && condicaoFimVigencia;

//        if (this.id != null && vigenteNoBanco == null) {
        if (vigenteNoBanco == null) {
            vigenteNoBanco = isVigente;
        }

//        if (this.id != null && inicioVigenciaNoBanco == null) {
        if (inicioVigenciaNoBanco == null) {
            inicioVigenciaNoBanco = inicioVigencia;
        }

//        if (this.id != null && fimVigenciaNoBanco == null) {
        if (fimVigenciaNoBanco == null) {
            fimVigenciaNoBanco = this.fimVigencia;
        }

        return isVigente;
    }

    /**
     * Obter a Data de Inicio de Vigência da Regra.
     *
     * @return the inicioVigencia
     */
    public Date getInicioVigencia() {

        if (this.inicioVigencia != null) {

//            if (getId() != null && this.vigenteNoBanco == null) {
            if (this.vigenteNoBanco == null) {
                this.vigenteNoBanco = isVigente();
            }

//            if (this.id != null && inicioVigenciaNoBanco == null) {
            if (inicioVigenciaNoBanco == null) {
                this.inicioVigenciaNoBanco = this.inicioVigencia;
            }

            return new Date(this.inicioVigencia.getTime());
        } else {
            return null;
        }
    }

    /**
     * Alterar a Data de Inicio de Vigência da Regra.
     *
     * @param inicioVigencia
     *            the inicioVigencia to set
     */
    public void setInicioVigencia(Date inicioVigencia) {
        if (inicioVigencia != null) {

//            if (getId() != null && this.vigenteNoBanco == null) {
            if (this.vigenteNoBanco == null) {
                this.vigenteNoBanco = isVigente();
            }

            this.inicioVigencia = new Date(inicioVigencia.getTime());

//            if (this.id != null && inicioVigenciaNoBanco == null) {
            if (inicioVigenciaNoBanco == null) {
                this.inicioVigenciaNoBanco = this.inicioVigencia;
            }

        } else {
            this.inicioVigencia = null;
        }
    }

    /**
     * Obter a Data de Final de Vigência da Regra.
     *
     * @return the fimVigencia
     */
    public Date getFimVigencia() {
        if (this.fimVigencia != null) {
//            if (getId() != null && this.vigenteNoBanco == null) {
            if (this.vigenteNoBanco == null) {
                this.vigenteNoBanco = isVigente();
            }

//            if (this.id != null && fimVigenciaNoBanco == null) {
            if (fimVigenciaNoBanco == null) {
                this.fimVigenciaNoBanco = this.fimVigencia;
            }

            return new Date(this.fimVigencia.getTime());
        } else {
            return null;
        }
    }

    /**
     * Alterar a Data de Final de Vigência da Regra.
     *
     * @param fimVigencia
     *            the fimVigencia to set
     */
    public void setFimVigencia(Date fimVigencia) {
        if (fimVigencia != null) {

//            if (getId() != null && this.vigenteNoBanco == null) {
            if (this.vigenteNoBanco == null) {
                this.vigenteNoBanco = isVigente();
            }

            this.fimVigencia = new Date(fimVigencia.getTime());

//            if (this.id != null && fimVigenciaNoBanco == null) {
            if (fimVigenciaNoBanco == null) {
                this.fimVigenciaNoBanco = this.fimVigencia;
            }

        } else {
            this.fimVigencia = null;
        }
    }

    /**
     * Obter as Condições Compostas da Regra.
     *
     * @return
     */
    public List<CondicaoCompostaRegra> getCondicoesComposta() {
        iniciaCondicoesComposta();

        Collections.sort(condicoesComposta, new Comparator<CondicaoCompostaRegra>() {
            @Override
            public int compare(CondicaoCompostaRegra ccr1, CondicaoCompostaRegra ccr2) {
                if (ccr1 == null || ccr2 == null) {
                    return 0;
                }
                return ccr1.getOrdem().compareTo(ccr2.getOrdem());
            }
        });

        return condicoesComposta;
    }

    /**
     * Metodo que retorna uma String com o ID e Nome.
     *
     * @return
     */
    public String getStringIdNome() {
//        if (getId() != null && getNome() != null) {
        if (getNome() != null) {
            return getNome();
        }
        return "";
    }

    /**
     * Obter Id da Regra.
     *
     * @return the id
     */
//    public Long getId() {
//        return id;
//    }

    /**
     * Alterar o Id da Regra.
     *
     * @param id
     *            the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Obter o nome da Regra.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Alterar o nome da Regra.
     *
     * @param nome
     *            the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obter o Tipo de Regra.
     *
     * @return the tipoRegra
     */
    public TipoRegraEnum getTipoRegraEnum() {
        return TipoRegraEnum.getTipoRegraEnum(tipoRegra);
    }

    /**
     * Alterar o Tipo de Regra.
     *
     * @param tipoRegra
     *            the tipoRegra to set
     */
    public void setTipoRegraEnum(TipoRegraEnum tipoRegra) {
        if (tipoRegra != null) {
            this.tipoRegra = tipoRegra.getCodigo();
        } else {
            this.tipoRegra = null;
        }
    }

    /**
     * Alterar as Condições Compostas da Regra.
     *
     * @param condicoesComposta
     */
    public void setCondicoesComposta(List<CondicaoCompostaRegra> condicoesComposta) {
        this.condicoesComposta = new ArrayList<>();
        this.adicionaTodasCondicaoesComposta(condicoesComposta);
    }

    /**
     * Iniciar a Lista de Condições Compostas da Regra.
     */
    private void iniciaCondicoesComposta() {
        if (this.condicoesComposta == null) {
            this.condicoesComposta = new ArrayList<CondicaoCompostaRegra>();
        }
    }

    /**
     * Adicionar um Condições Compostas a Regra.
     *
     * @param condicaoComposta
     */
    public void adicionaCondicaoComposta(CondicaoCompostaRegra condicaoComposta) {
        iniciaCondicoesComposta();
        if (!this.condicoesComposta.contains(condicaoComposta)) {
            this.condicoesComposta.add(condicaoComposta);
            condicaoComposta.setRegra(this);
        }
    }

    /**
     * Adicionar todas as Condições Compostas da lista na Regra.
     *
     * @param listaCondicoesComposta
     */
    public void adicionaTodasCondicaoesComposta(List<CondicaoCompostaRegra> listaCondicoesComposta) {
        iniciaCondicoesComposta();
        if (listaCondicoesComposta != null) {
            for (CondicaoCompostaRegra condicaoComposta : listaCondicoesComposta) {
                adicionaCondicaoComposta(condicaoComposta);
            }
        }
    }

    /**
     * Remover um Condições Compostas a Regra.
     *
     * @param condicaoComposta
     */
    public void removeCondicaoComposta(CondicaoCompostaRegra condicaoComposta) {
        iniciaCondicoesComposta();
        this.condicoesComposta.remove(condicaoComposta);
    }

    /**
     * Recupera a lista de atributos utilizados na definição da regra.
     *
     * @return atributos da regra.
     */
    public List<Atributo> getAtributosCondicaoRegra() {
        List<Atributo> atributosCondicaoRegra = new ArrayList<Atributo>();
        for (CondicaoCompostaRegra condicaoComposta : this.condicoesComposta) {
            for (CondicaoSimplesRegra condicaoSimples : condicaoComposta.getCondicoesSimples()) {
                atributosCondicaoRegra.add(condicaoSimples.getAtributo());
            }
        }
        return atributosCondicaoRegra;
    }

    /**
     * Obter as Ações da Regra.
     *
     * @return the acoesRegra
     */
    public List<AcaoRegra> getAcoesRegra() {
        iniciaAcoesRegra();
        return this.acoesRegra;
    }

    /**
     * Alterar as Ações da Regra.
     *
     * @param acoesRegraAtribuicao
     */
    public void setAcoesRegra(List<AcaoRegra> acoesRegraAtribuicao) {
        this.acoesRegra = acoesRegraAtribuicao;
    }

    /**
     * Iniciar a Lista de Ações da Regra.
     */
    private void iniciaAcoesRegra() {
        if (this.acoesRegra == null) {
            this.acoesRegra = new ArrayList<AcaoRegra>();
        }
    }

    /**
     * Adicionar uma Ação da Regra.
     *
     * @param acaoRegra
     */
    public void adicionaAcoesRegra(AcaoRegra acaoRegra) {
        iniciaAcoesRegra();
        if (!this.acoesRegra.contains(acaoRegra)) {
            this.acoesRegra.add(acaoRegra);
        }
    }

    /**
     * Remover uma Ação da Regra.
     *
     * @param acaoRegra
     */
    public void removeAcaoRegra(AcaoRegra acaoRegra) {
        iniciaAcoesRegra();
        this.acoesRegra.remove(acaoRegra);
    }

    /**
     * Obter Cliente de Cobrança Terceirizada.
     *
     * @return the cliente
     */
    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }

    /**
     * Alterarr Cliente de Cobrança Terceirizada.
     *
     * @param clienteCobrancaTerceirizada
     *            the cliente to set
     */
    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
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

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * Verifica se a Regra estÃ¡ ativa.
     *
     * @return
     */
    public Boolean isAtivo() {
        return this.ativo != null ? this.ativo : Boolean.TRUE;
    }

    /**
     * @param ativo
     *            the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return the inicioVigenciaNoBanco
     */
    public Date getInicioVigenciaNoBanco() {
        return inicioVigenciaNoBanco != null ? new Date(inicioVigenciaNoBanco.getTime()) : null;
    }

    /**
     * @param inicioVigenciaNoBanco
     *            the inicioVigenciaNoBanco to set
     */
    public void setInicioVigenciaNoBanco(Date inicioVigenciaNoBanco) {
        this.inicioVigenciaNoBanco = inicioVigenciaNoBanco != null ? new Date(inicioVigenciaNoBanco.getTime()) : null;
    }

    /**
     * Método que define se uma regra precisa de redistribuicao.
     *
     * @return
     */
    public boolean necessitaRedistribuicao() {
        return false;
    }

    public Date getDataInicioAnterior() {
        return dataInicioAnterior != null ? new Date(dataInicioAnterior.getTime()) : null;
    }

    public void setDataInicioAnterior(Date dataInicioAnterior) {
        this.dataInicioAnterior = DateUtil.duplicaObjetoData(dataInicioAnterior);
    }

    public Date getDataFimAnterior() {
        return dataFimAnterior != null ? new Date(dataFimAnterior.getTime()) : null;
    }

    public void setDataFimAnterior(Date dataFimAnterior) {
        this.dataFimAnterior = dataFimAnterior != null ? new Date(dataFimAnterior.getTime()) : null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((tipoRegra == null) ? 0 : tipoRegra.hashCode());
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
        if (!(obj instanceof Regra)) {
            return false;
        }
        Regra other = (Regra) obj;
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }

        if (nome == null) {
            if (other.nome != null) {
                return false;
            }
        } else if (!nome.equals(other.nome)) {
            return false;
        }
        if (tipoRegra == null) {
            if (other.tipoRegra != null) {
                return false;
            }
        } else if (!tipoRegra.equals(other.tipoRegra)) {
            return false;
        }

        return true;
    }
}
