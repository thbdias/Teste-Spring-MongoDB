package unisys.thbd.sigma.models;

import java.math.BigDecimal;
import java.util.Date;

public class AtributoSistemaUtil {

    private AtributoSistemaUtil() {
    }

    public static Integer toInteger(String valor) {
        Decorator<Integer> toInteger = new DecoratorUtil.IntegerDecorator();
        Integer resultado = null;

        try {
            resultado = toInteger.fromString(valor);
        } catch (DecoratorException e) {
            return resultado;
        }
        return resultado;
    }

    public static Double toDouble(String valor) {
        Decorator<BigDecimal> toDouble = new DecoratorUtil.DecimalDecorator();
        Double resultado = null;

        try {
            resultado = toDouble.fromString(valor).doubleValue();
        } catch (DecoratorException e) {
            return resultado;
        }
        return resultado;
    }

    public static Date toDate(String valor) {
        Decorator<Date> toDate = new DecoratorUtil.DateDecorator();
        Date resultado = null;

        try {
            resultado = toDate.fromString(valor);
        } catch (DecoratorException e) {
            return resultado;
        }
        return resultado;
    }
}
