package unisys.thbd.sigma.models;

public abstract class AcaoRegra extends EntidadeCobrancaTerceirizada {

//    private static final long serialVersionUID = 1L;
//    private Long id;

    private Integer tipoAcao;
    private Regra regra;
    private Atributo atributo;
    private String valor;

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public TipoAcaoRegraEnum getTipoAcaoEnum() {
        return TipoAcaoRegraEnum.getTipoAcaoRegraEnum(tipoAcao);
    }

    /**
     * retorna se o atributo e valor correspondem a acaoRegra.
     *
     * @param valor
     * @param atributo
     *
     * @return
     */

    public boolean atributoValorCorrespondente(Atributo atributo, String valor) {
        if (!this.atributo.equals(atributo)) {
            return false;
        }
        if (!this.valor.equals(valor)) {
            return false;
        }
        return true;
    }

    /**
     * Alterar o Tipo da Ação.
     *
     * @param tipoAcao
     *            the tipoAcaoEnum to set
     */
    protected void setTipoAcao(TipoAcaoRegraEnum tipoAcao) {
        if (tipoAcao != null) {
            this.tipoAcao = tipoAcao.getCodigo();
        } else {
            tipoAcao = null;
        }
    }

    /**
     * Obter a Regra que está vinculada a Ação.
     *
     * @return the regra
     */
    public Regra getRegra() {
        return regra;
    }

    /**
     * Alterar a Regra que está vinculada a Ação.
     *
     * @param regra
     *            the regra to set
     */
    public void setRegra(Regra regra) {
        this.regra = regra;
    }

    /**
     * Obter o Atributo que está vinculada a Ação.
     *
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }

    /**
     * Alterar o Atributo que está vinculada a Ação.
     *
     * @param atributo
     *            the atributo to set
     */
    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    /**
     * Obter o valor que deverá ser utilizado pelo Atributo que está vinculada a
     * Ação.
     *
     * @return the valor
     */
    public String getValor() {
        if (valor != null) {
            valor = valor.trim();
        }
        return valor;
    }

    /**
     * Alterar o valor que deverá ser utilizado pelo atributo que está vinculada
     * a Ação.
     *
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((atributo == null) ? 0 : atributo.hashCode());
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((regra == null) ? 0 : regra.hashCode());
        result = prime * result + ((tipoAcao == null) ? 0 : tipoAcao.hashCode());
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
        if (!(obj instanceof AcaoRegra)) {
            return false;
        }
        AcaoRegra other = (AcaoRegra) obj;
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
        if (regra == null) {
            if (other.regra != null) {
                return false;
            }
        } else if (!regra.equals(other.regra)) {
            return false;
        }
        if (tipoAcao == null) {
            if (other.tipoAcao != null) {
                return false;
            }
        } else if (!tipoAcao.equals(other.tipoAcao)) {
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
