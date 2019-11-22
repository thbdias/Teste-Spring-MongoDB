package io.codementor.gtommee.rest_tutorial.models;

public class TipoDistribuicaoOrdem extends EntidadeCobrancaTerceirizada implements Comparable<TipoDistribuicaoOrdem> {

//    private static final long serialVersionUID = 1L;

//    private TipoDistribuicaoOrdemID id;

    private int ordem;
    private Atributo atributo;
    private String rotulo;

    public TipoDistribuicaoOrdem() {
        super();
    }

//    public TipoDistribuicaoOrdem(Atributo atr, TipoDistribuicao tipoDistribuicao) {
    public TipoDistribuicaoOrdem(Atributo atr) {
//        id = new TipoDistribuicaoOrdemID();
        this.atributo = atr;
//        id.setAtributo(atr.getId());
//        id.setTipoDistribuicao(tipoDistribuicao.getId());
    }


//    public TipoDistribuicaoOrdemID getId() {
//        return id;
//    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getRotulo() {
        rotulo = getAtributo().getRotulo();
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    @Override
    public int compareTo(TipoDistribuicaoOrdem o) {

        return this.getOrdem() - o.getOrdem();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
//        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ordem;
        result = prime * result + ((rotulo == null) ? 0 : rotulo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }
//        if (this.id.equals(((TipoDistribuicaoOrdem) obj).id)) {
//            return true;
//        }

        return false;
    }

}
