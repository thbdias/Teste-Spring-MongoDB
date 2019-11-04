package unisys.thbd.sigma.models;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
        import java.util.TreeMap;


public class AmbienteDistribuicaoContrato {

    private Map<TipoRegraEnum, List<Regra>> mapaDeRegras;
    private Map<Long, Convenio> mapaDeConvenios;
    private static List<ContratoDistribuicaoModel> listaContratosDistribuidosSimples = new ArrayList<ContratoDistribuicaoModel>();
    private static List<ContratoDistribuicaoModel> listaContratosDistribuidosMultiplos = new ArrayList<ContratoDistribuicaoModel>();
    private static Map<String, List<ContratoDistribuicaoModel>> mapaContratosPorConveniosEnquadrados =
            new HashMap<String, List<ContratoDistribuicaoModel>>();
    private Map<String, Double> mapaSomatorioValorDividaPorCriteriosOrdenacao;
    private List<Convenio> conveniosMultiplosCorrente;
    private Convenio[] arrayConveniosMultiplosCorrente;
    // private Map<Long, Double> valoresDistribuidosPorConvenio;
    private Map<Long, Map<String, Double>> valoresDistribuidosPorConvenio;
    private int posicaoCampoValorDividaCorrente;
    private int posicaoConvenioCorrente = 0;
    private Date dataApuracao;

    /**
     * Construtor.
     */
    public AmbienteDistribuicaoContrato() {
        // calcula data apuracao para contratos distribuidos
        this.dataApuracao = DateUtil.adicionaMes(DateUtil.adicionaDias(DateUtil.dataUltimoDiaMes(DateUtil.getDataAtual()), 1), 3);
        AmbienteDistribuicaoContrato.listaContratosDistribuidosSimples = new ArrayList<ContratoDistribuicaoModel>();
        AmbienteDistribuicaoContrato.listaContratosDistribuidosMultiplos = new ArrayList<ContratoDistribuicaoModel>();
        AmbienteDistribuicaoContrato.mapaContratosPorConveniosEnquadrados = new HashMap<String, List<ContratoDistribuicaoModel>>();
    }

    /**
     * Inicia o mapa de regras por tipo.
     */
    private void iniciaMapaDeRegras() {
        if (this.mapaDeRegras == null) {
            this.mapaDeRegras = new HashMap<TipoRegraEnum, List<Regra>>();
        }
    }

    /**
     * Inicia o mapa de convenios.
     */
    private void iniciaMapaDeConvenios() {
        if (this.mapaDeConvenios == null) {
            this.mapaDeConvenios = new HashMap<Long, Convenio>();
        }
    }

    /**
     * @param regra
     * @return
     */
    public boolean adicionarRegra(Regra regra) {
        this.iniciaMapaDeRegras();
        List<Regra> regras = this.mapaDeRegras.get(regra.getTipoRegraEnum());
        if (regras == null) {
            regras = new ArrayList<Regra>();
            this.mapaDeRegras.put(regra.getTipoRegraEnum(), regras);
        }
        return regras.add(regra);
    }

    /**
     * Adicionar convenio a lista.
     *
     * @param convenio
     */
    public void adicionarConvenio(Convenio convenio) {
        iniciaMapaDeConvenios();
//        this.mapaDeConvenios.put(convenio.getId(), convenio);
    }

    /**
     * @return
     */
    public List<Regra> recuperarRegrasDistribuicao() {
        this.iniciaMapaDeRegras();
        return this.mapaDeRegras.get(TipoRegraEnum.REGRA_DISTRIBUICAO);
    }

    /**
     * @return
     */
    public List<Regra> recuperarRegrasRestricao() {
        this.iniciaMapaDeRegras();
        return this.mapaDeRegras.get(TipoRegraEnum.REGRA_RESTRICAO);
    }

    /**
     *
     * @return
     */
    public List<Regra> recuperarRegrasComposicao() {
        this.iniciaMapaDeRegras();
        return this.mapaDeRegras.get(TipoRegraEnum.REGRA_COMPOSICAO);
    }

    /**
     * @return
     */
    public List<Regra> recuperarRegrasAcaoCobranca() {
        this.iniciaMapaDeRegras();
        return this.mapaDeRegras.get(TipoRegraEnum.REGRA_ACAO_COBRANCA);
    }

    /**
     * @return
     */
    public List<Regra> recuperarRegrasCampanha() {
        this.iniciaMapaDeRegras();
        return this.mapaDeRegras.get(TipoRegraEnum.REGRA_CAMPANHA);
    }

    /**
     * @return
     */
    public Convenio recuperarConvenio(Long idConvenio) {
        this.iniciaMapaDeConvenios();
        return this.mapaDeConvenios.get(idConvenio);
    }

    /**
     * @return the dataApuracao
     */
    public Date getDataApuracao() {
        return DateUtil.duplicaObjetoData(dataApuracao);
    }

    /**
     * @return the listaContratosDistribuidosSimples
     */
    public List<ContratoDistribuicaoModel> getListaContratosDistribuidosSimples() {
        return listaContratosDistribuidosSimples;
    }

    /**
     * @param contratoSimples
     *            the adicionaContratoDistribuidoSimples to set
     */
    public static synchronized void adicionaContratoDistribuidoSimples(ContratoDistribuicaoModel contratoSimples) {
        listaContratosDistribuidosSimples.add(contratoSimples);
    }

    /**
     * @return the listaContratosDistribuidosMultiplos
     */
    public List<ContratoDistribuicaoModel> getListaContratosDistribuidosMultiplos() {
        return listaContratosDistribuidosMultiplos;
    }

    /**
     * @param contratoMultiplo
     */
    public static synchronized void adicionaContratoDistribuidoMultiplo(ContratoDistribuicaoModel contratoMultiplo) {
        listaContratosDistribuidosMultiplos.add(contratoMultiplo);
        adicionarContratoEnquadradosMultiplosConvenios(contratoMultiplo);
    }

    /*
     * Adicionar Contrato Multiplos
     */
    private static void adicionarContratoEnquadradosMultiplosConvenios(ContratoDistribuicaoModel contratoDistribuicao) {
        String codigoConvenios = "";

        List<Long> convenios = new ArrayList<Long>();

        for (ContratoDistribuicaoRegra cdrRegra : contratoDistribuicao.recuperaRegraEnquadrada(TipoRegraEnum.REGRA_DISTRIBUICAO)) {
            Convenio convenio = ((RegraDistribuicao) cdrRegra.getRegra()).getConvenio();
//            convenios.add(convenio.getId());
        }

        Collections.sort(convenios);

        for (Long idConvenio : convenios) {
            codigoConvenios = codigoConvenios.toString() + idConvenio + ";";
        }

        List<ContratoDistribuicaoModel> contratosDistribuicao = mapaContratosPorConveniosEnquadrados.get(codigoConvenios);

        if (contratosDistribuicao == null) {
            contratosDistribuicao = new ArrayList<ContratoDistribuicaoModel>();
        }
        contratosDistribuicao.add(contratoDistribuicao);

        mapaContratosPorConveniosEnquadrados.put(codigoConvenios, contratosDistribuicao);

    }

    /**
     * @return the mapaContratosPorConveniosEnquadrados
     */
    public Map<String, List<ContratoDistribuicaoModel>> getContratosPorConveniosEnquadrados() {
        return mapaContratosPorConveniosEnquadrados;
    }

    /**
     * @return the conveniosMultiplosCorrente
     */
    public List<Convenio> getConveniosMultiplosCorrente() {
        return conveniosMultiplosCorrente;
    }

    /**
     * @param conveniosMultiplosCorrente
     *            the conveniosMultiplosCorrente to set
     */
    public void setConveniosMultiplosCorrente(List<Convenio> conveniosMultiplosCorrente) {
        this.conveniosMultiplosCorrente = conveniosMultiplosCorrente;

        iniciaArrayConveniosEValores();
    }

    private void iniciaArrayConveniosEValores() {
        posicaoConvenioCorrente = 0;
        arrayConveniosMultiplosCorrente = null;
        valoresDistribuidosPorConvenio = null;

        if (getConveniosMultiplosCorrente() != null) {
            int posicaoAtual = 0;
            arrayConveniosMultiplosCorrente = new Convenio[getConveniosMultiplosCorrente().size()];
            valoresDistribuidosPorConvenio = new TreeMap<Long, Map<String, Double>>();

            for (Convenio convenio : getConveniosMultiplosCorrente()) {
                arrayConveniosMultiplosCorrente[posicaoAtual++] = convenio;
//                valoresDistribuidosPorConvenio.put(convenio.getId(), new HashMap<String, Double>());
            }
        }
    }

    /**
     * @return the valoresDistribuidosPorConvenio
     */
    public Map<Long, Map<String, Double>> getValoresDistribuidosPorConvenio() {
        return valoresDistribuidosPorConvenio;
    }

    /**
     * adicionar Valor ao Convênio e Criterio de Distribuicao Ordem.
     *
     * @param idConvenio
     * @param valoresAtributosOrdem
     * @param valorDivida
     */
    public void adicionarValorDistribuidosPorConvenio(Long idConvenio, String valoresAtributosOrdem, Double valorDivida) {
        Map<String, Double> valorDistribuidoPorConvenioECriterios = valoresDistribuidosPorConvenio.get(idConvenio);
        Double valorDistribuido = valorDistribuidoPorConvenioECriterios.get(getValorAtributosSemValorDivida(valoresAtributosOrdem));

        if (valorDistribuido == null) {
            valorDistribuidoPorConvenioECriterios = new HashMap<String, Double>();
            valorDistribuido = 0.0D;
        }

        valorDistribuidoPorConvenioECriterios.put(getValorAtributosSemValorDivida(valoresAtributosOrdem), valorDistribuido + valorDivida);
        valoresDistribuidosPorConvenio.put(idConvenio, valorDistribuidoPorConvenioECriterios);
    }

    /**
     * Obter o próximo convênio que deverá receber Contrato.
     *
     * @param valorDivida
     * @return
     */
    public Convenio proximoConvenio(Double valorDivida, String valoresAtributosOrdem) {

        if (posicaoConvenioCorrente == arrayConveniosMultiplosCorrente.length) {
            posicaoConvenioCorrente = 0;
        }

        Convenio convenio = arrayConveniosMultiplosCorrente[posicaoConvenioCorrente];
//        Map<String, Double> valoresDistribuidosPorConvenioECriterios = valoresDistribuidosPorConvenio.get(convenio.getId());
//        Double valorDistribuido = valoresDistribuidosPorConvenioECriterios.get(getValorAtributosSemValorDivida(valoresAtributosOrdem));
//        valorDistribuido = valorDistribuido != null ? valorDistribuido : 0.0D;
        posicaoConvenioCorrente++;

//        if (valorDistribuido <= this.getValorMedioValoresPorCriteriosOrdenacao(valoresAtributosOrdem)) {
            return convenio;
//        } else {
//            return proximoConvenio(valorDivida, valoresAtributosOrdem);
//        }
    }

    /**
     * @return the mapaValorMedioPorCriteriosOrdenacao
     */
    public Map<String, Double> getSomatorioValorDividaPorCriteriosOrdenacao() {
        iniciaMapaValorMedioPorCriteriosOrdenacao();
        return mapaSomatorioValorDividaPorCriteriosOrdenacao;
    }

    /**
     * @return the valorMedioValoresPorConvenioCorrente
     */
    public Double getValorMedioValoresPorCriteriosOrdenacao(String valoresAtributosOrdem) {
        return this.getSomatorioValorDividaPorCriteriosOrdenacao().get(getValorAtributosSemValorDivida(valoresAtributosOrdem))
                / (double) getConveniosMultiplosCorrente().size();
    }


    public void adicionarValorDividaPorCriteriosOrdenacao(String valoresAtributosOrdem, Double valorDivida) {
        iniciaMapaValorMedioPorCriteriosOrdenacao();
        String valoresAtributosOrdemSemValorDivida = getValorAtributosSemValorDivida(valoresAtributosOrdem);

        Double valorDividaCriterio =
                this.getSomatorioValorDividaPorCriteriosOrdenacao().get(valoresAtributosOrdemSemValorDivida) != null
                        ? this.getSomatorioValorDividaPorCriteriosOrdenacao().get(valoresAtributosOrdemSemValorDivida) : 0.0D;
        this.getSomatorioValorDividaPorCriteriosOrdenacao().put(valoresAtributosOrdemSemValorDivida, valorDividaCriterio + valorDivida);
    }

    private String getValorAtributosSemValorDivida(String valoresAtributosOrdem) {
        String[] valoresAtributos = valoresAtributosOrdem.split(";");
        String valoresAtributosOrdemSemValorDivida = "";

        for (int i = 0; i < valoresAtributos.length - 1; i++) {
            if (this.getPosicaoCampoValorDividaCorrente() != i) {
                valoresAtributosOrdemSemValorDivida += valoresAtributos[i] != null ? valoresAtributos[i] + ";" : ";";
            }
        }
        return valoresAtributosOrdemSemValorDivida;
    }

    /**
     * Inicia o mapa de regras por tipo.
     */
    private void iniciaMapaValorMedioPorCriteriosOrdenacao() {
        if (this.mapaSomatorioValorDividaPorCriteriosOrdenacao == null) {
            this.mapaSomatorioValorDividaPorCriteriosOrdenacao = new HashMap<String, Double>();
        }
    }

    /**
     * Inicia o mapa de regras por tipo.
     */
    public void reiniciaMapaValorMedioPorCriteriosOrdenacao() {
        this.mapaSomatorioValorDividaPorCriteriosOrdenacao = null;
        iniciaMapaValorMedioPorCriteriosOrdenacao();
    }

    public int getPosicaoCampoValorDividaCorrente() {
        return posicaoCampoValorDividaCorrente;

    }

    public void setPosicaoCampoValorDividaCorrente(int posicaoCampoValorDividaCorrente) {
        this.posicaoCampoValorDividaCorrente = posicaoCampoValorDividaCorrente;
    }
}
