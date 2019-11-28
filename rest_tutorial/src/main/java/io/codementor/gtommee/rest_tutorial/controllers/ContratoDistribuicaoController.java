package io.codementor.gtommee.rest_tutorial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.codementor.gtommee.rest_tutorial.models.*;
import io.codementor.gtommee.rest_tutorial.repositories.ContratoDistribuicaoRepository;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contrato_distribuicao")
public class ContratoDistribuicaoController {
    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;
    //tamanho máximo de posições do número de contrato
    private static final int TAM_NUMERO_CONTRATO = 12;
    private static final int TAM_SES = 3; //tamanho máximo de caracteres de uma situacao especial
    private static final int QUANT_MAX_SES = 10; //quantidade máxima de situações especiais que devem conter no layout SIGA
    private static final int TAM_MAX_NOME = 36;
    private static final int TAM_CPF = 14;
    private static final int TAM_MAX_DDD = 4;
    private static final int TAM_MAX_TEL = 9;
    private static final int TAM_MAX_CODIGO_FASE = 20;
    private static final int TAM_MAX_UNIDADE_OPERACIONAL = 5;
    private static final int TAM_MAX_DIA_VENCIMENTO = 2;
    private static final int TAM_MAX_DATA_PRIM_PREST_ABERTA = 6;
    private static final int TAM_MAX_VALOR_PREST_ATRASO_INT = 14; //parte inteira
    private static final int TAM_MAX_VALOR_PREST_ATRASO_DEC = 2; //parte decimal
    private static final int TAM_MAX_DIAS_ATRASO = 3;
    private static final int TAM_MAX_QUANT_PREST_ATRASO = 3;
    private static final int TAM_MAX_VALOR_DIVIDA_ATRASO_INT = 14; //parte inteira
    private static final int TAM_MAX_VALOR_DIVIDA_ATRASO_DEC = 2; //parte decimal
    private static final int TAM_MAX_REGENCIA_CRITICA = 4;
    private static final int TAM_MAX_ORIGEM_RECURSO = 3;
    private static final int TAM_MAX_LINHA_FINANCIAMENTO = 3;
    private static final int TAM_MAX_TIPO_FINANCIAMENTO = 3;
    private static final int TAM_MAX_TIPO_GARANTIA = 4;
    private static final int TAM_MAX_INDICADOR_PMCMV = 1;
    private static final int TAM_MAX_GRUPO_HABITACIONAL = 5;
    private static final int TAM_MAX_PRODUTO_FINANCEIRO = 4;


    @RequestMapping(value = "/object_from_json", method = RequestMethod.GET)
    public String createContratoDistribuicaoModel() {
//        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont3.json")) {
        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont3-1.json")) {
//        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont4.json")) {
//        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont5.json")) {
//        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont6.json")) {
            long tempo_inicio = System.currentTimeMillis();
            JsonElement jsonElement = JsonParser.parseReader(reader);

            ContratoDistribuicaoModel contratoDistribuicaoModel;
            Contratos contratos = new Contratos();
            Contrato contrato;

            for (JsonElement itemContratoArray : jsonElement.getAsJsonArray()){
                contratoDistribuicaoModel = new ContratoDistribuicaoModel();
                contrato = new Contrato();
                contrato = parseContratoObject(
                                refatorarContratoJson(itemContratoArray)
                            );
                contratos.addContrato(contrato);
                contratoDistribuicaoModel.setContrato(contrato);
                contratoDistribuicaoRepository.save(contratoDistribuicaoModel);
            }
            long tempo_fim = System.currentTimeMillis();
            long tempo_milisegundos = (tempo_fim - tempo_inicio);
            long tempo_segundos = tempo_milisegundos/1000;
            return ">>> alta -> json (milissegundos): " + tempo_milisegundos + "\n" +
                    ">>> alta -> json (segundos): " + tempo_segundos;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
        }
    }

    @RequestMapping(value = "/get_txt", method = RequestMethod.GET)
    public String getContratoDistribuicaoModelTxt() throws IOException {
        long tempo_inicio = System.currentTimeMillis();
        //lendo contratos model do banco mongodb
        List<ContratoDistribuicaoModel> listContratoDistribuicaoModel = contratoDistribuicaoRepository.findAll();
        //arquivo que será enviado para o SIGA
        FileWriter arq = new FileWriter("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\siga.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        //numeracao que aparecera no inicio de cada linha de cada contrato
        int tipoInfo = 0;

        //iterando lista de contratos vindo do banco mongoDB
        for (ContratoDistribuicaoModel contratoDistribuicaoModel: listContratoDistribuicaoModel){
            tipoInfo = 0;
            gravarArq.printf("%s", "H");  //gravando tipo registro ok
            gravarArq.printf("%s", formatter.format(date)); //gravando data ok
            //dados do contrato ?????
            gravarDadosContratoTxt(arq, contratoDistribuicaoModel.getContrato());
            //coobrigados ?????
            gravarCoobrigadoTxt(arq, contratoDistribuicaoModel.getContrato().getCoobrigados(), contratoDistribuicaoModel.getContrato().getNumeroContrato());
            //ses ok
            gravarSituacaoEspecialTxt(arq, contratoDistribuicaoModel.getContrato().getSituacoesEspeciais(), contratoDistribuicaoModel.getContrato().getNumeroContrato());
            //additional properties
            gravarArq.printf("%n");
            gravarAdditionalPropertiesTxt(arq, contratoDistribuicaoModel.getContrato().getAdditionalProperties(), tipoInfo++, contratoDistribuicaoModel.getContrato().getNumeroContrato());
            gravarArq.printf("%n");
        }
        gravarArq.printf("QTDE: %d", listContratoDistribuicaoModel.size());

        arq.close();
        long tempo_fim = System.currentTimeMillis();
        long tempo_milisegundos = (tempo_fim - tempo_inicio);
        long tempo_segundos = tempo_milisegundos/1000;
        return ">>> gerar txt (milissegundos): " + tempo_milisegundos + "\n" +
                ">>> gerar txt (segundos): " + tempo_segundos;
    }

    private void gravarDadosContratoTxt(FileWriter arq, Contrato contrato){
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%n%s", "D");
        gravarArq.printf("%s", getNumeroContratoFormatado(contrato.getNumeroContrato()));
        gravarArq.printf("%s", getNomeFormatado(contrato.getMutuario().getNome()));
        gravarArq.printf("%d", contrato.getMutuario().getTipoPessoa());
        gravarArq.printf("%s", getCpfFormatado(contrato.getMutuario().getCpf()));
        gravarArq.printf("%s", getDDDFormatado(contrato.getMutuario().getDddResidencia()));
        gravarArq.printf("%s", getTelFormatado(contrato.getMutuario().getTelResidencia()));
        gravarArq.printf("%s", getDDDFormatado(contrato.getMutuario().getDddComercial()));
        gravarArq.printf("%s", getTelFormatado(contrato.getMutuario().getTelComercial()));
        gravarArq.printf("%s", getCodigoFaseFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getUnidadeOperacionalFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getDiaVencimentoFormatado(contrato.getSituacaoContrato().getDiaVencimento()));
        gravarArq.printf("%s", getDataPrimPrestAbertaFormatada(contrato.getSituacaoContrato().getDataPrimeiraPrestacaoAberta()));
        gravarArq.printf("%s", getValorPrestAtrasoFormatado(contrato.getSituacaoContrato().getValorUltimaPrestacaoAtraso() + ""));
        gravarArq.printf("%s", getDiasAtrasoFormatado(contrato.getSituacaoContrato().getDiasAtraso() + ""));
        gravarArq.printf("%s", getQuantPrestAtrasoFormatado(contrato.getSituacaoContrato().getQuantPrestAtraso() + ""));
        gravarArq.printf("%s", getValorDividaAtrasoFormatado(contrato.getSituacaoContrato().getValorDividaEmAtraso() + ""));
        gravarArq.printf("%s", getRegenciaCriticaFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getOrigemRecursoFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getLinhaFinanciamentoFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getTipoFinanciamentoFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getTipoGarantiaFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getIndicadorPMCMVFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getGrupoHabitacionalFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getProdutoFinanceiroFormatado(contrato.getAdditionalProperties()));
    }

    private String getProdutoFinanceiroFormatado(Map<String, Object> additionalProperties) {
        String newProdutoFinanceiro = "";
        String produtoFinanceiro = additionalProperties.get("prf").toString();

        if (produtoFinanceiro.length() == TAM_MAX_PRODUTO_FINANCEIRO){ newProdutoFinanceiro = produtoFinanceiro; }
        else if (produtoFinanceiro.length() < TAM_MAX_PRODUTO_FINANCEIRO){
            for (int i = 0; i < (TAM_MAX_PRODUTO_FINANCEIRO - produtoFinanceiro.length()); i++){
                newProdutoFinanceiro += "0";
            }
            newProdutoFinanceiro += produtoFinanceiro;
        }
        else{ newProdutoFinanceiro = "    "; }

        return newProdutoFinanceiro;
    }

    private String getGrupoHabitacionalFormatado(Map<String, Object> additionalProperties) {
        String newGrupoHabitacional = "";
        String grupoHabitacional = additionalProperties.get("grpHab").toString();

        if (grupoHabitacional.length() == TAM_MAX_GRUPO_HABITACIONAL){ newGrupoHabitacional = grupoHabitacional; }
        else if (grupoHabitacional.length() < TAM_MAX_GRUPO_HABITACIONAL){
            for (int i = 0; i < (TAM_MAX_GRUPO_HABITACIONAL - grupoHabitacional.length()); i++){
                newGrupoHabitacional += "0";
            }
            newGrupoHabitacional += grupoHabitacional;
        }
        else{ newGrupoHabitacional = "     "; }

        return newGrupoHabitacional;
    }

    private String getIndicadorPMCMVFormatado(Map<String, Object> additionalProperties) {
        String newIndicador = "";
        String indicador = additionalProperties.get("pmcmv").toString();
        indicador = removeEspacosBrancos(indicador);

        newIndicador = (indicador.length() == TAM_MAX_INDICADOR_PMCMV) ? indicador : " ";

        return newIndicador;
    }

    private String getTipoGarantiaFormatada(Map<String, Object> additionalProperties) {
        String newTipoGarantia = "";
        String tipoGarantia = additionalProperties.get("tg").toString();

        if (tipoGarantia.length() == TAM_MAX_TIPO_GARANTIA){ newTipoGarantia = tipoGarantia; }
        else if (tipoGarantia.length() < TAM_MAX_TIPO_GARANTIA){
            for (int i = 0; i < (TAM_MAX_TIPO_GARANTIA - tipoGarantia.length()); i++){
                newTipoGarantia += "0";
            }
            newTipoGarantia += tipoGarantia;
        }
        else{ newTipoGarantia = "    "; }

        return newTipoGarantia;
    }

    private String getTipoFinanciamentoFormatada(Map<String, Object> additionalProperties) {
        String newTipoFinanciamento = "";
        String tipoFinanciamento = additionalProperties.get("tf").toString();

        if (tipoFinanciamento.length() == TAM_MAX_TIPO_FINANCIAMENTO){ newTipoFinanciamento = tipoFinanciamento; }
        else if (tipoFinanciamento.length() < TAM_MAX_TIPO_FINANCIAMENTO){
            for (int i = 0; i < (TAM_MAX_TIPO_FINANCIAMENTO - tipoFinanciamento.length()); i++){
                newTipoFinanciamento += "0";
            }
            newTipoFinanciamento += tipoFinanciamento;
        }
        else{ newTipoFinanciamento = "   "; }

        return newTipoFinanciamento;
    }

    private String getLinhaFinanciamentoFormatada(Map<String, Object> additionalProperties) {
        String newLinhaFinanciamento = "";
        String linhaFinanciamento = additionalProperties.get("lf").toString();

        if (linhaFinanciamento.length() == TAM_MAX_LINHA_FINANCIAMENTO){ newLinhaFinanciamento = linhaFinanciamento; }
        else if (linhaFinanciamento.length() < TAM_MAX_LINHA_FINANCIAMENTO){
            for (int i = 0; i < (TAM_MAX_LINHA_FINANCIAMENTO - linhaFinanciamento.length()); i++){
                newLinhaFinanciamento += "0";
            }
            newLinhaFinanciamento += linhaFinanciamento;
        }
        else{ newLinhaFinanciamento = "   "; }

        return newLinhaFinanciamento;
    }

    private String getOrigemRecursoFormatado(Map<String, Object> additionalProperties) {
        String newOrigemRecurso = "";
        String origemRecurso = additionalProperties.get("orr").toString();

        if (origemRecurso.length() == TAM_MAX_ORIGEM_RECURSO){ newOrigemRecurso = origemRecurso; }
        else if (origemRecurso.length() < TAM_MAX_ORIGEM_RECURSO){
            for (int i = 0; i < (TAM_MAX_ORIGEM_RECURSO - origemRecurso.length()); i++){
                newOrigemRecurso += "0";
            }
            newOrigemRecurso += origemRecurso;
        }
        else{ newOrigemRecurso = "   "; }

        return newOrigemRecurso;
    }

    private String getRegenciaCriticaFormatada(Map<String, Object> additionalProperties) {
        String newRegenciaCritica = "";
        String regenciaCritica = additionalProperties.get("rcr").toString();

        if (regenciaCritica.length() == TAM_MAX_REGENCIA_CRITICA){ newRegenciaCritica = regenciaCritica; }
        else if (regenciaCritica.length() < TAM_MAX_REGENCIA_CRITICA){
            for (int i = 0; i < (TAM_MAX_REGENCIA_CRITICA - regenciaCritica.length()); i++){
                newRegenciaCritica += "0";
            }
            newRegenciaCritica += regenciaCritica;
        }
        else{ newRegenciaCritica = "    "; }

        return newRegenciaCritica;
    }

    private String getValorDividaAtrasoFormatado(String valorDividaAtraso) {
        String newValorDividaAtraso = "";
        String [] arrayValores = valorDividaAtraso.split("\\.");
        String parteInt = arrayValores[0];
        String parteDec = arrayValores[1];

        //tratar parte inteira
        if (parteInt.length() == TAM_MAX_VALOR_DIVIDA_ATRASO_INT){ newValorDividaAtraso += parteInt; }
        else if (parteInt.length() < TAM_MAX_VALOR_DIVIDA_ATRASO_INT){
            for (int i = 0; i < (TAM_MAX_VALOR_DIVIDA_ATRASO_INT - parteInt.length()); i++){
                newValorDividaAtraso += "0";
            }
            newValorDividaAtraso += parteInt;
        }
        else {
            for (int i = 0; i < TAM_MAX_VALOR_DIVIDA_ATRASO_INT; i++){
                newValorDividaAtraso += parteInt.charAt(i);
            }
        }

        //tratar parte decimal
        if (parteDec.length() == TAM_MAX_VALOR_DIVIDA_ATRASO_DEC){ newValorDividaAtraso += "," + parteDec; }
        else if (parteDec.length() < TAM_MAX_VALOR_DIVIDA_ATRASO_DEC){
            newValorDividaAtraso += ",";
            for (int i = 0; i < (TAM_MAX_VALOR_DIVIDA_ATRASO_DEC - parteDec.length()); i++){
                newValorDividaAtraso += "0";
            }
            newValorDividaAtraso += parteDec;
        }
        else {
            newValorDividaAtraso += ",";
            for (int i = 0; i < TAM_MAX_VALOR_DIVIDA_ATRASO_DEC; i++){
                newValorDividaAtraso += parteDec.charAt(i);
            }
        }

        return newValorDividaAtraso;
    }

    private String getQuantPrestAtrasoFormatado(String quantPrestAtraso) {
        String newQuantPrestAtraso = "";

        if ((quantPrestAtraso + "").length() == TAM_MAX_QUANT_PREST_ATRASO){ newQuantPrestAtraso = quantPrestAtraso; }
        else if ((quantPrestAtraso + "").length() < TAM_MAX_QUANT_PREST_ATRASO){
            for (int i = 0; i < (TAM_MAX_QUANT_PREST_ATRASO - (quantPrestAtraso + "").length()); i++){
                newQuantPrestAtraso += "0";
            }
            newQuantPrestAtraso += quantPrestAtraso;
        }
        else {
            for (int i = ((quantPrestAtraso + "").length() - TAM_MAX_QUANT_PREST_ATRASO); i < (quantPrestAtraso + "").length(); i++){
                newQuantPrestAtraso += quantPrestAtraso.charAt(i);
            }
        }
        return newQuantPrestAtraso;
    }

    private String getDiasAtrasoFormatado(String diasAtraso) {
        String newDiasAtraso = "";

        if ((diasAtraso + "").length() == TAM_MAX_DIAS_ATRASO){ newDiasAtraso = diasAtraso; }
        else if ((diasAtraso + "").length() < TAM_MAX_DIAS_ATRASO){
            for (int i = 0; i < (TAM_MAX_DIAS_ATRASO - (diasAtraso + "").length()); i++){
                newDiasAtraso += "0";
            }
            newDiasAtraso += diasAtraso;
        }
        else {
            for (int i = ((diasAtraso + "").length() - TAM_MAX_DIAS_ATRASO); i < (diasAtraso + "").length(); i++){
                newDiasAtraso += diasAtraso.charAt(i);
            }
        }
        return newDiasAtraso;
    }

    private String getValorPrestAtrasoFormatado(String valorUltimaPrestacaoAtraso) {
        String newValorUltimaPrestacaoAtraso = "";
        String [] arrayValores = valorUltimaPrestacaoAtraso.split("\\.");
        String parteInt = arrayValores[0];
        String parteDec = arrayValores[1];

        //tratar parte inteira
        if (parteInt.length() == TAM_MAX_VALOR_PREST_ATRASO_INT){ newValorUltimaPrestacaoAtraso += parteInt; }
        else if (parteInt.length() < TAM_MAX_VALOR_PREST_ATRASO_INT){
            for (int i = 0; i < (TAM_MAX_VALOR_PREST_ATRASO_INT - parteInt.length()); i++){
                newValorUltimaPrestacaoAtraso += "0";
            }
            newValorUltimaPrestacaoAtraso += parteInt;
        }
        else {
            for (int i = 0; i < TAM_MAX_VALOR_PREST_ATRASO_INT; i++){
                newValorUltimaPrestacaoAtraso += parteInt.charAt(i);
            }
        }

        //tratar parte decimal
        if (parteDec.length() == TAM_MAX_VALOR_PREST_ATRASO_DEC){ newValorUltimaPrestacaoAtraso += "," + parteDec; }
        else if (parteDec.length() < TAM_MAX_VALOR_PREST_ATRASO_DEC){
            newValorUltimaPrestacaoAtraso += ",";
            for (int i = 0; i < (TAM_MAX_VALOR_PREST_ATRASO_DEC - parteDec.length()); i++){
                newValorUltimaPrestacaoAtraso += "0";
            }
            newValorUltimaPrestacaoAtraso += parteDec;
        }
        else {
            newValorUltimaPrestacaoAtraso += ",";
            for (int i = 0; i < TAM_MAX_VALOR_PREST_ATRASO_DEC; i++){
                newValorUltimaPrestacaoAtraso += parteDec.charAt(i);
            }
        }

        return newValorUltimaPrestacaoAtraso;
    }

    private Object getDataPrimPrestAbertaFormatada(String dataPrimeiraPrestacaoAberta) {
        String newDataPrimeiraPrestacaoAberta = "      "; //tamanho de 6 espacos em branco
        String mes = "";
        String ano = "";

        if (dataPrimeiraPrestacaoAberta.length() == TAM_MAX_DATA_PRIM_PREST_ABERTA){
            mes = dataPrimeiraPrestacaoAberta.substring(4, 6);
            ano = dataPrimeiraPrestacaoAberta.substring(0, 4);
            newDataPrimeiraPrestacaoAberta = mes + ano;
        }
        return newDataPrimeiraPrestacaoAberta;
    }

    private String getDiaVencimentoFormatado(int diaVencimento) {
        String newDiaVencimento = "";

        if ((diaVencimento + "").length() == TAM_MAX_DIA_VENCIMENTO){ newDiaVencimento = diaVencimento + ""; }
        else if ((diaVencimento + "").length() < TAM_MAX_DIA_VENCIMENTO){
            for (int i = 0; i < (TAM_MAX_DIA_VENCIMENTO - (diaVencimento + "").length()); i++){
                newDiaVencimento += "0";
            }
            newDiaVencimento += (diaVencimento + "");
        }
        else{
            for (int i = 0; i < TAM_MAX_DIA_VENCIMENTO; i++){
                newDiaVencimento += (diaVencimento + "").charAt(i);
            }
        }
        return newDiaVencimento;
    }

    private String getCodigoFaseFormatado(Map<String, Object> additionalProperties) {
        String codigoFase = additionalProperties.get("codFase").toString();
        String newCodigoFase = "";

        if (codigoFase.length() == TAM_MAX_CODIGO_FASE){ newCodigoFase = codigoFase; }
        else if (codigoFase.length() < TAM_MAX_CODIGO_FASE){
            for (int i = 0; i < (TAM_MAX_CODIGO_FASE - codigoFase.length()); i++){
                newCodigoFase += "0";
            }
            newCodigoFase += codigoFase;
        }
        else{
            for (int i = 0; i < TAM_MAX_CODIGO_FASE; i++){
                newCodigoFase += codigoFase.charAt(i);
            }
        }
        return newCodigoFase;
    }

    private String getUnidadeOperacionalFormatado(Map<String, Object> additionalProperties) {
        String unidadeOperacional = additionalProperties.get("uno").toString();
        String newUnidadeOperacional = "";

        if (unidadeOperacional.length() == TAM_MAX_UNIDADE_OPERACIONAL){ newUnidadeOperacional = unidadeOperacional; }
        else if (unidadeOperacional.length() < TAM_MAX_UNIDADE_OPERACIONAL){
            for (int i = 0; i < (TAM_MAX_UNIDADE_OPERACIONAL - unidadeOperacional.length()); i++){
                newUnidadeOperacional += "0";
            }
            newUnidadeOperacional += unidadeOperacional;
        }
        else{
            for (int i = 0; i < TAM_MAX_UNIDADE_OPERACIONAL; i++){
                newUnidadeOperacional += unidadeOperacional.charAt(i);
            }
        }
        return newUnidadeOperacional;
    }

    private void gravarAdditionalPropertiesTxt(FileWriter arq, Map<String, Object> additionalProperties, int tipoInfo, Long numeroContrato) {
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%d ", tipoInfo);
        additionalProperties.forEach((k, v) -> {
            gravarArq.printf("\"%s\" ", v);
        });
    }

    private void gravarCoobrigadoTxt(FileWriter arq, List<Coobrigado> listCoobrigado, Long numeroContrato){
        PrintWriter gravarArq = new PrintWriter(arq);

        for (int i = 0; i < listCoobrigado.size(); i++){
            gravarArq.printf("%n%s", "C");  //gravando tipo registro
            gravarArq.printf("%s", getNumeroContratoFormatado(numeroContrato));
            gravarArq.printf("%s", getNomeFormatado(listCoobrigado.get(i).getNome())); //ok
            gravarArq.printf("%s ", getCpfFormatado(listCoobrigado.get(i).getCpf())); //ok
            gravarArq.printf("%s ", listCoobrigado.get(i).getDddResidencia()); //?
            gravarArq.printf("%s ", listCoobrigado.get(i).getTelResidencia()); //?
            gravarArq.printf("%s ", listCoobrigado.get(i).getDddCelular()); //?
            gravarArq.printf("%s ", listCoobrigado.get(i).getTelCelular()); //?
            gravarArq.printf("%s ", listCoobrigado.get(i).getDddComercial()); //?
            gravarArq.printf("%s ", listCoobrigado.get(i).getTelComercial()); //?
            gravarArq.printf("%s", listCoobrigado.get(i).getRamalComercial()); //?
        }
    }

    private void gravarSituacaoEspecialTxt(FileWriter arq, List<SituacaoEspecial> listSituacaoEspecial, Long numeroContrato){
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%n%s", "S");  //gravando tipo registro
        gravarArq.printf("%s", getNumeroContratoFormatado(numeroContrato)); //gravando numero contrato
        //gravando situacoes especiais existentes
        for (int i = 0; i < listSituacaoEspecial.size(); i++){
            gravarArq.printf("%s", getSesString(listSituacaoEspecial.get(i).getCodigoSituacaoEspecial()));
        }
        //gravando situacoes especiais com zero
        for (int i = 0; i < (QUANT_MAX_SES - listSituacaoEspecial.size()); i++){
            gravarArq.printf("%s", getSesString(0));
        }
    }

    private Contrato parseContratoObject(JsonObject contratoJson) throws IOException{
        //clone com atributos restantes que serão gravados no contrato atribuicao
        JsonObject clone = contratoJson.deepCopy();

        String companyJsonString = clone.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Contrato contrato = objectMapper.readValue(companyJsonString, Contrato.class);

        return contrato;
    }

    /**
     * function que refatora o contrato vindo da alta em formato json
     * */
    private JsonObject refatorarContratoJson(JsonElement contratoJsonElement){
        //ses
        JsonElement newSes = tratarRefatoracaoSes(contratoJsonElement);
        contratoJsonElement.getAsJsonObject().remove("ses");
        contratoJsonElement.getAsJsonObject().add("ses", newSes);

        //coobrigados
        JsonElement newCoobrigados = tratarRefatoracaoCoobrigados(contratoJsonElement);
        contratoJsonElement.getAsJsonObject().remove("coobrigados");
        contratoJsonElement.getAsJsonObject().add("coobrigados", newCoobrigados);

        //mutuario
        contratoJsonElement = tratarRefatoracaoMutuario(contratoJsonElement);

        //situacao contrato
        contratoJsonElement = tratarRefatoracaoSituacaoContrato(contratoJsonElement);

        return contratoJsonElement.getAsJsonObject();
    }

    private JsonElement tratarRefatoracaoSes(JsonElement contratoJsonElement){
        JsonElement newArraySituacaoEspecial = new JsonArray();
        JsonArray arraySituacaoEspecial = contratoJsonElement.getAsJsonObject().getAsJsonArray("ses");
        for (JsonElement itemSes : arraySituacaoEspecial){
            //itemSes não pode ser null
            if (itemSes.isJsonObject()){
                if (getIntegerCodigoSes(itemSes) > 0) {
                    JsonElement newJsonElementSituacaoEspecial = new JsonObject();
                    newJsonElementSituacaoEspecial.getAsJsonObject().addProperty("codigo", getIntegerCodigoSes(itemSes));
                    newArraySituacaoEspecial.getAsJsonArray().add(newJsonElementSituacaoEspecial);
                }
            }
        }
        return newArraySituacaoEspecial;
    }

    private JsonElement tratarRefatoracaoCoobrigados(JsonElement contratoJsonElement){
        JsonArray arrayCoobrigados = contratoJsonElement.getAsJsonObject().getAsJsonArray("coobrigados");
        JsonElement newArrayCoobrigados = arrayCoobrigados.deepCopy();
        for (JsonElement itemCoobrigado : arrayCoobrigados){
            //itemSes não pode ser null
            if (!(itemCoobrigado.isJsonObject())){
                newArrayCoobrigados.getAsJsonArray().remove(itemCoobrigado);
            }
            else{
                //TipPesC não pode ser <= 0
                if (!(itemCoobrigado.getAsJsonObject().getAsJsonPrimitive("TipPesC").getAsInt() > 0)) {
                    newArrayCoobrigados.getAsJsonArray().remove(itemCoobrigado);
                }
            }
        }
        return newArrayCoobrigados;
    }

    private JsonElement tratarRefatoracaoMutuario (JsonElement contratoJsonElement){
        JsonElement newJsonMutuario = new JsonObject();
        JsonElement newContratoJsonElement = contratoJsonElement.deepCopy();

        newJsonMutuario.getAsJsonObject().add("nome", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("nome"));
        newJsonMutuario.getAsJsonObject().add("TipPes", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("TipPes"));
        newJsonMutuario.getAsJsonObject().add("cpf", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cpf"));
        newJsonMutuario.getAsJsonObject().add("dddRes", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("dddRes"));
        newJsonMutuario.getAsJsonObject().add("telRes", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("telRes"));
        newJsonMutuario.getAsJsonObject().add("dddCel", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("dddCel"));
        newJsonMutuario.getAsJsonObject().add("telCel", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("telCel"));
        newJsonMutuario.getAsJsonObject().add("dddCom", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("dddCom"));
        newJsonMutuario.getAsJsonObject().add("telCom", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("telCom"));
        newJsonMutuario.getAsJsonObject().add("ramCom", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("ramCom"));
        newJsonMutuario.getAsJsonObject().add("email", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("email"));

        newContratoJsonElement.getAsJsonObject().remove("nome");
        newContratoJsonElement.getAsJsonObject().remove("TipPes");
        newContratoJsonElement.getAsJsonObject().remove("cpf");
        newContratoJsonElement.getAsJsonObject().remove("dddRes");
        newContratoJsonElement.getAsJsonObject().remove("telRes");
        newContratoJsonElement.getAsJsonObject().remove("dddCel");
        newContratoJsonElement.getAsJsonObject().remove("telCel");
        newContratoJsonElement.getAsJsonObject().remove("dddCom");
        newContratoJsonElement.getAsJsonObject().remove("telCom");
        newContratoJsonElement.getAsJsonObject().remove("ramCom");
        newContratoJsonElement.getAsJsonObject().remove("email");

        //mutuario endereco
        newContratoJsonElement = tratarRefatoracaoMutuarioEndereco(newContratoJsonElement);
        newJsonMutuario.getAsJsonObject().add("endereco", newContratoJsonElement.getAsJsonObject().getAsJsonObject("endereco"));
        newContratoJsonElement.getAsJsonObject().remove("endereco");

        //mutuario endereco correspondencia
        newContratoJsonElement = tratarRefatoracaoMutuarioEnderecoCorrespondencia(newContratoJsonElement);
        newJsonMutuario.getAsJsonObject().add("endCorresp", newContratoJsonElement.getAsJsonObject().getAsJsonObject("endCorresp"));
        newContratoJsonElement.getAsJsonObject().remove("endCorresp");

        newContratoJsonElement.getAsJsonObject().add("mutuario", newJsonMutuario);

        return  newContratoJsonElement;
    }

    private JsonElement tratarRefatoracaoMutuarioEndereco (JsonElement contratoJsonElement){
        JsonElement newJsonMutuarioEndereco = new JsonObject();
        JsonElement newContratoJsonElement = contratoJsonElement.deepCopy();

        newJsonMutuarioEndereco.getAsJsonObject().add("abvEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("abvEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("nomEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("nomEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("nroEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("nroEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cplEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cplEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("baiEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("baiEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cidEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cidEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("ufEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("ufEnd"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cepEnd", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cepEnd"));

        newContratoJsonElement.getAsJsonObject().remove("abvEnd");
        newContratoJsonElement.getAsJsonObject().remove("nomEnd");
        newContratoJsonElement.getAsJsonObject().remove("nroEnd");
        newContratoJsonElement.getAsJsonObject().remove("cplEnd");
        newContratoJsonElement.getAsJsonObject().remove("baiEnd");
        newContratoJsonElement.getAsJsonObject().remove("cidEnd");
        newContratoJsonElement.getAsJsonObject().remove("ufEnd");
        newContratoJsonElement.getAsJsonObject().remove("cepEnd");

        newContratoJsonElement.getAsJsonObject().add("endereco", newJsonMutuarioEndereco);

        return  newContratoJsonElement;
    }

    private JsonElement tratarRefatoracaoMutuarioEnderecoCorrespondencia (JsonElement contratoJsonElement){
        JsonElement newJsonMutuarioEndereco = new JsonObject();
        JsonElement newContratoJsonElement = contratoJsonElement.deepCopy();

        newJsonMutuarioEndereco.getAsJsonObject().add("abvCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("abvCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("nomCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("nomCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("nroCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("nroCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cplCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cplCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("baiCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("baiCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cidCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cidCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("ufCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("ufCor"));
        newJsonMutuarioEndereco.getAsJsonObject().add("cepCor", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("cepCor"));

        newContratoJsonElement.getAsJsonObject().remove("abvCor");
        newContratoJsonElement.getAsJsonObject().remove("nomCor");
        newContratoJsonElement.getAsJsonObject().remove("nroCor");
        newContratoJsonElement.getAsJsonObject().remove("cplCor");
        newContratoJsonElement.getAsJsonObject().remove("baiCor");
        newContratoJsonElement.getAsJsonObject().remove("cidCor");
        newContratoJsonElement.getAsJsonObject().remove("ufCor");
        newContratoJsonElement.getAsJsonObject().remove("cepCor");

        newContratoJsonElement.getAsJsonObject().add("endCorresp", newJsonMutuarioEndereco);

        return  newContratoJsonElement;
    }

    private JsonElement tratarRefatoracaoSituacaoContrato (JsonElement contratoJsonElement){
        JsonElement newJsonSituacaoContrato = new JsonObject();
        JsonElement newContratoJsonElement = contratoJsonElement.deepCopy();

        newJsonSituacaoContrato.getAsJsonObject().add("diaVenc", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("diaVenc"));
        newJsonSituacaoContrato.getAsJsonObject().add("dtPriAber", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("dtPriAber"));
        newJsonSituacaoContrato.getAsJsonObject().add("qtdPreAtraso", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("qtdPreAtraso"));
        newJsonSituacaoContrato.getAsJsonObject().add("diasAtraso", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("diasAtraso"));
        newJsonSituacaoContrato.getAsJsonObject().add("DiasAtrUltPrePg", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("DiasAtrUltPrePg"));
        newJsonSituacaoContrato.getAsJsonObject().add("PercDivPg", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("PercDivPg"));
        newJsonSituacaoContrato.getAsJsonObject().add("garAtu", newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("garAtu"));

        //tratando valorUltimaPrestacaoAtraso
        String [] partesNumber = newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("VlrPreAtr").toString().split(",");
        Integer parteInteira = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[0])));
        Integer parteDecimal = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[1])));
        newJsonSituacaoContrato.getAsJsonObject().addProperty("VlrPreAtr", Double.parseDouble(parteInteira + "." + parteDecimal));

        //tratando valorDividaAtraso
        partesNumber = newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("vlrDiv").toString().split(",");
        parteInteira = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[0])));
        parteDecimal = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[1])));
        newJsonSituacaoContrato.getAsJsonObject().addProperty("vlrDiv", Double.parseDouble(parteInteira + "." + parteDecimal));

        newContratoJsonElement.getAsJsonObject().remove("diaVenc");
        newContratoJsonElement.getAsJsonObject().remove("dtPriAber");
        newContratoJsonElement.getAsJsonObject().remove("qtdPreAtraso");
        newContratoJsonElement.getAsJsonObject().remove("diasAtraso");
        newContratoJsonElement.getAsJsonObject().remove("VlrPreAtr");
        newContratoJsonElement.getAsJsonObject().remove("DiasAtrUltPrePg");
        newContratoJsonElement.getAsJsonObject().remove("PercDivPg");
        newContratoJsonElement.getAsJsonObject().remove("vlrDiv");
        newContratoJsonElement.getAsJsonObject().remove("garAtu");

        newContratoJsonElement.getAsJsonObject().add("situacaoContrato", newJsonSituacaoContrato);

        return  newContratoJsonElement;
    }

    private String removeEspacosBrancos(String dado){
        String newDado = "";
        for (int i = 0; i < dado.length(); i++){
            if (dado.charAt(i) != ' '){
                newDado += dado.charAt(i);
            }
        }
        return newDado;
    }

    private String removeAspas(String dado){
        String newDado = "";
        for (int i = 0; i < dado.length(); i++){
            if (dado.charAt(i) != '\"'){
                newDado += dado.charAt(i);
            }
        }
        return newDado;
    }

    private Integer getIntegerCodigoSes(JsonElement jsonElement){
        return Integer.parseInt(
                jsonElement.getAsJsonObject().get("codigo").getAsString()
        );
    }

    private String getNumeroContratoFormatado(Long numeroContrato){
        String newNumeroContrato = "";

        if ((numeroContrato + "").length() < TAM_NUMERO_CONTRATO){
            for (int i = 0; i < (TAM_NUMERO_CONTRATO - (numeroContrato + "").length()); i++){
                newNumeroContrato += "0";
            }
            newNumeroContrato += numeroContrato + "";
        }
        else{
            newNumeroContrato += numeroContrato + "";
        }
        return newNumeroContrato;
    }

    private String getSesString(Integer situacaoEspecial){
        String newSituacaoEspecial = "";

        if ((situacaoEspecial + "").length() < TAM_SES){
            for (int i = 0; i < (TAM_SES - (situacaoEspecial + "").length()); i++){
                newSituacaoEspecial += "0";
            }
            newSituacaoEspecial += situacaoEspecial + "";
        }
        else{
            newSituacaoEspecial += situacaoEspecial + "";
        }
        return newSituacaoEspecial;
    }

    private String getNomeFormatado(String nome){
        String newNome = "";

        if (nome.length() == TAM_MAX_NOME){
            newNome = nome;
        }
        else if (nome.length() > TAM_MAX_NOME){
            newNome = nome.substring(0, TAM_MAX_NOME);
        }
        else {
            newNome = nome;
            for (int i = 0; i < (TAM_MAX_NOME - nome.length()); i++){
                newNome += " ";
            }
        }

        return newNome;
    }

    private String getCpfFormatado(String cpf){
        String newCpf = "";

        if (cpf.length() == TAM_CPF){
            newCpf = cpf;
        }
        else if (cpf.length() < TAM_CPF){
            for (int i = 0; i < (TAM_CPF - cpf.length()); i++){
                newCpf += "0";
            }
            newCpf += cpf;
        }

        return newCpf;
    }

    private String getDDDFormatado(String ddd){
        String newDdd = "";

        if (ddd.length() == TAM_MAX_DDD){
            newDdd = ddd;
        }
        else if (ddd.length() < TAM_MAX_DDD){
            for (int i = 0; i < (TAM_MAX_DDD - ddd.length()); i++){ newDdd += "0"; }
            newDdd += ddd;
        }
        else{
            for (int i = 0; i < TAM_MAX_DDD; i++){ newDdd += ddd.charAt(i); }
        }
        return newDdd;
    }

    private String getTelFormatado(String tel){
        String newTel = "";

        if (tel.length() == TAM_MAX_TEL){
            newTel = tel;
        }
        else if (tel.length() < TAM_MAX_TEL){
            for (int i = 0; i < (TAM_MAX_TEL - tel.length()); i++){ newTel += "0"; }
            newTel += tel;
        }
        else{
            for (int i = 0; i < TAM_MAX_TEL; i++){ newTel += tel.charAt(i); }
        }
        return newTel;
    }
}
