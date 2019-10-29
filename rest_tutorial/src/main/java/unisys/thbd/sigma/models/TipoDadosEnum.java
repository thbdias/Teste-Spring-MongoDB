package unisys.thbd.sigma.models;

import unisys.thbd.sigma.models.DecoratorUtil.*;

public enum TipoDadosEnum {

    NUMERICO(0, "Númerico", "9999", LongDecorator.class),
    DECIMAL(1, "Decimal", "999,99", DecimalDecorator.class),
    MOEDA(2, "Moeda", "999,99", MoedaDecorator.class),
    DATA(3, "Data", "DD/MM/AAAA", DateDecorator.class),
    BOOLEANO(4, "Verdadeiro/Falso", "S ou N", BooleanDecorator.class),
    TEXTO(5, "Texto", "Texto", DefaultDecorator.class);

    private Integer codigo;

    private String descricao;

    private String exemplo;

    private Class<? extends Decorator<?>> decorator;

    TipoDadosEnum(Integer codigo, String descricao, String exemplo, Class<? extends Decorator<?>> decorator) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.exemplo = exemplo;
        this.decorator = decorator;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getExemplo() {
        return exemplo;
    }

    public Class<? extends Decorator<?>> getDecorator() {
        return decorator;
    }


    public static TipoDadosEnum getTipoDadosEnum(Integer codigo) {
        if (codigo != null) {
            for (TipoDadosEnum tipoDados : TipoDadosEnum.values()) {
                if (tipoDados.getCodigo().equals(codigo)) {
                    return tipoDados;
                }
            }
        }
        return null;
    }

}
