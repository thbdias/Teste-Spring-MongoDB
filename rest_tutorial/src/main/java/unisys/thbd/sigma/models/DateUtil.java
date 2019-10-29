package unisys.thbd.sigma.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class DateUtil {

    private static final int ULTIMO_MES_ANO = 11;
    private static final int ULTIMA_HORA_DIA = 23;
    private static final int ULTIMO_MINUTO_SEGUNDO_DATA = 59;
    private static final int TAMANHO_DATA = 10;
    private static final int HORAS = 24;
    private static final int MINUTOS = 60;
    private static final int SEGUNDOS = 60;
    private static final int MILISEGUNDOS = 1000;
    private static final String FORMATO_DDMMYYYY = "dd/MM/yyyy";
    private static List<Date> listaDate = new ArrayList<Date>(8);

    private DateUtil() {
    }

    public static String buscaHoraAtual() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", new Locale("pt", "BR"));
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static Date duplicaObjetoData(Date data) {
        return data != null ? new Date(data.getTime()) : null;
    }

    public static String buscaDataHoraAtual() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static String buscaDataAtual() {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", new Locale("pt", "BR"));
        return simpleDateFormat.format(cal.getTime());
    }

    public static Date getDataAtualHoraMinuto() {
        Date dataRetorno = null;

        try {
            Calendar cal = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));

            String data = simpleDateFormat.format(cal.getTime());
            dataRetorno = simpleDateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataRetorno;
    }

    public static Date getDataAtual() {
        Date dataRetorno = null;
        try {
            Calendar cal = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY, new Locale("pt", "BR"));

            String data = simpleDateFormat.format(cal.getTime());

            dataRetorno = simpleDateFormat.parse(data);

        } catch (ParseException p) {
            p.printStackTrace();
        }

        return dataRetorno;
    }

    public static Date getDataAtualUltimaHoraMinutoSegundoDoDia() {
        return atribuirUltimaHoraMinutoSegundoNaData(new Date());
    }

    public static Date atribuirUltimaHoraMinutoSegundoNaData(Date date) {
        GregorianCalendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(date);
        dataAtual.set(Calendar.HOUR_OF_DAY, ULTIMA_HORA_DIA);
        dataAtual.set(Calendar.MINUTE, ULTIMO_MINUTO_SEGUNDO_DATA);
        dataAtual.set(Calendar.SECOND, ULTIMO_MINUTO_SEGUNDO_DATA);
        return dataAtual.getTime();

    }

    public static Date atribuirPrimeiraHoraMinutoSegundoNaData(Date date) {
        GregorianCalendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(date);
        dataAtual.set(Calendar.HOUR_OF_DAY, 0);
        dataAtual.set(Calendar.MINUTE, 0);
        dataAtual.set(Calendar.SECOND, 1);
        return dataAtual.getTime();

    }

    public static Date getMesAnoAtualComDiaPassadoPorParametro(int diaParametro) {
        Calendar dataAtualCalendar = GregorianCalendar.getInstance();

        Calendar dataParametrizada =
                new GregorianCalendar(dataAtualCalendar.get(Calendar.YEAR), dataAtualCalendar.get(Calendar.MONTH), diaParametro);

        return atribuirUltimaHoraMinutoSegundoNaData(dataParametrizada.getTime());
    }

    public static String buscaDataAtualAAAA() {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY, new Locale("pt", "BR"));
        return simpleDateFormat.format(cal.getTime());
    }

    public static String formataData(Date dataFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY, new Locale("pt", "BR"));
        return simpleDateFormat.format(dataFormat.getTime());
    }

    public static String formataDataDDMMMAAAA(Date dataFormat) {
        return formataDataDDMMMAAAA(dataFormat, null);
    }

    public static String formataDataDDMMMAAAA(Date dataFormat, Locale locale) {
        return formataDataNoFormato(dataFormat, "dd-MMM-yyyy", locale);
    }

    public static String formataDataNoFormato(Date dataFormat, String formato, Locale locale) {
        if (dataFormat != null) {
            if (locale != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato, locale);
                return simpleDateFormat.format(dataFormat.getTime());
            } else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
                return simpleDateFormat.format(dataFormat.getTime());
            }
        }

        return null;
    }

    public static String formataDataMesAno(Date dataFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy", new Locale("pt", "BR"));
        return simpleDateFormat.format(dataFormat.getTime());
    }

    public static String formataDataHoraMinuto(Date dataFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
        return simpleDateFormat.format(dataFormat.getTime());
    }

    public static Date formataDataHoraMinutoSemSeparador(String data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmm", new Locale("pt", "BR"));
        return simpleDateFormat.parse(data);
    }

    public static Date formataDataHoraMinutoSemSeparadorParser(String data) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm", new Locale("pt", "BR"));
        return simpleDateFormat.parse(data);
    }

    public static String getDataHoraMinutoSemSeparadorParser(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm", new Locale("pt", "BR"));
        return simpleDateFormat.format(data);
    }

    public static String getDataHoraMinutoComSeparador(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("pt", "BR"));
        return simpleDateFormat.format(data);
    }

    public static String getDataHoraMinutoSemSeparador(Date data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmm", new Locale("pt", "BR"));
        return simpleDateFormat.format(data);
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    public static Map buscaAnoMesAtual() {

        Calendar cal = new GregorianCalendar();
        int mes = (cal.get(Calendar.MONTH)) + 1;
        int ano = cal.get(Calendar.YEAR);
        Map mapAnoMes = new LinkedHashMap();
        mapAnoMes.put("ano", Integer.valueOf(ano));
        mapAnoMes.put("mes", Integer.valueOf(mes));
        return mapAnoMes;
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    public static Map buscaAnoMesAnteriorAoParametro(int totalMesesAnteriores) {

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, -totalMesesAnteriores);
        int mes = (cal.get(Calendar.MONTH)) + 1;
        int ano = cal.get(Calendar.YEAR);
        Map mapAnoMes = new LinkedHashMap();
        mapAnoMes.put("ano", Integer.valueOf(ano));
        mapAnoMes.put("mes", Integer.valueOf(mes));
        return mapAnoMes;
    }

    public static String gerarMesAnoData(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int mes = (cal.get(Calendar.MONTH)) + 1;
        int ano = cal.get(Calendar.YEAR);
        return String.format("%02d", mes) + "/" + ano;
    }

    @SuppressWarnings("rawtypes")
    public static Map buscaAnoMesAnterior() {
        return buscaMesAnoAtualMenosMesesPassadoPorParametro(1);
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    public static Map buscaMesAnoAtualMenosMesesPassadoPorParametro(int subtrairMeses) {

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, -subtrairMeses);
        int mes = (cal.get(Calendar.MONTH)) + 1;
        int ano = cal.get(Calendar.YEAR);
        Map mapAnoMes = new LinkedHashMap();
        mapAnoMes.put("ano", Integer.valueOf(ano));
        mapAnoMes.put("mes", Integer.valueOf(mes));
        return mapAnoMes;
    }


    @SuppressWarnings({"rawtypes", "unchecked" })
    public static Map buscaAnoMesAnterior(Integer ano, Integer mes) {
        Calendar data = new GregorianCalendar();
        data.set(Calendar.YEAR, ano.intValue());
        data.set(Calendar.MONTH, mes.intValue() - 1);
        data.add(Calendar.MONTH, -1);
        int mes1 = (data.get(Calendar.MONTH) + 1);
        int ano1 = data.get(Calendar.YEAR);
        Map mapAnoMes = new LinkedHashMap();
        mapAnoMes.put("ano", Integer.valueOf(ano1));
        mapAnoMes.put("mes", Integer.valueOf(mes1));
        return mapAnoMes;
    }


    public static boolean estaPreenchido(String parametro) {
        boolean retorno = true;

        if ((parametro == null) || (parametro.trim().length() < 1)) {
            retorno = false;
        }

        return retorno;
    }


    public static Date adicionaMes(Date data, int numMes) {

        Calendar dataLimite = Calendar.getInstance();
        dataLimite.setTime(data);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY, new Locale("pt", "BR"));

        dataLimite.add(Calendar.MONTH, numMes);

        String data1 = simpleDateFormat.format(dataLimite.getTime());

        Date dataRetorno = null;
        try {
            dataRetorno = simpleDateFormat.parse(data1);

        } catch (ParseException p) {
            p.printStackTrace();
        }

        return dataRetorno;

    }

    public static Date adicionaDias(Date data, int numDias) {

        Calendar dataLimite = Calendar.getInstance();
        dataLimite.setTime(data);
        dataLimite.add(Calendar.DAY_OF_MONTH, numDias);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMATO_DDMMYYYY, new Locale("pt", "BR"));

        String data1 = simpleDateFormat.format(dataLimite.getTime());

        Date dataRetorno = null;
        try {
            dataRetorno = simpleDateFormat.parse(data1);

        } catch (ParseException p) {
            p.printStackTrace();
        }

        return dataRetorno;

    }

    public static Date primeiroMilissegundoDeData(Calendar data) {
        Calendar work = (Calendar) data.clone();
        work.set(Calendar.HOUR_OF_DAY, Integer.parseInt("0"));
        work.set(Calendar.MINUTE, Integer.parseInt("0"));
        work.set(Calendar.SECOND, Integer.parseInt("0"));
        work.set(Calendar.MILLISECOND, Integer.parseInt("0"));
        return work.getTime();
    }

    public static Date primeiroMilissegundoDeHoje() {
        return primeiroMilissegundoDeData(GregorianCalendar.getInstance());
    }

    public static Date primeiroMilissegundoDeData(Date data) {
        Calendar work = Calendar.getInstance();
        work.setTime(data);
        return primeiroMilissegundoDeData(work);
    }

    public static Date ultimoMilissegundoDeData(Calendar data) {
        Calendar work = (Calendar) data.clone();
        work.set(Calendar.HOUR_OF_DAY, Integer.parseInt("23"));
        work.set(Calendar.MINUTE, Integer.parseInt("59"));
        work.set(Calendar.SECOND, Integer.parseInt("59"));
        work.set(Calendar.MILLISECOND, Integer.parseInt("999"));
        return work.getTime();
    }

    public static Date ultimoMilissegundoDeHoje() {
        return ultimoMilissegundoDeData(GregorianCalendar.getInstance());
    }

    public static Date ultimoMilissegundoDeData(Date data) {
        Calendar work = Calendar.getInstance();
        work.setTime(data);
        return ultimoMilissegundoDeData(work);
    }

    public static String diaSemana(int i) {
        String[] diasem = {"Domingo", "Segunda-Feira", "TerÃ§a-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "SÃ¡bado" };

        return (diasem[i - 1]); // extenso
    }

    public static String getMesExtenso(int mes) {
        if (mes > ULTIMO_MES_ANO || mes < 0) {
            return null;
        }

        String[] meses = {
                "Janeiro",
                "Fevereiro",
                "MarÃ§o",
                "Abril",
                "Maio",
                "Junho",
                "Julho",
                "Agosto",
                "Setembro",
                "Outubro",
                "Novembro",
                "Dezembro" };

        return (meses[mes]);
    }

    public static String getMesExtensoAbreviado(int mes) {
        if (mes > ULTIMO_MES_ANO || mes < 0) {
            return null;
        }

        String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" };

        return (meses[mes]);
    }

    public static String dataAtualPorExtenso() {

        Calendar dataAtual = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy, HH:mm", new Locale("pt", "BR"));
        String dataFormatada = simpleDateFormat.format(dataAtual.getTime());

        return (diaSemana(dataAtual.get(Calendar.DAY_OF_WEEK)) + " " + dataFormatada);
    }

    public static String dataPorExtenso(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy, HH:mm", new Locale("pt", "BR"));
        String dataFormatada = simpleDateFormat.format(data.getTime());

        return (diaSemana(calendar.get(Calendar.DAY_OF_WEEK)) + " " + dataFormatada);
    }

    public static Date dataUltimoDiaMes(int mes, int ano) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, mes + 1);
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date ultimoDiaDoMes = calendar.getTime();

        return ultimoDiaDoMes;

    }

    public static Date dataUltimoDiaMes(String dataMesAno) {

        String[] data = dataMesAno.split("/");

        if (data.length != 2) {
            return null;
        }

        return dataUltimoDiaMes(Integer.parseInt(data[0]) - 1, Integer.parseInt(data[1]));

    }


    public static Date dataUltimoDiaMes(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();

    }


    public static int getUltimoDiaMes(Date data) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        return calendar.get(Calendar.DAY_OF_MONTH);

    }

    public static Date dataPrimeiroDiaMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date dataPrimeiroDiaMes(String dataMesAno) {

        String[] data = dataMesAno.split("/");

        if (data.length != 2) {
            return null;
        }

        return dataPrimeiroDiaMes(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
    }

    public static Date dataPrimeiroDiaMes(int mes, int ano) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.YEAR, ano);

        return calendar.getTime();
    }

    public static Date converteStringParaDate(String data) {
        if (data != null && data != "" && data.length() == TAMANHO_DATA) {
            try {
                return new SimpleDateFormat("dd/MM/yyyyy").parse(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date converteStringParaDate(String data, String formato, Locale locale) {
        if (data != null && !"".equals(data) && formato != null && !"".equals(formato) && locale != null) {
            try {
                return new SimpleDateFormat(formato, locale).parse(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int getDiferencaDias(Date dataInicio, Date dataFim) {
        long diferencaMilisegundos = dataFim.getTime() - dataInicio.getTime();
        return (int) (diferencaMilisegundos / MILISEGUNDOS / SEGUNDOS / MINUTOS / HORAS);

    }

    public static int getAnoDaData(Date data) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMesDaData(Date data) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDiaDaData(Date data) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(data);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int verificarUltimoDiaMesCorrente(int dia) {

        int ultimoDia = getUltimoDiaMes(new Date());

        if (dia > ultimoDia) {
            return ultimoDia;
        }
        return dia;

    }

    public static int getMesPosteriorMesPassadoPorParametro(int mes) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.get(Calendar.MONTH) + 1;

    }

    public static int getMesAnteriorMesPassadoPorParametro(int mes) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.add(Calendar.MONTH, -1);
        return calendar.get(Calendar.MONTH) + 1;

    }

    public static int subtrairMeses(int mes, int quantidadeMesesSeraSubtraida) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.add(Calendar.MONTH, -quantidadeMesesSeraSubtraida);
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static boolean isDatasSaoMesesOuAnoDiferentes(Date data1, Date data2) {

        if (data1 == null || data2 == null) {
            return false;
        }

        Calendar data1Calendar = GregorianCalendar.getInstance();
        data1Calendar.setTime(data1);

        Calendar data2Calendar = GregorianCalendar.getInstance();
        data2Calendar.setTime(data2);

        int mesData1 = data1Calendar.get(Calendar.MONTH);
        int anoData1 = data1Calendar.get(Calendar.YEAR);

        int mesData2 = data2Calendar.get(Calendar.MONTH);
        int anoData2 = data2Calendar.get(Calendar.YEAR);

        if (mesData1 != mesData2 || anoData1 != anoData2) {
            return true;
        }
        return false;

    }

    public static String getDataHoraAtual() {
        Calendar calendar = Calendar.getInstance();
        return new SimpleDateFormat("dd 'de " + DateUtil.getMesExtenso(calendar.get(Calendar.MONTH)) + " de' yyyy HH:mm:ss")
                .format(calendar.getTime());

    }

    public static boolean isDiaUtil(Date data) {
        criarFeriadosNacionais();
        for (Date item : listaDate) {
            if (isDiaMesIgual(data, item) || isFDS(data)) {
                return false;
            }
        }
        return true;
    }

    private static void criarFeriadosNacionais() {

        try {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            listaDate.add(format.parse("02-07"));
            listaDate.add(format.parse("01-01"));
            listaDate.add(format.parse("04-21"));
            listaDate.add(format.parse("05-01"));
            listaDate.add(format.parse("09-07"));
            listaDate.add(format.parse("10-12"));
            listaDate.add(format.parse("11-02"));
            listaDate.add(format.parse("11-15"));
            listaDate.add(format.parse("12-25"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isFDS(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ? true : false;
    }

    private static boolean isDiaMesIgual(Date atual, Date feriado) {
        Calendar calAtual = Calendar.getInstance();
        Calendar calFeriado = Calendar.getInstance();
        calAtual.setTime(atual);
        calFeriado.setTime(feriado);
        calFeriado.set(Calendar.YEAR, calAtual.get(Calendar.YEAR));
        atual = DateUtil.atribuirUltimaHoraMinutoSegundoNaData(calAtual.getTime());
        feriado = DateUtil.atribuirUltimaHoraMinutoSegundoNaData(calFeriado.getTime());
        if (atual.compareTo(feriado) == 0) {
            return true;
        }
        return false;
    }

    public static Date recuperarProximoDiaUtil(Date date) {
        while (!DateUtil.isDiaUtil(date)) {
            date = DateUtil.adicionaDias(date, 1);
        }
        return date;
    }

    public static String formataData(GregorianCalendar dataFormat, String format) {
        return new SimpleDateFormat(format).format(dataFormat.getTime());
    }

    public static int dateDiffDays(Calendar data1, Calendar data2) {

        // calculo somente com datas, sem hora

        data1 = clearTime(data1);

        data2 = clearTime(data2);

        // retorna a diferenca em dias

        long millisDate1 = data1.getTimeInMillis();

        long millisDate2 = data2.getTimeInMillis();

        return new Long((millisDate1 - millisDate2) / (24 * 60 * 60 * 1000)).intValue();

    }

    public static int dateDiffDays(Date data1, Date data2) {

        Calendar d1 = Calendar.getInstance();
        d1.setTime(data1);

        Calendar d2 = Calendar.getInstance();
        d2.setTime(data2);

        return dateDiffDays(d1, d2);

    }

    public static Calendar clearTime(Calendar calendar) {

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;

    }

}