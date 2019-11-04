package unisys.thbd.sigma.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.*;

import org.springframework.data.annotation.Transient;
import unisys.thbd.sigma.models.EntidadeCobrancaTerceirizada;

public abstract class ContratoDistribuicaoModel extends EntidadeCobrancaTerceirizada {

    private static final long serialVersionUID = 1L;
    @Id
    private ObjectId id;
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    // Constructors
    public ContratoDistribuicaoModel() {}

//    public ContratoDistiruicaoModel( ) {
//    }

    private ContratoCobrancaTerceirizada contratoCobrancaTerceirizada;
    private Integer situacao;
    private Convenio convenio;
    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;
    private Date dataDistribuicao;
    private List<ContratoDistribuicaoRegra> contratoDistribuicaoRegras;
    private List<Pagamento> pagamentos;
    private Date dataTerminoPermancencia;
    private Date dataEncerramento;
    private Date dataRemessaEncerramento;
    private String usuarioResponsavel;
    private Integer motivoEncerramento;
    private Date dataIncorporacao;
    private Date dataApuracao;
    private String descricaoEncerramento;
    private List<Integer> filtroSituacao;
    private Date dataIncio;
    private Date dataFim;
    private Map<TipoRegraEnum, List<ContratoDistribuicaoRegra>> mapaContratoDistribuicaoRegraPorTipo;



    // ObjectId needs to be converted to string
    public String get_id() { return id.toHexString(); }
    public void set_id(ObjectId id) { this.id = id; }
    public abstract Serializable getId();

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.getAdditionalProperties().put(name, value);
    }


//    public String getDataDistribuicaoFormatada() {
//        if (this.dataDistribuicao != null) {
//            return DateUtil.formataData(this.dataDistribuicao);
//        }
//        return "";
//    }
//
//    /**
//     * Metodo que formata a data de incorporacao.
//     *
//     * @return
//     */
//    public String getDataIncorporacaoFormatada() {
//        if (this.dataIncorporacao != null) {
//            return DateUtil.formataData(this.dataIncorporacao);
//        }
//        return "";
//    }
//
//    /**
//     * Metodo quje formata a data de apuracao.
//     *
//     * @return
//     */
//    public String getDataApuracaoFormatada() {
//        if (this.dataApuracao != null) {
//            return DateUtil.formataDataMesAno(this.dataApuracao);
//        }
//        return "";
//    }
//
//    /**
//     *
//     * @return
//     */
//    public String getDataTerminoPermancenciaFormatada() {
//        if (this.dataTerminoPermancencia != null) {
//            return DateUtil.formataData(this.dataTerminoPermancencia);
//        }
//        return "";
//    }
//
//    /**
//     *
//     * @return
//     */
//    public String getDataEncerramentoFormatada() {
//        if (this.dataEncerramento != null) {
//            return DateUtil.formataData(this.dataEncerramento);
//        }
//        return "";
//    }
//
//    /**
//     * Retorna a lista de Filhos de acordo com o atributo.
//     *
//     * @param codigoAtributo
//     * @return
//     */
    public List<ContratoDistribuicaoRegra> recuperaRegraEnquadrada(TipoRegraEnum tipoRegra) {
        List<ContratoDistribuicaoRegra> listaContratoDistribuicaoRegra = getMapaContratoDistribuicaoRegraPorTipo().get(tipoRegra);
        return listaContratoDistribuicaoRegra == null ? Collections.emptyList() : listaContratoDistribuicaoRegra;
    }

    private void iniciaMapaContratoDistribuicaoRegraPorTipo() {
        if (this.mapaContratoDistribuicaoRegraPorTipo == null) {
            this.mapaContratoDistribuicaoRegraPorTipo = new HashMap<TipoRegraEnum, List<ContratoDistribuicaoRegra>>();

            if (getContratoDistribuicaoRegras().size() > mapaContratoDistribuicaoRegraPorTipo.size()) {
                for (ContratoDistribuicaoRegra contratoDistribuicaoRegra : contratoDistribuicaoRegras) {
                    TipoRegraEnum tipoRegraEnum = contratoDistribuicaoRegra.getRegra().getTipoRegraEnum();
                    List<ContratoDistribuicaoRegra> contratosDistribuicaoRegra = mapaContratoDistribuicaoRegraPorTipo.get(tipoRegraEnum);

                    if (contratosDistribuicaoRegra == null) {
                        contratosDistribuicaoRegra = new ArrayList<ContratoDistribuicaoRegra>();
                    }
                    if (!contratosDistribuicaoRegra.contains(contratoDistribuicaoRegra)) {
                        contratosDistribuicaoRegra.add(contratoDistribuicaoRegra);
                    }
                    this.mapaContratoDistribuicaoRegraPorTipo.put(tipoRegraEnum, contratosDistribuicaoRegra);
                }
            }
        }
    }

    private Map<TipoRegraEnum, List<ContratoDistribuicaoRegra>> getMapaContratoDistribuicaoRegraPorTipo() {
        iniciaMapaContratoDistribuicaoRegraPorTipo();
        return this.mapaContratoDistribuicaoRegraPorTipo;
    }

//    /**
//     * @param id
//     *            the id to set
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return the contratoCobrancaTerceirizada
//     */
//    public ContratoCobrancaTerceirizada getContratoCobrancaTerceirizada() {
//        return contratoCobrancaTerceirizada;
//    }
//
//    /**
//     * @param contratoCobrancaTerceirizada
//     *            the contratoCobrancaTerceirizada to set
//     */
//    public void setContratoCobrancaTerceirizada(ContratoCobrancaTerceirizada contratoCobrancaTerceirizada) {
//        this.contratoCobrancaTerceirizada = contratoCobrancaTerceirizada;
//    }
//
//    /**
//     * @return the situacao
//     */
//    public SituacaoContratoDistribuicaoEnum getSituacao() {
//        if (situacao == null) {
//            return null;
//        }
//        return SituacaoContratoDistribuicaoEnum.getSituacaoContratoDistribuicaoEnum(situacao);
//    }
//
//    /**
//     * @param situacao
//     *            the situacao to set
//     */
    public void setSituacao(SituacaoContratoDistribuicaoEnum situacao) {
        if (situacao == null) {
            this.situacao = null;
        } else {
            this.situacao = situacao.getCodigo();
        }
    }


    public Convenio getConvenio() {
        return convenio;
    }

//    /**
//     * @param convenio
//     *            the convenio to set
//     */
//    public void setConvenio(Convenio convenio) {
//        this.convenio = convenio;
//    }
//
//    /**
//     * @return the clienteCobrancaTerceirizada
//     */
//    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
//        return clienteCobrancaTerceirizada;
//    }
//
//    /**
//     * @param clienteCobrancaTerceirizada
//     *            the clienteCobrancaTerceirizada to set
//     */
//    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
//        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
//    }
//
//    /**
//     * @return the contratoDistribuicaoRegras
//     */
    public List<ContratoDistribuicaoRegra> getContratoDistribuicaoRegras() {
        this.iniciaContratoDistribuicaoRegras();
        return Collections.unmodifiableList(contratoDistribuicaoRegras);
    }

//    /**
//     * @param contratoDistribuicaoRegras
//     *            the contratoDistribuicaoRegras to set
//     */
//    public void setContratoDistribuicaoRegras(List<ContratoDistribuicaoRegra> contratoDistribuicaoRegras) {
//        this.contratoDistribuicaoRegras = null;
//        this.iniciaContratoDistribuicaoRegras();
//        if (contratoDistribuicaoRegras != null) {
//            for (ContratoDistribuicaoRegra contratoDistribuicaoRegra : contratoDistribuicaoRegras) {
//                adicionaContratoDistribuicaoRegra(contratoDistribuicaoRegra);
//            }
//        }
//    }
//
//    /**
//     * Inicializa a lista contratoDistribuicaoRegras.
//     */
    private void iniciaContratoDistribuicaoRegras() {
        if (this.contratoDistribuicaoRegras == null) {
            this.contratoDistribuicaoRegras = new ArrayList<ContratoDistribuicaoRegra>();
        }
    }

//    /**
//     * @param contratoDistribuicaoRegra
//     */
    public void adicionaContratoDistribuicaoRegra(ContratoDistribuicaoRegra contratoDistribuicaoRegra) {
        this.iniciaContratoDistribuicaoRegras();
        if (contratoDistribuicaoRegra != null && !this.contratoDistribuicaoRegras.contains(contratoDistribuicaoRegra)) {
            this.contratoDistribuicaoRegras.add(contratoDistribuicaoRegra);
            contratoDistribuicaoRegra.atribuiContratoDistribuicaoRegraPersistindo(this);

            TipoRegraEnum tipoRegra = contratoDistribuicaoRegra.getRegra().getTipoRegraEnum();
            List<ContratoDistribuicaoRegra> listaContratoDistribuicaoRegras = getMapaContratoDistribuicaoRegraPorTipo().get(tipoRegra);
            if (listaContratoDistribuicaoRegras == null) {
                listaContratoDistribuicaoRegras = new ArrayList<ContratoDistribuicaoRegra>();
            }
            listaContratoDistribuicaoRegras.add(contratoDistribuicaoRegra);
            this.getMapaContratoDistribuicaoRegraPorTipo().put(tipoRegra, listaContratoDistribuicaoRegras);
        }
    }

//    /**
//     * @param contratoDistribuicaoRegra
//     */
//    public void removerContratoDistribuicaoRegra(ContratoDistribuicaoRegra contratoDistribuicaoRegra) {
//        this.iniciaContratoDistribuicaoRegras();
//        if (contratoDistribuicaoRegra != null && this.contratoDistribuicaoRegras.remove(contratoDistribuicaoRegra)) {
//            contratoDistribuicaoRegra.atribuiContratoDistribuicaoRegraPersistindo(null);
//
//            TipoRegraEnum tipoRegra = contratoDistribuicaoRegra.getRegra().getTipoRegraEnum();
//            List<ContratoDistribuicaoRegra> listaContratoDistribuicaoRegras = getMapaContratoDistribuicaoRegraPorTipo().get(tipoRegra);
//            listaContratoDistribuicaoRegras.remove(contratoDistribuicaoRegra);
//        }
//    }
//
//    /**
//     * @return the dataDistribuicao
//     */
//    public Date getDataDistribuicao() {
//        return dataDistribuicao != null ? new Date(dataDistribuicao.getTime()) : null;
//    }
//
//    /**
//     * @param dataDistribuicao
//     *            the dataDistribuicao to set
//     */
    public void setDataDistribuicao(Date dataDistribuicao) {
        this.dataDistribuicao = dataDistribuicao != null ? new Date(dataDistribuicao.getTime()) : null;
    }

//    public List<Integer> getFiltroSituacao() {
//        return filtroSituacao;
//    }
//
//    public void setFiltroSituacao(List<Integer> filtroSituacao) {
//        this.filtroSituacao = filtroSituacao;
//    }
//
//    /**
//     * @return the dataTerminoPermancencia
//     */
//    public Date getDataTerminoPermancencia() {
//        return dataTerminoPermancencia != null ? new Date(dataTerminoPermancencia.getTime()) : null;
//    }
//
//    /**
//     * @param dataTerminoPermancencia
//     *            the dataTerminoPermancencia to set
//     */
//    public void setDataTerminoPermancencia(Date dataTerminoPermancencia) {
//        this.dataTerminoPermancencia = dataTerminoPermancencia != null ? new Date(dataTerminoPermancencia.getTime()) : null;
//    }
//
//    /**
//     *
//     */
//    public Date getDataEventoSituacao() {
//        switch (getSituacao()) {
//            case EM_COBRANCA:
//                return getDataDistribuicao();
//            case EM_ACOMPANHAMENTO:
//                return getDataIncorporacao();
//            case ENCERRADO:
//                return getDataEncerramento();
//            case NAO_DISTRIBUIDO:
//                return getDataDistribuicao();
//            case NAO_ENQUADRADO:
//                return getDataDistribuicao();
//            default:
//                break;
//        }
//        return null;
//
//    }
//
//    /**
//     * @return the dataEncerramento
//     */
//    public Date getDataEncerramento() {
//        return dataEncerramento != null ? new Date(dataEncerramento.getTime()) : null;
//    }
//
//    /**
//     * @param dataEncerramento
//     *            the dataEncerramento to set
//     */
//    public void setDataEncerramento(Date dataEncerramento) {
//        this.dataEncerramento = dataEncerramento != null ? new Date(dataEncerramento.getTime()) : null;
//    }
//
//    /**
//     * @return the usuarioResponsavel
//     */
//    public String getUsuarioResponsavel() {
//        return usuarioResponsavel;
//    }
//
//    /**
//     * @param usuarioResponsavel
//     *            the usuarioResponsavel to set
//     */
//    public void setUsuarioResponsavel(String usuarioResponsavel) {
//        this.usuarioResponsavel = usuarioResponsavel;
//    }
//
//    /**
//     * @return the motivoEncerramento
//     */
//    public MotivoEncerramentoEnum getMotivoEncerramento() {
//        if (this.motivoEncerramento == null) {
//            return null;
//        }
//        return MotivoEncerramentoEnum.getMotivoEncerramentoEnum(this.motivoEncerramento);
//    }
//
//    /**
//     * @param motivoEncerramento
//     *            the motivoEncerramento to set
//     */
//    public void setMotivoEncerramento(MotivoEncerramentoEnum motivoEncerramento) {
//        if (motivoEncerramento == null) {
//            this.motivoEncerramento = null;
//        } else {
//            this.motivoEncerramento = motivoEncerramento.getCodigo();
//        }
//    }
//
//    public Date getDataIncio() {
//        return dataIncio != null ? new Date(dataIncio.getTime()) : null;
//    }
//
//    /**
//     * @param dataIncio
//     */
//    public void setDataIncio(Date dataIncio) {
//        this.dataIncio = dataIncio != null ? new Date(dataIncio.getTime()) : null;
//    }
//
//    public Date getDataFim() {
//        return dataFim != null ? new Date(dataFim.getTime()) : null;
//    }
//
//    /**
//     * @param dataFim
//     */
//    public void setDataFim(Date dataFim) {
//        this.dataFim = dataFim != null ? new Date(dataFim.getTime()) : null;
//    }
//
//    /**
//     * @return the dataIncorporacao
//     */
//    public Date getDataIncorporacao() {
//        return dataIncorporacao != null ? new Date(dataIncorporacao.getTime()) : null;
//    }
//
//    /**
//     * @param dataIncorporacao
//     *            the dataIncorporacao to set
//     */
//    public void setDataIncorporacao(Date dataIncorporacao) {
//        this.dataIncorporacao = dataIncorporacao != null ? new Date(dataIncorporacao.getTime()) : null;
//    }
//
//    /**
//     * @return the descricaoEncerramento
//     */
//    public String getDescricaoEncerramento() {
//        return descricaoEncerramento;
//    }
//
//    /**
//     * @param descricaoEncerramento
//     *            the descricaoEncerramento to set
//     */
//    public void setDescricaoEncerramento(String descricaoEncerramento) {
//        this.descricaoEncerramento = descricaoEncerramento;
//    }
//
//    /**
//     * @param situacao
//     *            the situacao to set
//     */
    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

//    /**
//     * @param motivoEncerramento
//     *            the motivoEncerramento to set
//     */
//    public void setMotivoEncerramento(Integer motivoEncerramento) {
//        this.motivoEncerramento = motivoEncerramento;
//    }
//
//    /**
//     * Obter data de Apuração.
//     * @return
//     */
//    public Date getDataApuracao() {
//        return dataApuracao != null ? new Date(dataApuracao.getTime()) : null;
//    }
//
//    /**
//     * Setar data de Apuração.
//     *
//     * @param dataApuracao
//     */
//    public void setDataApuracao(Date dataApuracao) {
//        this.dataApuracao = dataApuracao != null ? new Date(dataDistribuicao.getTime()) : null;
//    }
//
//    public Date getDataRemessaEncerramento() {
//        return dataRemessaEncerramento != null ? new Date(dataRemessaEncerramento.getTime()) : null;
//    }
//
//    public void setDataRemessaEncerramento(Date dataRemessaEncerramento) {
//        this.dataRemessaEncerramento = dataRemessaEncerramento != null ? new Date(dataRemessaEncerramento.getTime()) : null;
//    }
//
//    public List<Pagamento> getPagamentos() {
//        return pagamentos;
//    }
//
//    public void setPagamentos(List<Pagamento> pagamentos) {
//        this.pagamentos = pagamentos;
//    }

}
