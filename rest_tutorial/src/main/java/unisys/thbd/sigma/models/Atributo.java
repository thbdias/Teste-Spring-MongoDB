package unisys.thbd.sigma.models;

import java.util.List;

public class Atributo extends EntidadeAuditavelCobrancaTerceirizada {

//    private Long id;

    private String rotulo;
    private String atributo;
    private ClienteCobrancaTerceirizada clienteCobrancaTerceirizada;
    private Integer atributoSistema;
    private List<TipoDistribuicao> atributosCriteriosDistribuicao;
    private Atributo composicao;
    private Integer codigoTipoDados;
    private Boolean criterioDistribuicao;
    private Integer tamanhoMaximo;

//    @Override
//    public Long getId() {
//        return this.id;
//    }


//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public ClienteCobrancaTerceirizada getClienteCobrancaTerceirizada() {
        return clienteCobrancaTerceirizada;
    }


    public void setClienteCobrancaTerceirizada(ClienteCobrancaTerceirizada clienteCobrancaTerceirizada) {
        this.clienteCobrancaTerceirizada = clienteCobrancaTerceirizada;
    }


    public AtributoSistemaEnum getAtributoSistema() {
        return AtributoSistemaEnum.getAtributoSistemaEnum(atributoSistema);
    }


    public void setAtributoSistema(AtributoSistemaEnum atributoSistemaEnum) {
        if (atributoSistemaEnum == null) {
            this.atributoSistema = null;
        } else {
            this.atributoSistema = atributoSistemaEnum.getCodigo();
        }
    }

    public List<TipoDistribuicao> getAtributosCriteriosDistribuicao() {
        return atributosCriteriosDistribuicao;
    }

    public void setAtributosCriteriosDistribuicao(List<TipoDistribuicao> atributosCriteriosDistribuicao) {
        this.atributosCriteriosDistribuicao = atributosCriteriosDistribuicao;
    }


    public TipoDadosEnum getTipoDados() {
        return TipoDadosEnum.getTipoDadosEnum(codigoTipoDados);
    }

    /**
     * Alterar o Tipo de Metados.
     *
     * @param tipoDadosEnum the tipoDadosEnum to set
     */
    public void setTipoDados(TipoDadosEnum tipoDadosEnum) {
        if (tipoDadosEnum == null) {
            this.codigoTipoDados = null;
        } else {
            this.codigoTipoDados = tipoDadosEnum.getCodigo();
        }
    }

    /**
     * @return the criterioDistribuicao
     */
    public Boolean getCriterioDistribuicao() {
        return criterioDistribuicao;
    }

    /**
     * @param criterioDistribuicao the criterioDistribuicao to set
     */
    public void setCriterioDistribuicao(Boolean criterioDistribuicao) {
        this.criterioDistribuicao = criterioDistribuicao;
    }

    /**
     * Obter o Atributo da Composição.
     *
     * @return the composicao
     */
    public Atributo getComposicao() {
        return composicao;
    }

    /**
     * Alterar o Atributo da Composição.
     *
     * @param composicao the composicao to set
     */
    public void setComposicao(Atributo composicao) {
        this.composicao = composicao;
    }

    /**
     * Verifica se o Atributo é sistêmico.
     *
     * @return se o Atributo é sistêmico ou não.
     */
    public Boolean isAtributoSistemico() {
        return this.atributoSistema != null;
    }

    /**
     * Verifica se o atributo é composto.
     *
     * @return
     */
    public Boolean isComposicao() {
        return this.composicao != null;
    }

    /**
     * @return the tamanhoMaximo
     */
    public Integer getTamanhoMaximo() {
        return tamanhoMaximo;
    }

    /**
     * @param tamanhoMaximo the tamanhoMaximo to set
     */
    public void setTamanhoMaximo(Integer tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
        result = prime * result + ((clienteCobrancaTerceirizada == null) ? 0 : clienteCobrancaTerceirizada.hashCode());
        result = prime * result + ((codigoTipoDados == null) ? 0 : codigoTipoDados.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((atributoSistema == null) ? 0 : atributoSistema.hashCode());
        result = prime * result + ((rotulo == null) ? 0 : rotulo.hashCode());
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
        Atributo other = (Atributo) obj;
        if (atributo == null) {
            if (other.atributo != null) {
                return false;
            }
        } else if (!atributo.equals(other.atributo)) {
            return false;
        }
        if (clienteCobrancaTerceirizada == null) {
            if (other.clienteCobrancaTerceirizada != null) {
                return false;
            }
        } else if (!clienteCobrancaTerceirizada.equals(other.clienteCobrancaTerceirizada)) {
            return false;
        }
        if (codigoTipoDados == null) {
            if (other.codigoTipoDados != null) {
                return false;
            }
        } else if (!codigoTipoDados.equals(other.codigoTipoDados)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (atributoSistema == null) {
            if (other.atributoSistema != null) {
                return false;
            }
        } else if (!atributoSistema.equals(other.atributoSistema)) {
            return false;
        }
        if (rotulo == null) {
            if (other.rotulo != null) {
                return false;
            }
        } else if (!rotulo.equals(other.rotulo)) {
            return false;
        }
        return true;
    }

}
