package unisys.thbd.sigma.models;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.List;

public class CondicaoCompostaRegra extends EntidadeAuditavelCobrancaTerceirizada
        implements CondicaoRegra {

//    private static final long serialVersionUID = 1L;
//    private Long id;
    private Regra regra;
    private Integer ordem;
    private String operadorLogico;
    private List<CondicaoSimplesRegra> condicoesSimples;

    @Override
    public Boolean avaliarCondicao(AmbienteAvaliacaoRegra ambiente) {
        Collections.sort(this.getCondicoesSimples(), new Comparator<CondicaoSimplesRegra>() {
            @Override
            public int compare(CondicaoSimplesRegra c1, CondicaoSimplesRegra c2) {
                return c1.getOrdem().compareTo(c2.getOrdem());
            }
        });
        Boolean resultado = null;
        CondicaoSimplesRegra condicaoAnterior = null;
        for (CondicaoSimplesRegra condicao : this.getCondicoesSimples()) {
            Boolean resultadoParcial = condicao.avaliarCondicao(ambiente);
            if (condicaoAnterior != null) {
                resultado = condicaoAnterior.getOperadorLogico().avaliarCondicao(resultado, resultadoParcial);
            } else {
                resultado = resultadoParcial;
            }
            condicaoAnterior = condicao;
        }
        return resultado;
    }

    @Override
    public OperadorLogico getOperadorLogico() {
        return this.getOperadorLogicoEnum().getOperadorLogico();
    }

    /**
     * Obter o Id da CondicaoComposta.
     *
     * @return the id
     */
//    public Long getId() {
//        return id;
//    }

    /**
     * @param id
     *            the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * @return the regra
     */
    public Regra getRegra() {
        return regra;
    }

    /**
     * @param regra
     *            the regra to set
     */
    public void setRegra(Regra regra) {
        this.regra = regra;
        if (this.regra != null) {
            this.regra.adicionaCondicaoComposta(this);
        }
    }

    /**
     * @return the ordem
     */
    public Integer getOrdem() {
        return ordem;
    }

    /**
     * @param ordem
     *            the ordem to set
     */
    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    /**
     * @return the operadorLogico
     */
    public TipoOperadorLogicoEnum getOperadorLogicoEnum() {
        return TipoOperadorLogicoEnum.getTipoOperadorCondicaoEnum(operadorLogico);
    }

    /**
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

    /**
     * @return the condicoesSimples
     */
    public List<CondicaoSimplesRegra> getCondicoesSimples() {
        iniciaCondicoesSimples();

        Collections.sort(condicoesSimples, new Comparator<CondicaoSimplesRegra>() {
            @Override
            public int compare(CondicaoSimplesRegra csr1, CondicaoSimplesRegra csr2) {
                if (csr1 == null || csr2 == null) {
                    return 0;
                }
                return csr1.getOrdem().compareTo(csr2.getOrdem());
            }
        });

        return condicoesSimples;
    }

    /**
     * @param condicoesSimples
     *            the condicoesSimples to set
     */
    public void setCondicoesSimples(List<CondicaoSimplesRegra> condicoesSimples) {
        this.getCondicoesSimples().clear();
        this.adicionaTodasCondicoesSimples(condicoesSimples);
    }

    private void iniciaCondicoesSimples() {
        if (this.condicoesSimples == null) {
            this.condicoesSimples = new ArrayList<CondicaoSimplesRegra>();
        }
    }

    /**
     * @param condicao
     */
    public void adicionaCondicaoSimples(CondicaoSimplesRegra condicao) {
        iniciaCondicoesSimples();
        if (!this.condicoesSimples.contains(condicao)) {
            this.condicoesSimples.add(condicao);
            condicao.setCondicaoComposicao(this);
        }
    }

    public void adicionaTodasCondicoesSimples(List<CondicaoSimplesRegra> condicoes) {
        iniciaCondicoesSimples();
        if (condicoes != null) {
            for (CondicaoSimplesRegra condicao : condicoes) {
                this.adicionaCondicaoSimples(condicao);
            }
        }
    }

    /**
     * @param condicao
     */
    public void removeCondicaoSimples(CondicaoSimplesRegra condicao) {
        iniciaCondicoesSimples();
        this.condicoesSimples.remove(condicao);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((operadorLogico == null) ? 0 : operadorLogico.hashCode());
        result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
        result = prime * result + ((regra == null) ? 0 : regra.hashCode());
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
        if (!(obj instanceof CondicaoCompostaRegra)) {
            return false;
        }
        CondicaoCompostaRegra other = (CondicaoCompostaRegra) obj;
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
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
        if (regra == null) {
            if (other.regra != null) {
                return false;
            }
        } else if (!regra.equals(other.regra)) {
            return false;
        }
        return true;
    }

}
