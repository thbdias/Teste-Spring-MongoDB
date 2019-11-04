package io.codementor.gtommee.rest_tutorial.models;

public enum CodigoPagamentoEnum {

    // Pagamentos complementares
    CODIGO_308("308", Boolean.TRUE),
    CODIGO_318("318", Boolean.FALSE),
    CODIGO_307("307", Boolean.TRUE),
    CODIGO_370("370", Boolean.FALSE),
    CODIGO_508("508", Boolean.TRUE),
    CODIGO_518("518", Boolean.FALSE),
    // Pagamentos de amortização
    CODIGO_300("300", Boolean.TRUE),
    CODIGO_303("303", Boolean.TRUE),
    CODIGO_305("305", Boolean.TRUE),
    CODIGO_310("310", Boolean.FALSE),
    CODIGO_311("311", Boolean.FALSE),
    CODIGO_312("312", Boolean.FALSE),
    CODIGO_313("313", Boolean.FALSE),
    CODIGO_314("314", Boolean.FALSE),
    CODIGO_315("315", Boolean.FALSE),
    CODIGO_316("316", Boolean.FALSE),
    CODIGO_317("317", Boolean.FALSE),
    CODIGO_351("351", Boolean.FALSE),
    CODIGO_352("352", Boolean.FALSE),
    CODIGO_353("353", Boolean.FALSE),
    CODIGO_354("354", Boolean.FALSE),
    CODIGO_355("355", Boolean.FALSE),
    CODIGO_356("356", Boolean.FALSE),
    CODIGO_357("357", Boolean.FALSE),
    CODIGO_358("358", Boolean.FALSE),
    // Pagamentos de Incorporação
    CODIGO_503("503", Boolean.TRUE),
    CODIGO_543("543", Boolean.FALSE);

    private String codigo;
    private Boolean estornoPagamento;

    CodigoPagamentoEnum(String codigo, Boolean estornoPagamento) {
        this.codigo = codigo;
        this.estornoPagamento = estornoPagamento;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the estornoPagamento
     */
    public Boolean isEstornoPagamento() {
        return estornoPagamento;
    }

    /**
     * Recuperar um Enumetor através de seu código.
     *
     * @param codigo
     * @return
     */
    public static CodigoPagamentoEnum getByCodigoPagamento(String codigo) {
        for (CodigoPagamentoEnum codigoPagamento : CodigoPagamentoEnum.values()) {
            if (codigoPagamento.getCodigo().equals(codigo)) {
                return codigoPagamento;
            }
        }
        return null;
    }

}