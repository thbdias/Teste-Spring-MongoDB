package io.codementor.gtommee.rest_tutorial.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContratoCobrancaTerceirizada extends EntidadeCobrancaTerceirizada {

    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;
    private ContratoDistribuicaoModel contratoDistribuicao;
    private String numeroContrato;
    private Integer diasAtraso;
    private Double valorDivida;
    private Double valorDividaAtualizada;
    private Date dataInclusao;
    private Date dataAtualizacaoValorDivida;
    private List<DadoContratoCobrancaTerceirizada> dadosContratoCobrancaTerceirizada;
    private Long execucaoSelecao;
    private Long execucaoDistribuicao;
    private Map<String, DadoContratoCobrancaTerceirizada> mapDadosContratoCobrancaTerceirizada = null;
    private Map<String, DadoContratoCobrancaTerceirizada> dadosSistemicosContratoCobrancaTerceirizada = null;
    private String descricaoEncerramento;

    public String getDataInclusaoFormatada() {
        if (this.dataInclusao != null) {
            return DateUtil.formataData(this.dataInclusao);
        }
        return "";
    }

    /**
     * Retorna a lista de Filhos de acordo com o atributo.
     *
     * @param codigoAtributo
     * @return
     */
    public List<DadoContratoCobrancaTerceirizada> recuperaListaFilhos(String codigoAtributo) {

        List<DadoContratoCobrancaTerceirizada> dados = new ArrayList<DadoContratoCobrancaTerceirizada>();

        for (DadoContratoCobrancaTerceirizada p : dadosContratoCobrancaTerceirizada) {
            if (p.getDadoContratoCobrancaTerceirizadaPai() != null
                    && p.getDadoContratoCobrancaTerceirizadaPai().getAtributo().getAtributo().equals(codigoAtributo)) {
                dados.add(p);
            }
        }

//        Collections.sort(dados, new Comparator<DadoContratoCobrancaTerceirizada>() {
//
//            @Override
//            public int compare(DadoContratoCobrancaTerceirizada o1, DadoContratoCobrancaTerceirizada o2) {
//
//                return o1.getId() > o2.getId() ? 1 : -1;
//            }
//        });

        return dados;

    }

    /**
     * Retorna o enderco completo do imóvel do contrato.
     *
     * @return
     */
    public String enderecoImovel() {
        String enderecoImovel = "";

        DadoContratoCobrancaTerceirizada tipoLog =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.TIPO_LOGRADOURO_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada logra =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.LOGRADOURO_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada numero =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.NUMERO_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada bairro =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.BAIRRO_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada cidade = mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.NOME_MUNICIPIO.getCodigo());
        DadoContratoCobrancaTerceirizada uF = mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.UF_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada cep = mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.CEP_ENDERECO.getCodigo());
        DadoContratoCobrancaTerceirizada complemento =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.COMPLEMENTO_ENDERECO.getCodigo());

        enderecoImovel = montarEndereco(tipoLog != null ? tipoLog.getValor() : "", logra != null ? logra.getValor() : "",
                numero != null ? numero.getValor() : "", bairro != null ? bairro.getValor() : "",
                cidade != null ? cidade.getValor() : "", uF != null ? uF.getValor() : "", cep != null ? cep.getValor() : "",
                complemento != null ? complemento.getValor() : "");

        return enderecoImovel;

    }

    private String montarEndereco(String tipoLog, String logra, String numero, String bairro, String cidade, String uF, String cep,
                                  String complemento) {
        if (complemento == null || "".equals(complemento)) {
            return tipoLog + logra.trim() + ", " + numero.trim() + " - " + bairro.trim() + " - " + cidade.trim() + "/" + uF.trim() + " CEP "
                    + Util.formataCEP(cep.trim());
        } else {
            return tipoLog + logra.trim() + ", " + numero.trim() + " - " + complemento.trim() + " - " + bairro.trim() + " - "
                    + cidade.trim() + "/" + uF.trim() + " CEP " + Util.formataCEP(cep.trim());
        }
    }

    /**
     * Retorna o enderco completo de correspondencia do contrato.
     *
     * @return
     */
    public String enderecoCorrespondencia() {

        DadoContratoCobrancaTerceirizada tipoLog =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.TIPO_LOGRADOURO_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada logra =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.LOGRADOURO_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada numero =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.NUMERO_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada bairro =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.BAIRRO_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada cidade =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.NOME_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada uF =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.UF_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada cep =
                getMapDadosContratoCobrancaTerceirizada().get(DadosContratoEnum.CEP_CORRESPONDENCIA.getCodigo());
        DadoContratoCobrancaTerceirizada complemento =
                mapDadosContratoCobrancaTerceirizada.get(DadosContratoEnum.COMPLEMENTO_ENDERECO.getCodigo());

        if (logra != null) {
            return montarEndereco(tipoLog != null ? tipoLog.getValor() : "", logra.getValor(),
                    numero != null ? numero.getValor() : "", bairro != null ? bairro.getValor() : "",
                    cidade != null ? cidade.getValor() : "", uF != null ? uF.getValor() : "", cep != null ? cep.getValor() : "",
                    complemento != null ? complemento.getValor() : "");
        } else {
            return "";
        }

    }

    /**
     * Recupera valor Atributo Formatado em String.
     *
     * @param codigoAtributo
     * @return
     */
    public String recuperarDadoFormatado(String codigoAtributo) {
        DadoContratoCobrancaTerceirizada atr = getMapDadosContratoCobrancaTerceirizada().get(codigoAtributo);
        return atr != null ? ConversorAtributo.atributoToString(atr.getAtributo(), atr.getValor()) : "";
    }

    /**
     * Recupera valor Atributo Formatado exibição Tela DD/MM/AAAA.
     *
     * @param codigoAtributo
     * @return
     */
    public String recuperarDadoDataFormatadoSeparador(String codigoAtributo) {
        DadoContratoCobrancaTerceirizada atr = getMapDadosContratoCobrancaTerceirizada().get(codigoAtributo);
        Date data = null;
        if (atr.getAtributo().getTipoDados().equals(TipoDadosEnum.DATA)) {
            DecoratorUtil.DateDecorator dec = new DecoratorUtil.DateDecorator();
            try {
                data = dec.fromString(atr.getValor());
            } catch (DecoratorException e) {
                e.printStackTrace();
            }
        }

        return DateUtil.formataData(data);
    }

    /**
     * Recupera valor Atributo.
     *
     * @param codigoAtributo
     * @return
     */
    public DadoContratoCobrancaTerceirizada recuperaDadoContrato(String codigoAtributo) {

        return getMapDadosContratoCobrancaTerceirizada().get(codigoAtributo) != null
                ? getMapDadosContratoCobrancaTerceirizada().get(codigoAtributo) : new DadoContratoCobrancaTerceirizada();
    }

    /**
     * Obter Id do Contrato de Cobrança Terceirizada.
     *
     * @return the id
     */
//    public Long getId() {
//        return id;
//    }

    /**
     * Alterar Id do Contrato de Cobrança Terceirizada.
     *
     * @param id
     *            the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Obter Cliente de Cobrança Terceirizada que o Contrato de Cobrança
     * Terceirizada está vinculado.
     *
     * @return the cliente
     */
    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }

    /**
     * Alterar Cliente de Cobrança Terceirizada que o Contrato de Cobrança
     * Terceirizada está vinculado.
     *
     * @param clienteCobrancaTerceirizada
     *            the cliente to set
     */
    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
    }

    /**
     * Obter Número do Contrato de Cobrança.
     *
     * @return the numeroContrato
     */
    public String getNumeroContrato() {
        if (this.numeroContrato == null) {
            this.numeroContrato = getValorAtributo(AtributoSistemaEnum.CONTRATO.getAtributo());
        }
        return this.numeroContrato;
    }

    /**
     * Alterar Número do Contrato de Cobrança.
     *
     * @param numeroContrato
     *            the numeroContrato to set
     */
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    /**
     * Obter quantidade de Dias em atraso do Contrato de Cobrança.
     *
     * @return
     */
    public Integer getDiasAtraso() {
        if (this.diasAtraso == null) {
            this.diasAtraso = AtributoSistemaUtil.toInteger(getValorAtributo(AtributoSistemaEnum.DIAS_ATRASO.getAtributo()));
        }
        return this.diasAtraso;
    }

    /**
     * Alterar quantidade de Dias em atraso do Contrato de Cobrança.
     *
     * @param diasAtraso
     *            the diasAtraso to set
     */
    public void setDiasAtraso(Integer diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    /**
     * Obter Valor da divida do Contrato de Cobrança.
     *
     * @return the valorDivida
     */
    public Double getValorDivida() {
        if (this.valorDivida == null) {
            this.valorDivida = AtributoSistemaUtil.toDouble(getValorAtributo(AtributoSistemaEnum.VALOR_DIVIDA.getAtributo()));
        }
        return this.valorDivida;
    }

    /**
     * Alterar quantidade de Dias em atraso que do Contrato de Cobrança.
     *
     * @param valorDivida
     *            the valorDivida to set
     */
    public void setValorDivida(Double valorDivida) {
        this.valorDivida = valorDivida;
    }

    /**
     * Obter Valor da divida Atualizada do Contrato de Cobrança.
     *
     * @return the valorDividaAtualizada
     */
    public Double getValorDividaAtualizada() {
        return this.valorDividaAtualizada;
    }

    /**
     * Atribuir Valor Divida Atualizada do Contrato de Cobrança.
     *
     * @param valorDividaAtualizada the valorDivida to set
     */
    public void setValorDividaAtualizada(Double valorDividaAtualizada) {
        this.valorDividaAtualizada = valorDividaAtualizada;
    }

    /**
     * @return the dataInclusao
     */
    public Date getDataInclusao() {
        if (dataInclusao == null) {
            return null;
        }
        return new Date(dataInclusao.getTime());
    }

    /**
     * @param dataInclusao
     *            the dataInclusao to set
     */
    public void setDataInclusao(Date dataInclusao) {
        if (dataInclusao == null) {
            this.dataInclusao = null;
        } else {
            this.dataInclusao = new Date(dataInclusao.getTime());
        }
    }

    /**
     * @return the dataInclusao
     */
    public Date getDataAtualizacaoValorDivida() {
        if (dataAtualizacaoValorDivida == null) {
            return null;
        }
        return new Date(dataAtualizacaoValorDivida.getTime());
    }

    /**
     * @param dataAtualizacaoValorDivida
     *            the dataInclusao to set
     */
    public void setDataAtualizacaoValorDivida(Date dataAtualizacaoValorDivida) {
        if (dataAtualizacaoValorDivida == null) {
            this.dataAtualizacaoValorDivida = null;
        } else {
            this.dataAtualizacaoValorDivida = new Date(dataAtualizacaoValorDivida.getTime());
        }
    }

    /**
     * Obter a Lista de Dados do Contrato de Cobrança.
     *
     * @return the dadosContrato
     */
    public List<DadoContratoCobrancaTerceirizada> getDadosContratoCobrancaTerceirizada() {
        iniciaDadosContratoCobrancaTerceirizada();
        return dadosContratoCobrancaTerceirizada;
    }

    /**
     * Alterar a Lista de Dados do Contrato de Cobrança.
     *
     * @param dadosContratoCobrancaTerceirizada
     *            the dadosContrato to set
     */
    public void setDadosContratoCobrancaTerceirizada(List<DadoContratoCobrancaTerceirizada> dadosContratoCobrancaTerceirizada) {
        this.dadosContratoCobrancaTerceirizada = dadosContratoCobrancaTerceirizada;
    }

    /*
     * Inicializa a lista de dados do contrato.
     */
    private void iniciaDadosContratoCobrancaTerceirizada() {
        if (this.dadosContratoCobrancaTerceirizada == null) {
            this.dadosContratoCobrancaTerceirizada = new ArrayList<DadoContratoCobrancaTerceirizada>();
        }
    }

    /**
     * Metodo para adicionar a lista um novo registro de dado do contrato.
     *
     * @param dadoContratoCobrancaTerceirizada
     */
    public void adicionaDadosContratoCobrancaTerceirizada(DadoContratoCobrancaTerceirizada dadoContratoCobrancaTerceirizada) {
        iniciaDadosContratoCobrancaTerceirizada();
        this.dadosContratoCobrancaTerceirizada.add(dadoContratoCobrancaTerceirizada);
    }

    /**
     * Metodo para remover da lista um registro de dado do contrato.
     *
     * @param dadoContratoCobrancaTerceirizada
     */
    public void removeDadosContratoCobrancaTerceirizada(DadoContratoCobrancaTerceirizada dadoContratoCobrancaTerceirizada) {
        iniciaDadosContratoCobrancaTerceirizada();
        if (this.dadosContratoCobrancaTerceirizada.contains(dadoContratoCobrancaTerceirizada)) {
            this.dadosContratoCobrancaTerceirizada.remove(dadoContratoCobrancaTerceirizada);
        }
    }

    /**
     * Recuperar dados sistemicos da ContratoCobrancaTerceirizada.
     *
     * @return the dadosSistemicosContratoCobrancaTerceirizada
     */
    private String getValorAtributo(String idAtributoSistema) {
        DadoContratoCobrancaTerceirizada dadoContrato = null;
        String valorAtributo = null;

        dadosSistemicosContratoCobrancaTerceirizada = new HashMap<String, DadoContratoCobrancaTerceirizada>();

        for (DadoContratoCobrancaTerceirizada dadoContratoCobrancaTerceirizada : getDadosContratoCobrancaTerceirizada()) {
            if (dadoContratoCobrancaTerceirizada != null && dadoContratoCobrancaTerceirizada.getAtributo().isAtributoSistemico()) {
                dadosSistemicosContratoCobrancaTerceirizada.put(
                        dadoContratoCobrancaTerceirizada.getAtributo().getAtributoSistema().getAtributo(),
                        dadoContratoCobrancaTerceirizada);
            }
        }
        dadoContrato = dadosSistemicosContratoCobrancaTerceirizada.get(idAtributoSistema);

        if (dadoContrato != null) {
            valorAtributo = dadoContrato.getValor();
        }
        return valorAtributo;
    }

    /**
     * Atualizar todos os campos que são decorrentes de atributos de sistema.
     */
    public void atualizarAtributosSistemicos() {
        getNumeroContrato();
        getDiasAtraso();
        getValorDivida();
        setValorDividaAtualizada(getValorDivida());
    }

    /**
     * @return the execucaoSelecao
     */
    public Long getExecucaoSelecao() {
        return execucaoSelecao;
    }

    /**
     * @param execucaoSelecao
     *            the execucaoSelecao to set
     */
    public void setExecucaoSelecao(Long execucaoSelecao) {
        this.execucaoSelecao = execucaoSelecao;
    }

    /**
     * @return the execucaoDistribuicao
     */
    public Long getExecucaoDistribuicao() {
        return execucaoDistribuicao;
    }

    /**
     * @param execucaoDistribuicao
     *            the execucaoDistribuicao to set
     */
    public void setExecucaoDistribuicao(Long execucaoDistribuicao) {
        this.execucaoDistribuicao = execucaoDistribuicao;
    }

    public ContratoDistribuicaoModel getContratoDistribuicao() {
        return contratoDistribuicao;
    }

    public void setContratoDistribuicao(ContratoDistribuicaoModel contratoDistribuicao) {
        this.contratoDistribuicao = contratoDistribuicao;
    }

    /**
     * Retorna um map com os dados do contrato.
     *
     * @return
     */
    public Map<String, DadoContratoCobrancaTerceirizada> getMapDadosContratoCobrancaTerceirizada() {
        if (mapDadosContratoCobrancaTerceirizada == null) {

            mapDadosContratoCobrancaTerceirizada =
                    new HashMap<String, DadoContratoCobrancaTerceirizada>(dadosContratoCobrancaTerceirizada.size());
            for (DadoContratoCobrancaTerceirizada i : dadosContratoCobrancaTerceirizada) {
                mapDadosContratoCobrancaTerceirizada.put(i.getAtributo().getAtributo(), i);
            }
        }
        return mapDadosContratoCobrancaTerceirizada;
    }

    public void setMapDadosContratoCobrancaTerceirizada(
            Map<String, DadoContratoCobrancaTerceirizada> mapDadosContratoCobrancaTerceirizada) {
        this.mapDadosContratoCobrancaTerceirizada = mapDadosContratoCobrancaTerceirizada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clienteCobrancaTerceirizada == null) ? 0 : clienteCobrancaTerceirizada.hashCode());
        result = prime * result + ((dadosContratoCobrancaTerceirizada == null) ? 0 : dadosContratoCobrancaTerceirizada.hashCode());
        result = prime * result + ((diasAtraso == null) ? 0 : diasAtraso.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((numeroContrato == null) ? 0 : numeroContrato.hashCode());
        result = prime * result + ((valorDivida == null) ? 0 : valorDivida.hashCode());
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
        if (!(obj instanceof ContratoCobrancaTerceirizada)) {
            return false;
        }
        ContratoCobrancaTerceirizada other = (ContratoCobrancaTerceirizada) obj;
        if (clienteCobrancaTerceirizada == null) {
            if (other.clienteCobrancaTerceirizada != null) {
                return false;
            }
        } else if (!clienteCobrancaTerceirizada.equals(other.clienteCobrancaTerceirizada)) {
            return false;
        }
        if (diasAtraso == null) {
            if (other.diasAtraso != null) {
                return false;
            }
        } else if (!diasAtraso.equals(other.diasAtraso)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (numeroContrato == null) {
            if (other.numeroContrato != null) {
                return false;
            }
        } else if (!numeroContrato.equals(other.numeroContrato)) {
            return false;
        }
        if (valorDivida == null) {
            if (other.valorDivida != null) {
                return false;
            }
        } else if (!valorDivida.equals(other.valorDivida)) {
            return false;
        }
        return true;
    }

    /**
     * Retorna a data final para cobrança quando não existir data de permanencia.
     * @return
     */
    public Date getDataFinalCobranca() {
        if (getContratoDistribuicao().getConvenio().getPermanencia() == null) {
            return null;
        }
        return DateUtil.adicionaDias(getDataInclusao(),
                Integer.valueOf(getContratoDistribuicao().getConvenio().getPermanencia().intValue()));
    }

}
