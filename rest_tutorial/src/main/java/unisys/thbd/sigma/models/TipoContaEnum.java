package unisys.thbd.sigma.models;

public enum TipoContaEnum {
    OUTRO(4, "OUTRO_BANCO"), NSGD(2, "NSGD"), SIDEC(1, "SIDEC"), AMBOS(3, "AMBOS");

    private Integer codigo;
    private String descricao;

    TipoContaEnum(Integer codigo, String exemplo) {
        this.codigo = codigo;
        this.descricao = exemplo;
    }

    /**
     * Verifica se a conta eh uma conta caixa.
     * @param tipoConta
     * @return
     */
    public static boolean isContaCaixa(TipoContaEnum tipoConta) {
        if (TipoContaEnum.NSGD.equals(tipoConta)) {
            return true;
        }

        if (TipoContaEnum.SIDEC.equals(tipoConta)) {
            return true;
        }

        return false;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Recupera o tipoConta pelo codigo.
     *
     * @param codigo
     * @return
     */
    public static TipoContaEnum getTipoContaEnum(Integer codigo) {
        if (codigo != null) {
            for (TipoContaEnum tipoDados : TipoContaEnum.values()) {
                if (tipoDados.getCodigo().equals(codigo)) {
                    return tipoDados;
                }
            }
        }
        return null;
    }

}
