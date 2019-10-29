package unisys.thbd.sigma.models;

import java.util.List;

public class CondicaoSimplesRegra extends EntidadeAuditavelCobrancaTerceirizada implements CondicaoRegra {

//    private static final long serialVersionUID = 1L;
//    private Long id;

    private CondicaoCompostaRegra condicaoComposicao;

    private Atributo atributo;

    private String operador;

    private String valor;

    private Integer ordem;

    private String operadorLogico;

    @Override
    public Boolean avaliarCondicao(AmbienteAvaliacaoRegra ambiente) {
        if (this.atributo.isComposicao()) {
            return avaliarAtributoComposto(ambiente);

        }
        List<DadoContratoCobrancaTerceirizada> listaDado = ambiente.getDadoContratoCobTerc(this.atributo.getAtributo());
        if (listaDado == null) {
            return Boolean.FALSE;
        }
        String valorRegra = this.valor;
        for (DadoContratoCobrancaTerceirizada dadoContrato : listaDado) {
            String valorContrato = dadoContrato.getValor();
            if (valorContrato == null || valorRegra == null) {
                continue;
            }
            if (getTipoOperadorRelacionalEnum().getOperadorRelacional().avaliarCondicao(atributo, valorRegra,
                    valorContrato)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private Boolean avaliarAtributoComposto(AmbienteAvaliacaoRegra ambiente) {
        RegraComposicao regraComposta = ambiente.recuperarRegraComposicao(this.atributo, this.valor);
        if (regraComposta == null) {
            return false;
        }
        return regraComposta.avaliarCondicao(ambiente);
    }

    @Override
    public OperadorLogico getOperadorLogico() {
        return this.getOperadorLogicoEnum().getOperadorLogico();
    }

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * Obter a Composicao Composta da Regra.
     *
     * @return the condicaoComposicao
     */
    public CondicaoCompostaRegra getCondicaoComposicao() {
        return condicaoComposicao;
    }

    /**
     * Alterar a Composicao Composta da Regra.
     *
     * @param condicaoComposicao
     *            the condicaoComposicao to set
     */
    public void setCondicaoComposicao(CondicaoCompostaRegra condicaoComposicao) {
        this.condicaoComposicao = condicaoComposicao;
        if (this.condicaoComposicao != null) {
            this.condicaoComposicao.adicionaCondicaoSimples(this);
        }
    }

    /**
     * Obter o Atributo da Condição Simples.
     *
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * Alterar o Atributo da Condição Simples.
     *
     * @param atributo
     *            the atributo to set
     */
    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public TipoOperadorRelacionalEnum getTipoOperadorRelacionalEnum() {
        return TipoOperadorRelacionalEnum.getTipoOperadorRelacionalEnum(operador);
    }

    /**
     * Alterar o operador Relacionado da Condição Simples da Regra.
     *
     * @param operador
     *            the operador to set
     */
    public void setTipoOperadorRelacionalEnum(TipoOperadorRelacionalEnum operador) {
        if (operador != null) {
            this.operador = operador.getSimbolo();
        } else {
            this.operador = null;
        }
    }

    /**
     * Obter o valor da Condição Simples da Regra.
     *
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * Alterar o valor da Condição Simples da Regra.
     *
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Obter a ordem da Condição Simples da Regra.
     *
     * @return the ordem
     */
    public Integer getOrdem() {
        return ordem;
    }

    /**
     * Alterar a ordem da Condição Simples da Regra.
     *
     * @param ordem
     *            the ordem to set
     */
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public TipoOperadorLogicoEnum getOperadorLogicoEnum() {
        return TipoOperadorLogicoEnum.getTipoOperadorCondicaoEnum(operadorLogico);
    }

    /**
     * Alterar a Operador Logico da Condição Simples da Regra.
     *
     * @param operadorLogico
     *            the operadorLogico to set
     */
    public void setOperadorLogicoEnum(TipoOperadorLogicoEnum operadorLogico) {
        if (operadorLogico != null) {
            this.operadorLogico = operadorLogico.getOperador();
        } else {
            this.operadorLogico = null;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((operador == null) ? 0 : operador.hashCode());
        result = prime * result + ((operadorLogico == null) ? 0 : operadorLogico.hashCode());
        result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!(obj instanceof CondicaoSimplesRegra)) {
            return false;
        }
        CondicaoSimplesRegra other = (CondicaoSimplesRegra) obj;
        if (atributo == null) {
            if (other.atributo != null) {
                return false;
            }
        } else if (!atributo.equals(other.atributo)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (operador == null) {
            if (other.operador != null) {
                return false;
            }
        } else if (!operador.equals(other.operador)) {
            return false;
        }
        if (operadorLogico == null) {
            if (other.operadorLogico != null) {
                return false;
            }
        } else if (!operadorLogico.equals(other.operadorLogico)) {
            return false;
        }
        if (ordem == null) {
            if (other.ordem != null) {
                return false;
            }
        } else if (!ordem.equals(other.ordem)) {
            return false;
        }
        if (valor == null) {
            if (other.valor != null) {
                return false;
            }
        } else if (!valor.equals(other.valor)) {
            return false;
        }
        return true;
    }
}
