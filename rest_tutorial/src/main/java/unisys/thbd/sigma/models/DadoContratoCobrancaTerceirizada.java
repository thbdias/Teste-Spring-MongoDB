package unisys.thbd.sigma.models;

public class DadoContratoCobrancaTerceirizada extends EntidadeCobrancaTerceirizada {

    private ContratoCobrancaTerceirizada contratoCobrancaTerceirizada;
    private Atributo atributo;
    private String valor;
    private DadoContratoCobrancaTerceirizada dadoContratoCobrancaTerceirizadaPai;

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public ContratoCobrancaTerceirizada getContratoCobrancaTerceirizada() {
        return contratoCobrancaTerceirizada;
    }

    /**
     * Alterar o Contrato de Cobrança Terceirizada.
     *
     * @param contratoCobrancaTerceirizada
     *            the contrato to set
     */
    public void setContratoCobrancaTerceirizada(ContratoCobrancaTerceirizada contratoCobrancaTerceirizada) {
        this.contratoCobrancaTerceirizada = contratoCobrancaTerceirizada;
    }

    /**
     * Obter o Atributo referenciado no Contrato de Cobrança Terceirizada.
     *
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * Alterar o Atributo referenciado no Contrato de Cobrança Terceirizada.
     *
     * @param atributo
     *            the atributo to set
     */
    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    /**
     * Obter o valor do Atributo referenciado no Contrato de Cobrança Terceirizada.
     *
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * Alterar o valor do Atributo referenciado no Contrato de Cobrança Terceirizada.
     *
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Obter o Dado do Contrato Cobrança Terceirizada Pai.
     *
     * @return the dadoContratoCobrancaTerceirizadaPai
     */
    public DadoContratoCobrancaTerceirizada getDadoContratoCobrancaTerceirizadaPai() {
        return dadoContratoCobrancaTerceirizadaPai;
    }

    /**
     * Alterar o Dado do Contrato Cobrança Terceirizada Pai.
     *
     * @param dadoContratoCobrancaTerceirizadaPai the dadoContratoCobrancaTerceirizadaPai to set
     */
    public void setDadoContratoCobrancaTerceirizadaPai(
            DadoContratoCobrancaTerceirizada dadoContratoCobrancaTerceirizadaPai) {
        this.dadoContratoCobrancaTerceirizadaPai = dadoContratoCobrancaTerceirizadaPai;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contratoCobrancaTerceirizada == null) ? 0 : contratoCobrancaTerceirizada.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
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
        if (!(obj instanceof DadoContratoCobrancaTerceirizada)) {
            return false;
        }
        DadoContratoCobrancaTerceirizada other = (DadoContratoCobrancaTerceirizada) obj;
        if (contratoCobrancaTerceirizada == null) {
            if (other.contratoCobrancaTerceirizada != null) {
                return false;
            }
        } else if (!contratoCobrancaTerceirizada.equals(other.contratoCobrancaTerceirizada)) {
            return false;
        }
//        if (id == null) {
//            if (other.id != null) {
//                return false;
//            }
//        } else if (!id.equals(other.id)) {
//            return false;
//        }
        if (atributo == null) {
            if (other.atributo != null) {
                return false;
            }
        } else if (!atributo.equals(other.atributo)) {
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DadoContratoCobrancaTerceirizada [id=");
//        builder.append(id);
        builder.append(", contratoCobrancaTerceirizada=");
        builder.append(contratoCobrancaTerceirizada);
        builder.append(", atributo=");
        builder.append(atributo);
        builder.append(", valor=");
        builder.append(valor);
        builder.append(", dadoContratoCobrancaTerceirizadaPai=");
        builder.append(dadoContratoCobrancaTerceirizadaPai);
        builder.append("]");
        return builder.toString();
    }

}
