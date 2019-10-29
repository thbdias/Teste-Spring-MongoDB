package unisys.thbd.sigma.models;

import java.util.Date;

public class DataVigenciaUtil {

    private DataVigenciaUtil() {
    }

    public static Boolean isDataInicioInferiorDataFim(Date data1, Date data2) {

        if (data1 != null && data2 != null) {

            Date data1Validada = DateUtil.ultimoMilissegundoDeData(data1);
            Date data2Validada = DateUtil.ultimoMilissegundoDeData(data2);

            return !data1Validada.equals(data2Validada) && data1.before(data2);
        } else {
            return false;
        }
    }

    /**
     * Metodo para indentificar se a Data1 é igual a Data2 ignorando os
     * milissegundos.
     *
     * @param data1
     * @param data2
     * @return
     */
    public static Boolean isDatasIguaisSemMilissegundo(Date data1, Date data2) {
        Date data1Validada = DateUtil.ultimoMilissegundoDeData(data1);
        Date data2Validada = DateUtil.ultimoMilissegundoDeData(data2);
        return data1Validada.equals(data2Validada);
    }

    /**
     * Metodo que verifica se a data é inferior ou igual a data atual.
     *
     * @param data
     * @return
     */
    public static Boolean isDataInformadaInferiorOuIgualHoje(Date data) {
        if (data != null) {
            Date dataValidada = DateUtil.primeiroMilissegundoDeData(data);
            Date dataAtual = DateUtil.primeiroMilissegundoDeData(DateUtil.getDataAtual());

            return isDataInicioInferiorDataFim(dataValidada, dataAtual)
                    || isDatasIguaisSemMilissegundo(dataValidada, dataAtual);
        }
        return false;
    }

    /**
     * Metodo que verifica se a data informada é maior que a data atual.
     *
     * @param data
     * @return
     */
    public static Boolean isDataInformadaEMaiorDataAtual(Date data) {
        if (data != null) {
            Date dataValidada = DateUtil.ultimoMilissegundoDeData(data);
            Date dataAtual = DateUtil.ultimoMilissegundoDeData(DateUtil.getDataAtual());
            return isDataInicioInferiorDataFim(dataAtual, dataValidada);
        }
        return false;
    }

    /**
     * Metodo que verifica se data é maior ou igual a data atual.
     *
     * @param data
     * @return
     */
    public static Boolean isDataInformadaEMaiorIgualDataAtual(Date data) {
        if (data != null) {
            Date dataValidada = DateUtil.ultimoMilissegundoDeData(data);
            Date dataAtual = DateUtil.ultimoMilissegundoDeData(DateUtil.getDataAtual());
            if (!dataValidada.equals(dataAtual)) {
                return isDataInicioInferiorDataFim(dataAtual, dataValidada);
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que verifica se o quantidade de intervalo de dias é superior a um
     * número de dias.
     *
     * @param vigenciaDe
     * @param vigenciaAte
     * @param numeroDias
     * @return
     */
    public static Boolean isQtdeDiasSuperiorAQtdeDiasInformado(Date vigenciaDe, Date vigenciaAte, int numeroDias) {
        Date novaVigenciaDe = DateUtil.adicionaDias(vigenciaDe, numeroDias);
        return !novaVigenciaDe.after(vigenciaAte);
    }

    public static Boolean isDataAtualEntreDataInicioEDataFim(Date dataInicio, Date dataFim) {
        Date dataAtual = DateUtil.ultimoMilissegundoDeData(DateUtil.getDataAtual());

        return isDataInformaEntreDatasInicioEFim(dataAtual, dataInicio, dataFim);
    }

    public static Boolean isDataInformaEntreDatasInicioEFim(Date dataInformada, Date dataInicio, Date dataFim) {
        if (dataInformada != null && dataInicio != null && dataFim != null) {
            Date dataAtual = DateUtil.ultimoMilissegundoDeData(dataInformada);
            Date dataInicioValidada = DateUtil.ultimoMilissegundoDeData(dataInicio);
            Date dataFimValidada = DateUtil.ultimoMilissegundoDeData(dataFim);

            return (isDataInicioInferiorDataFim(dataInicioValidada, dataAtual)
                    || isDatasIguaisSemMilissegundo(dataInicioValidada, dataAtual))
                    && (isDataInicioInferiorDataFim(dataAtual, dataFimValidada)
                    || isDatasIguaisSemMilissegundo(dataAtual, dataFimValidada));
        }
        return false;
    }

    /**
     * Metodo que verifica se a data informada é inferior ao dia atual.
     *
     * @param data
     * @return
     */
    public static boolean isDataInformadaInferiorDiaAtual(Date data) {
        if (data != null) {
            Date dataValidada = DateUtil.primeiroMilissegundoDeData(data);
            Date dataAtual = DateUtil.primeiroMilissegundoDeData(DateUtil.getDataAtual());

            return isDataInicioInferiorDataFim(dataValidada, dataAtual);
        }
        return false;
    }
}
