package io.codementor.gtommee.rest_tutorial.models;


        import java.math.BigDecimal;
        import java.text.DecimalFormat;
        import java.text.DecimalFormatSymbols;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.GregorianCalendar;
        import java.util.List;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

//        import javax.persistence.criteria.Predicate;
        import javax.sql.rowset.Predicate;
        import javax.xml.datatype.DatatypeConfigurationException;
        import javax.xml.datatype.DatatypeFactory;
        import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe utilitÃ¡ria geral do sistema.
 *
 * @author DelfimSM
 *
 */
public class Util {

    private static final int TAMANHO_AGRUPAMENTO_MILHAR = 3;
    private static final int TAMANHO_ATE_SEPARADOR_CEP = 5;
    private static final int TAMANHO_TOTAL_CEP = 8;

    private Util() {
        super();
    }

    /**
     * Monta a data de acordo com a string passada como parÃ¢metro.
     *
     * @param data
     * @return
     */
    public static XMLGregorianCalendar montaData(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar gCalendar = new GregorianCalendar();
        XMLGregorianCalendar cal = null;
        try {

            gCalendar.setTime(sdf.parse(data));
            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            return cal;

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MÃ©todo que realiza o parse de uma data para XML.
     *
     * @param data
     * @return
     */
    public static XMLGregorianCalendar parseToXMLGregorianCalendar(Date data) {

        GregorianCalendar gCalendar = new GregorianCalendar();
        XMLGregorianCalendar cal = null;
        try {

            gCalendar.setTime(data);
            cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
            return cal;

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MÃ©todo que realiza o parse de uma data.
     *
     * @param data
     * @return
     */
    public static Date parseToDate(String data) {

        if (data == null || data.trim().length() == 0) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return sdf.parse(data);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * MÃ©todo que formata a data para diamesano.
     *
     * @param data
     * @return
     */
    public static String formatDDMMYYYY(Date data) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    /**
     * MÃ©todo de formataÃ§Ã£o de data.
     *
     * @param data
     * @param pattern
     * @return
     */
    public static String formataData(Date data, String pattern) {
        if (data == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(data);
    }

    /**
     * MÃ©todo de formataÃ§Ã£o de Moeda.
     *
     * @param valor
     * @return
     */
    public static String formataMoeda(Double valor) {

        if (valor == null) {
            return "0,00";
        }

        DecimalFormat dFormat = new DecimalFormat("#,###.##");
        dFormat.setMinimumFractionDigits(2);
        return dFormat.format(valor.doubleValue());
    }

    /**
     * MÃ©todo de formataÃ§Ã£o de Moeda.
     *
     * @param valor
     * @return
     */
    public static String formataNumeroMilhar(BigDecimal valor) {

        if (valor == null) {
            return null;
        }

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator(',');
        formatSymbols.setGroupingSeparator('.');

        String strange = "#,##0.###";
        DecimalFormat df = new DecimalFormat(strange, formatSymbols);
        df.setGroupingSize(TAMANHO_AGRUPAMENTO_MILHAR);
        df.setMinimumFractionDigits(0);

        return df.format(valor.doubleValue());

    }

    /**
     * MÃ©todo de formataÃ§Ã£o de moeda.
     *
     * @param valor
     * @return
     */
    public static String formataMoeda(BigDecimal valor) {

        return formataMoeda(valor.doubleValue());

    }

    /**
     * MÃ©todo de formataÃ§Ã£o de moeda.
     *
     * @param valor
     * @return
     */
    public static String formataMoeda(String valor) {

        if (valor == null) {
            return "0,00";
        }

        DecimalFormat dFormat = new DecimalFormat("#,###.##");
        dFormat.setMinimumFractionDigits(2);
        return dFormat.format(Double.parseDouble(valor.replace(",", ".")));

    }

    /**
     * MÃ©todo para formar BigDecimal em Real.
     *
     * @param valor
     * @return
     */
    public static String formatarMoedaReal(BigDecimal valor) {

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator(',');
        formatSymbols.setGroupingSeparator('.');

        String strange = "#,##0.###";
        DecimalFormat df = new DecimalFormat(strange, formatSymbols);
        df.setMinimumFractionDigits(2);

        return df.format(valor);

    }

    /**
     *
     * @param valor
     * @return
     */
    public static Double converterMoedaDoisDigitosDouble(String valor) {

        if (valor == null) {
            return new Double("0.00");
        }

        DecimalFormat dFormat = new DecimalFormat("####.##");
        dFormat.setDecimalSeparatorAlwaysShown(false);
        dFormat.setMinimumFractionDigits(2);
        return Double.parseDouble(dFormat.format(Double.parseDouble(valor.replace(",", "."))).replace(",", "."));
    }

    /**
     * MÃ©todo que formata uma data para XML.
     *
     * @param data
     * @return
     */
    public static String formataDataXML(XMLGregorianCalendar data) {
        return formatDDMMYYYY(data.toGregorianCalendar().getTime());
    }

    /**
     * MÃ©todo que converte uma data para um XML Gregorian Calendar.
     *
     * @param data
     * @return
     */
    public XMLGregorianCalendar toXmlDate(String data) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = sdf.parse(data);

            return toXmlDate(d);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * MÃ©todo que pega a data e formata como XML.
     *
     * @param data
     * @return
     */
    public XMLGregorianCalendar toXmlDate(Date data) {
        try {

            GregorianCalendar greg = new GregorianCalendar();
            greg.setTime(data);

            return DatatypeFactory.newInstance().newXMLGregorianCalendar(greg);

        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    /**
     * MÃ©todo que verifica se o email Ã© vÃ¡lido.
     *
     * @param email
     * @return
     */
    public static boolean isEmailValido(String email) {

        String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return validaRegex(email, regex);

    }

    /**
     * MÃ©todo que valida se um regex Ã© vÃ¡lido.
     *
     * @param valor
     * @param regex
     * @return
     */
    public static boolean validaRegex(String valor, String regex) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(valor);
        return m.matches();

    }

    /**
     * Formata Cep.
     *
     * @param cep
     * @return
     */
    public static String formataCEP(String cep) {
        if (cep == null) {
            return null;
        }
        String val = "00000000" + cep.trim();
        cep = val.substring(val.length() - TAMANHO_TOTAL_CEP);
        return cep.substring(0, TAMANHO_ATE_SEPARADOR_CEP) + "-" + cep.substring(TAMANHO_ATE_SEPARADOR_CEP);

    }

    /**
     * Metodo para formatacao do telefone.
     *
     * @param phoneNum
     * @return
     */
    public static String formataTelefone(Object phoneNum) {

        String phone = null;

        if (phoneNum != null) {
            if (phoneNum instanceof Long) {
                phone = ((Long) phoneNum).toString();
            } else if (phoneNum instanceof String) {
                phone = (String) phoneNum.toString();
            }

            if (phone != null && phone.length() >= 10) {
                if (phone.length() == 10) {
                    return phone.replaceFirst("(\\d{2})(\\d{4})(\\d+)", "($1) $2-$3");
                } else {
                    return phone.replaceFirst("(\\d{2})(\\d{5})(\\d+)", "($1) $2-$3");
                }
            }
        }
        return null;
    }

    /**
     * Converter lista de Predicate para array.
     *
     * @param lista
     * @return
     */
    public static Predicate[] toArray(List<Predicate> lista) {

        Predicate[] array = new Predicate[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            array[i] = lista.get(i);
        }

        return array;
    }

    /**
     * Remove zeros a esquerda da String passada por parametro. Sendo a String
     * um valor numerico.
     *
     * @param filtro
     * @return
     */
    public static String removerZerosEsquerda(String filtro) {
        for (int i = 0; i < filtro.length(); ++i) {
            char c = filtro.charAt(i);
            if (c != '0' && !Character.isSpaceChar(c)) {
                return filtro.substring(i);
            }
        }
        return filtro;

    }

    /**
     * verifica se um valor nÃ£o Ã© nulo ou branco.
     *
     * @param texto
     * @return
     */
    public static boolean isNotNullOrNotBlank(String texto) {
        if ((texto == null) || (texto.equals(""))) {
            return false;
        }
        return true;
    }

}
