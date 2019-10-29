package unisys.thbd.sigma.models;

public class DadoContratoDistribuicao extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;

//    private Long id;

    private ContratoDistribuicaoRegra contratoDistribuicaoRegra;

    private Atributo atributo;

    private String valor;

    /**
     * @return the id
     */
//    public Long getId() {
//        return id;
//    }

    /**
     * @param id the id to set
     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * @param atributo the atributo to set
     */
    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the contratoDistribuicaoRegra
     */
    public ContratoDistribuicaoRegra getContratoDistribuicaoRegra() {
        return contratoDistribuicaoRegra;
    }

    /**
     * @param contratoDistribuicaoRegra the contratoDistribuicaoRegra to set
     */
    public void setContratoDistribuicaoRegra(ContratoDistribuicaoRegra contratoDistribuicaoRegra) {
        if (contratoDistribuicaoRegra != null) {
            if (this.contratoDistribuicaoRegra != null && !this.contratoDistribuicaoRegra.equals(contratoDistribuicaoRegra)) {
                this.contratoDistribuicaoRegra.removeDadoContratoDistribuicao(this);
            }
            this.contratoDistribuicaoRegra = contratoDistribuicaoRegra;
            this.contratoDistribuicaoRegra.adicionaDadoContratoDistribuicao(this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
        result = prime * result + ((contratoDistribuicaoRegra == null) ? 0 : contratoDistribuicaoRegra.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
        if (!(obj instanceof DadoContratoDistribuicao)) {
            return false;
        }
        DadoContratoDistribuicao other = (DadoContratoDistribuicao) obj;
        if (atributo == null) {
            if (other.atributo != null) {
                return false;
            }
        } else if (!atributo.equals(other.atributo)) {
            return false;
        }
        if (contratoDistribuicaoRegra == null) {
            if (other.contratoDistribuicaoRegra != null) {
                return false;
            }
        } else if (!contratoDistribuicaoRegra.equals(other.contratoDistribuicaoRegra)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
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
