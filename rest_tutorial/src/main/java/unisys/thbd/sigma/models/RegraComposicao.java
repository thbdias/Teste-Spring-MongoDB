package unisys.thbd.sigma.models;

public class RegraComposicao extends Regra {

//    private static final long serialVersionUID = 1L;
    private String valor;
    private Atributo atributo;

    public RegraComposicao() {
        super(TipoRegraEnum.REGRA_COMPOSICAO);
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    /**
     * Metodo que valida a edicao da data inicio que veio do banco.
     *
     * @return
     */
//    public Boolean isReadOnlyDataInicio() {
//        return getId() != null && DataVigenciaUtil.isDataInformadaInferiorDiaAtual(getInicioVigenciaNoBanco());
//    }

    @Override
    public Boolean isDatasValidas() {
//        if (!isReadOnlyDataInicio() && getInicioVigencia().before(DateUtil.getDataAtual())) {
        if (getInicioVigencia().before(DateUtil.getDataAtual())) {
            throw new VigenciaInicialRetroativaInvalidaException();
        }
        return super.isDatasValidas();
    }
}
