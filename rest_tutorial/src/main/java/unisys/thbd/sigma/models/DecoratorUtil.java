package unisys.thbd.sigma.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Decorators Util.
 *
 * @author AroAL
 *
 */
public class DecoratorUtil {

    private static String diaMesAno = "ddMMyyyy";
    private static String anoMesDia = "yyyyMMdd";
    private static String horaMinutoSegundo = "HH:mm:ss";
    private static String diaMesAnoMensagem = "DDMMAAAA";

    private DecoratorUtil() {
    }

    /**
     * Decorator de Data (DD/MM/YYYY).
     *
     * @author AroAL
     *
     */
    public static class DateDecorator implements Decorator<Date> {

        /**
         * Converter de string para data "ddMMyyyy" ou "yyyyMMdd". SQN
         *
         */
        public Date fromString(String str) throws DecoratorException {
            if (str == null || str.trim().equals("")) {
                return null;
            }

            try {
                DateFormat df = new SimpleDateFormat(anoMesDia);
                df.setLenient(false);
                Date data = df.parse(str.replace(".", "").replace("-", "").replace("/", ""));
                return data;
            } catch (ParseException e) {
                try {
                    DateFormat df = new SimpleDateFormat(diaMesAno);
                    df.setLenient(false);
                    Date data = df.parse(str.replace(".", "").replace("-", "").replace("/", ""));
                    return data;
                } catch (ParseException pe) {
                    throw new DecoratorException(
                            "Não foi possível converter para o formato Dia, Mês e Ano [" + diaMesAnoMensagem + "]",
                            pe);
                }
            }
        }

        /**
         * Converte uma data para String.
         */
        public String toString(Date obj) {
            Date date = (Date) obj;

            return new SimpleDateFormat(anoMesDia).format(date);
        }

    }

    /**
     * Decorator Time formato HH:MM:SS.
     *
     * @author AroAL
     *
     */
    public static class TimeDecorator implements Decorator<Time> {

        /**
         * Converter de string para Time.
         */
        public Time fromString(String str) throws DecoratorException {
            try {
                Time tempo = null;
//                if (!StringUtil.isNullOrEmpty(str)) {
                if (!(str.isEmpty() || str == null)) {
                    DateFormat df = (new SimpleDateFormat(horaMinutoSegundo));
                    df.setLenient(false);
                    tempo = new Time(df.parse(str).getTime());
                }
                return tempo;
            } catch (ParseException e) {
                throw new DecoratorException("Não foi possível converter para o formato HH:MM:SS [" + horaMinutoSegundo + "]", e);
            }
        }

        /**
         * Converte uma Time para String.
         */
        public String toString(Time obj) {
            Time tempo = (Time) obj;
            return new SimpleDateFormat(horaMinutoSegundo).format(tempo);
        }
    }

    /**
     * Decorator de Moeda com dias casas Decimais.
     *
     * @author EgidioRR
     *
     */
    public static class MoedaDecorator implements Decorator<BigDecimal> {

        private static final int TAMANHO_PARTE_DECIMAL = 2;

        /**
         * Converte de String para BigDecimal.
         */
        public BigDecimal fromString(String str) throws DecoratorException {
            try {
                BigDecimal valorBigDecimal = null;
//                if (!StringUtil.isNullOrEmpty(str)) {
                if (!(str.isEmpty() || str == null)) {
                    String valorSemFormato = str.trim();
                    String parteNumerica = valorSemFormato;
                    String parteDecimal = "00";
                    if (valorSemFormato.contains(".") || valorSemFormato.contains(",")) {
                        parteNumerica = valorSemFormato.substring(0, valorSemFormato.length() - TAMANHO_PARTE_DECIMAL - 1);
                        parteDecimal = valorSemFormato.substring(valorSemFormato.length() - TAMANHO_PARTE_DECIMAL);
                    }
                    String numeroCompleto = parteNumerica + "." + parteDecimal;
                    valorBigDecimal = new BigDecimal(numeroCompleto);
                }

                return valorBigDecimal;
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato decimal com duas casas depois da virgula", e);
            }
        }

        public String toString(BigDecimal obj) {
//            Locale ptBR = I18nUtil.getLocale(I18nUtil.PT_BR);
//            NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(ptBR);
//            return moedaFormat.format(obj);
            return obj.toString();
        }
    }


    public static class DecimalDecorator implements Decorator<BigDecimal> {

        private static final int TAMANHO_PARTE_DECIMAL = 2;

        /**
         * Converte de String para BigDecimal.
         */
        public BigDecimal fromString(String str) throws DecoratorException {
            try {
                BigDecimal valorBigDecimal = null;
//                if (!StringUtil.isNullOrEmpty(str)) {
                if (!(str.isEmpty() || str == null)) {
                    String valorSemFormato = str.trim();
                    String parteNumerica = valorSemFormato;
                    String parteDecimal = "00";
                    int decimalPointPosition;
                    if (valorSemFormato.contains(".") || valorSemFormato.contains(",")) {
                        if (valorSemFormato.indexOf(".") > 0) {
                            decimalPointPosition = valorSemFormato.indexOf(".");
                        } else {
                            decimalPointPosition = valorSemFormato.indexOf(",");
                        }

                        parteNumerica = valorSemFormato.substring(0, decimalPointPosition);
                        parteDecimal = valorSemFormato.substring(decimalPointPosition + 1);
                    }
                    String numeroCompleto = parteNumerica + "." + parteDecimal;
                    valorBigDecimal = new BigDecimal(numeroCompleto);
                }

                return valorBigDecimal;
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato decimal com duas casas depois da virgula", e);
            }
        }

        /**
         * Converte de BigDecimal para String.
         */
        public String toString(BigDecimal obj) {
            String valor = getApplicationDecimalFormat(TAMANHO_PARTE_DECIMAL).format(obj);
            return valor;
        }
    }

    /**
     * Pega a formatação no formato Brasileiro.
     */
    private static DecimalFormat getApplicationDecimalFormat(int parteDecimal) {

//        Locale brasil = I18nUtil.getLocale(I18nUtil.PT_BR);
//        DecimalFormat numberFormat = new DecimalFormat("#,##0.00000", new DecimalFormatSymbols(brasil));
        DecimalFormat numberFormat = new DecimalFormat();
        numberFormat.setParseBigDecimal(true);
        numberFormat.setDecimalSeparatorAlwaysShown(true);
        numberFormat.setMinimumFractionDigits(parteDecimal);
        return numberFormat;
    }

    /**
     * Classe para conversao de long.
     *
     * @author AroAL
     *
     */
    public static class LongDecorator implements Decorator<Long> {

        /**
         * Converte para Long.
         */
        public Long fromString(String str) throws DecoratorException {
            try {
                return str == null || "".equals(str.trim()) ? null : Long.valueOf(str.trim());
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato númerico", e);
            }
        }

        @Override
        public String toString(Long field) throws DecoratorException {
            return field == null ? "" : field.toString();
        }
    }

    /**
     * Classe para conversao de long.
     *
     * @author AroAL
     *
     */
    public static class IntegerDecorator implements Decorator<Integer> {

        @Override
        public Integer fromString(String str) throws DecoratorException {
            try {
                return str == null || "".equals(str.trim()) ? null : Integer.valueOf(str.trim());
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato númerico", e);
            }
        }

        @Override
        public String toString(Integer field) throws DecoratorException {
            return field == null ? "" : field.toString();
        }

    }

    /**
     * Conversor para BigInteger.
     *
     * @author AroAL
     *
     */
    public static class BigIntegerDecorator implements Decorator<BigInteger> {

        @Override
        public BigInteger fromString(String str) throws DecoratorException {
            try {
                return str == null || "".equals(str.trim()) ? null : new BigInteger(str.trim());
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato númerico", e);
            }
        }

        @Override
        public String toString(BigInteger field) throws DecoratorException {
            return field == null ? "" : field.toString();
        }
    }

    /**
     * Classe para conversao de Booleano.
     *
     * @author AroAL
     *
     */
    public static class BooleanDecorator implements Decorator<Boolean> {

        @Override
        public Boolean fromString(String str) throws DecoratorException {
            try {
                return str == null || "".equals(str.trim()) ? null
                        : Boolean.valueOf(str.trim()) || "S".equalsIgnoreCase(str) || "Sim".equalsIgnoreCase(str);
            } catch (Exception e) {
                throw new DecoratorException("Não foi possível converter para o formato booleano", e);
            }
        }

        @Override
        public String toString(Boolean field) throws DecoratorException {
            return field == null ? "" : field.toString();
        }

    }

    /**
     * Retorna o atributo formatado em string.
     *
     * @param decorator
     * @param valor
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked" })
    public static String decoratorToString(Class<? extends Decorator<?>> decorator, String valor) {
        try {
            Decorator dec = decorator.newInstance();
            return dec.toString(dec.fromString(valor));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }


}
