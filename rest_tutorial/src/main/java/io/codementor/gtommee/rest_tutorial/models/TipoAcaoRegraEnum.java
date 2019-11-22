package io.codementor.gtommee.rest_tutorial.models;

public enum TipoAcaoRegraEnum {

    ATRIBUICAO(1, "Atribuição", "Atribui um valor a um determinado atributo.");

    private Integer codigo;

    private String nome;

    private String descricao;

    TipoAcaoRegraEnum(Integer codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param codigo
     * @return
     */
    public static TipoAcaoRegraEnum getTipoAcaoRegraEnum(Integer codigo) {
        for (TipoAcaoRegraEnum tipo : TipoAcaoRegraEnum.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        return null;
    }

}
