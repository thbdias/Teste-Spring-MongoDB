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
import java.util.*;

@RestController
@RequestMapping("/contrato_distribuicao")
public class ContratoDistribuicaoController {
    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

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

        //iterando lista de contratos vindo do banco mongoDB
        for (ContratoDistribuicaoModel contratoDistribuicaoModel: listContratoDistribuicaoModel){
            gravarArq.printf("%s", "H");  //gravando tipo registro ok
            gravarArq.printf("%s", formatter.format(date)); //gravando data ok
            //dados do contrato ?????
            gravarDadosContratoTxt(arq, contratoDistribuicaoModel.getContrato());
            //coobrigados ok
            gravarCoobrigadoTxt(arq, contratoDistribuicaoModel.getContrato().getCoobrigados(), contratoDistribuicaoModel.getContrato().getNumeroContrato());
            //ses ok
            gravarSituacaoEspecialTxt(arq, contratoDistribuicaoModel.getContrato().getSituacoesEspeciais(), contratoDistribuicaoModel.getContrato().getNumeroContrato());
            //acao cobranca ???
            gravarAcaoCobrancaTxt(arq, contratoDistribuicaoModel.getContrato().getAcoesCobranca(), contratoDistribuicaoModel.getContrato().getNumeroContrato());
            gravarArq.printf("%n");
        }

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
        gravarArq.printf("%s", getValorPrestAtrasoFormatado(contrato.getSituacaoContrato().getValorUltimaPrestacaoAtraso()));
        gravarArq.printf("%s", getDiasAtrasoFormatado(contrato.getSituacaoContrato().getDiasAtraso() + ""));
        gravarArq.printf("%s", getQuantPrestAtrasoFormatado(contrato.getSituacaoContrato().getQuantPrestAtraso() + ""));
        gravarArq.printf("%s", getValorDividaAtrasoFormatado(contrato.getSituacaoContrato().getValorDividaEmAtraso()));
        gravarArq.printf("%s", getRegenciaCriticaFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getOrigemRecursoFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getLinhaFinanciamentoFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getTipoFinanciamentoFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getTipoGarantiaFormatada(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getIndicadorPMCMVFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getGrupoHabitacionalFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getProdutoFinanceiroFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getCredorFormatado(contrato.getCodigoCredor() + ""));
        gravarArq.printf("%s", getCodigoAdministradorFormatado(contrato.getCodigoAdminitrador() + ""));
        gravarArq.printf("%s", getUfContratoFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getDataAssinaturaFormatada(contrato.getDataAssinatura()));
        gravarArq.printf("%s", getSubTituloContabilFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getIndicadorObraFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getIndicadorMaterialConstrucaoFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getIndicadorPrazoRemanescenteFormatado(contrato.getAdditionalProperties()));
        gravarArq.printf("%s", getTipoEnderecoFormatado(contrato.getMutuario().getEndereco().getAbreviacao()));
        gravarArq.printf("%s", getNomeEnderecoFormatado(contrato.getMutuario().getEndereco().getLogradouro()));
        gravarArq.printf("%s", getNumeroEnderecoFormatado(contrato.getMutuario().getEndereco().getNumero()));
        gravarArq.printf("%s", getComplementoEnderecoFormatado(contrato.getMutuario().getEndereco().getComplemento()));
        gravarArq.printf("%s", getBairroFormatado(contrato.getMutuario().getEndereco().getBairro()));
        gravarArq.printf("%s", getCidadeFormatada(contrato.getMutuario().getEndereco().getCidade()));
        gravarArq.printf("%s", getUfMutuarioFormatada(contrato.getMutuario().getEndereco().getUf()));
        gravarArq.printf("%s", getCepFormatado(contrato.getMutuario().getEndereco().getCep() + ""));
        gravarArq.printf("%s", getEmailFormatado(contrato.getMutuario().getEmail()));
        gravarArq.printf("%s", getValorGarantiaAtualizadaFormatada(contrato.getSituacaoContrato().getValorGarantiaAtualizada() + ""));
        gravarArq.printf("%s", getEmailFormatado(contrato.getMutuario().getEmailAlternativo()));
        gravarArq.printf("%s", getDDDFormatado(contrato.getMutuario().getDddAlternativo()));
        gravarArq.printf("%s", getTelFormatado(contrato.getMutuario().getTelAlternativo()));
    }

    private String getValorGarantiaAtualizadaFormatada(String valorGarantiaAtualizada) {
        String newValorGarantiaAtualizada = "";
        String [] arrayValores = valorGarantiaAtualizada.split("\\.");
        String parteInt = arrayValores[0];
        String parteDec = arrayValores[1];

        //tratar parte inteira
        if (parteInt.length() == EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_INT.getValor()){ newValorGarantiaAtualizada += parteInt; }
        else if (parteInt.length() < EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_INT.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_INT.getValor() - parteInt.length()); i++){
                newValorGarantiaAtualizada += "0";
            }
            newValorGarantiaAtualizada += parteInt;
        }
        else {
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_INT.getValor(); i++){
                newValorGarantiaAtualizada += parteInt.charAt(i);
            }
        }

        //tratar parte decimal
        if (parteDec.length() == EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_DEC.getValor()){ newValorGarantiaAtualizada += "," + parteDec; }
        else if (parteDec.length() < EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_DEC.getValor()){
            newValorGarantiaAtualizada += ",";
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_DEC.getValor() - parteDec.length()); i++){
                newValorGarantiaAtualizada += "0";
            }
            newValorGarantiaAtualizada += parteDec;
        }
        else {
            newValorGarantiaAtualizada += ",";
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_VALOR_GARANTIA_DEC.getValor(); i++){
                newValorGarantiaAtualizada += parteDec.charAt(i);
            }
        }

        return newValorGarantiaAtualizada;
    }

    private String getEmailFormatado(String email) {
        String newEmail = "";

        if (email.length() == EnumLayoutSiga.TAM_MAX_EMAIL.getValor()){ newEmail = email; }
        else if (email.length() < EnumLayoutSiga.TAM_MAX_EMAIL.getValor()){
            newEmail = email;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_EMAIL.getValor() - email.length()); i++){
                newEmail += " ";
            }
        }
        //adicionando TAM_MAX_EMAIL de espacos em branco
        else{ newEmail = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_EMAIL.getValor(), " ")); }

        return newEmail;
    }

    private String getCepFormatado(String cep) {
        String newCep = "";

        if (cep.length() == EnumLayoutSiga.TAM_MAX_CEP.getValor()){ newCep = cep; }
        else if (cep.length() < EnumLayoutSiga.TAM_MAX_CEP.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CEP.getValor() - cep.length()); i++){
                newCep += "0";
            }
            newCep = cep;
        }
        //adicionando TAM_MAX_CEP de espacos em branco
        else{ newCep = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_CEP.getValor(), "0")); }

        return newCep;
    }

    private String getUfMutuarioFormatada(String uf) {
        return ((uf.length() == EnumLayoutSiga.TAM_MAX_UF_MUTUARIO.getValor()) ? uf : "  ");
    }

    private String getCidadeFormatada(String cidade) {
        String newCidade = "";

        if (cidade.length() == EnumLayoutSiga.TAM_MAX_CIDADE.getValor()){ newCidade = cidade; }
        else if (cidade.length() < EnumLayoutSiga.TAM_MAX_CIDADE.getValor()){
            newCidade = cidade;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CIDADE.getValor() - cidade.length()); i++){
                newCidade += " ";
            }
        }
        //adicionando TAM_MAX_CIDADE de espacos em branco
        else{ newCidade = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_CIDADE.getValor(), " ")); }

        return newCidade;
    }

    private String getBairroFormatado(String bairro) {
        String newBairro = "";

        if (bairro.length() == EnumLayoutSiga.TAM_MAX_BAIRRO.getValor()){ newBairro = bairro; }
        else if (bairro.length() < EnumLayoutSiga.TAM_MAX_BAIRRO.getValor()){
            newBairro = bairro;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_BAIRRO.getValor() - bairro.length()); i++){
                newBairro += " ";
            }
        }
        //adicionando TAM_MAX_BAIRRO de espacos em branco
        else{ newBairro = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_BAIRRO.getValor(), " ")); }

        return newBairro;
    }

    private String getComplementoEnderecoFormatado(String complementoEndereco) {
        String newComplementoEndereco = "";

        if (complementoEndereco.length() == EnumLayoutSiga.TAM_MAX_COMPLEMENTO_ENDERECO.getValor()){ newComplementoEndereco = complementoEndereco; }
        else if (complementoEndereco.length() < EnumLayoutSiga.TAM_MAX_COMPLEMENTO_ENDERECO.getValor()){
            newComplementoEndereco = complementoEndereco;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_COMPLEMENTO_ENDERECO.getValor() - complementoEndereco.length()); i++){
                newComplementoEndereco += " ";
            }
        }
        //adicionando TAM_MAX_COMPLEMENTO_ENDERECO de espacos em branco
        else{ newComplementoEndereco = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_COMPLEMENTO_ENDERECO.getValor(), " ")); }

        return newComplementoEndereco;
    }

    private String getNumeroEnderecoFormatado(String numeroEndereco) {
        String newNumeroEndereco = "";

        if (numeroEndereco.length() == EnumLayoutSiga.TAM_MAX_NUMERO_ENDERECO.getValor()){ newNumeroEndereco = numeroEndereco; }
        else if (numeroEndereco.length() < EnumLayoutSiga.TAM_MAX_NUMERO_ENDERECO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_NUMERO_ENDERECO.getValor() - numeroEndereco.length()); i++){
                newNumeroEndereco += "0";
            }
            newNumeroEndereco = numeroEndereco;
        }
        //adicionando TAM_MAX_NUMERO_ENDERECO de espacos em branco
        else{ newNumeroEndereco = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_NUMERO_ENDERECO.getValor(), "0")); }

        return newNumeroEndereco;
    }

    private String getNomeEnderecoFormatado(String nomeEndereco) {
        String newNomeEndereco = "";

        if (nomeEndereco.length() == EnumLayoutSiga.TAM_MAX_NOME_ENDERECO.getValor()){ newNomeEndereco = nomeEndereco; }
        else if (nomeEndereco.length() < EnumLayoutSiga.TAM_MAX_NOME_ENDERECO.getValor()){
            newNomeEndereco = nomeEndereco;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_NOME_ENDERECO.getValor() - nomeEndereco.length()); i++){
                newNomeEndereco += " ";
            }
        }
        //adicionando TAM_MAX_NOME_ENDERECO de espacos em branco
        else{ newNomeEndereco = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_NOME_ENDERECO.getValor(), " ")); }

        return newNomeEndereco;
    }

    private String getTipoEnderecoFormatado(String abreviacao) {
        String newAbreviacao = "";

        if (abreviacao.length() == EnumLayoutSiga.TAM_MAX_TIPO_ENDERECO.getValor()){ newAbreviacao = abreviacao; }
        else if (abreviacao.length() < EnumLayoutSiga.TAM_MAX_TIPO_ENDERECO.getValor()){
            newAbreviacao = abreviacao;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_TIPO_ENDERECO.getValor() - abreviacao.length()); i++){
                newAbreviacao += " ";
            }
        }
        else{ newAbreviacao = "   "; }

        return newAbreviacao;
    }

    private String getIndicadorPrazoRemanescenteFormatado(Map<String, Object> additionalProperties) {
        String newPrazoRemanescente = "";
        String prazoRemanescente = additionalProperties.get("pzrem").toString();

        if (prazoRemanescente.length() == EnumLayoutSiga.TAM_MAX_PRAZO_REMANESCENTE.getValor()){ newPrazoRemanescente = prazoRemanescente; }
        else if (prazoRemanescente.length() < EnumLayoutSiga.TAM_MAX_PRAZO_REMANESCENTE.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_PRAZO_REMANESCENTE.getValor() - prazoRemanescente.length()); i++){
                newPrazoRemanescente += "0";
            }
            newPrazoRemanescente += prazoRemanescente;
        }
        else{ newPrazoRemanescente = "      "; }

        return newPrazoRemanescente;
    }

    private String getIndicadorMaterialConstrucaoFormatado(Map<String, Object> additionalProperties) {
        String indicadorMaterialConstrucao = additionalProperties.get("matCon").toString();
        indicadorMaterialConstrucao = removeAspas(indicadorMaterialConstrucao);
        indicadorMaterialConstrucao = removeEspacosBrancos(indicadorMaterialConstrucao);

        return ((indicadorMaterialConstrucao.length() == EnumLayoutSiga.TAM_MAX_INDICADOR_MATERIAL_CONSTRUCAO.getValor()) ? indicadorMaterialConstrucao : " ");
    }

    private String getIndicadorObraFormatado(Map<String, Object> additionalProperties) {
        String indicadorObra = additionalProperties.get("obra").toString();
        indicadorObra = removeAspas(indicadorObra);
        indicadorObra = removeEspacosBrancos(indicadorObra);

        return ((indicadorObra.length() == EnumLayoutSiga.TAM_MAX_INDICADOR_OBRA.getValor()) ? indicadorObra : " ");
    }

    private String getSubTituloContabilFormatado(Map<String, Object> additionalProperties) {
        String newSubtituloContabil = "";
        String subtituloContabil = additionalProperties.get("subContab").toString();

        if (subtituloContabil.length() == EnumLayoutSiga.TAM_MAX_SUBTITULO_CONTABIL.getValor()){ newSubtituloContabil = subtituloContabil; }
        else if (subtituloContabil.length() < EnumLayoutSiga.TAM_MAX_SUBTITULO_CONTABIL.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_SUBTITULO_CONTABIL.getValor() - subtituloContabil.length()); i++){
                newSubtituloContabil += "0";
            }
            newSubtituloContabil += subtituloContabil;
        }
        else{ newSubtituloContabil = "      "; }

        return newSubtituloContabil;
    }

    private String getDataAssinaturaFormatada(String dataAssinatura) {
        String newDataAssinatura = "        ";
        dataAssinatura = removeAspas(dataAssinatura);
        dataAssinatura = removeEspacosBrancos(dataAssinatura);
        String dia = "", mes = "", ano = "";

        if (dataAssinatura.length() == 8) { //8 = yyyy + mm + dd
            dia = dataAssinatura.substring(6, 8);
            mes = dataAssinatura.substring(4, 6);
            ano = dataAssinatura.substring(0, 4);

            if ((Integer.parseInt(dia) > 0) && (Integer.parseInt(dia) <= 31) &&
                    (Integer.parseInt(mes) > 0) && (Integer.parseInt(mes) <= 12)) {
                newDataAssinatura = ano + "" + mes + "" + dia;
            }
        }

        return newDataAssinatura;
    }

    private String getUfContratoFormatado(Map<String, Object> additionalProperties) {
        String newUf = "";
        String uf = additionalProperties.get("uf").toString();
        uf = removeEspacosBrancos(uf);

        newUf = (uf.length() == EnumLayoutSiga.TAM_MAX_UF_CONTRATO.getValor()) ? uf : "  ";

        return newUf;
    }

    private String getCodigoAdministradorFormatado(String codigoAdministrador) {
        String newCodigoAdministrador = "";

        if (codigoAdministrador.length() == EnumLayoutSiga.TAM_MAX_CODIGO_ADMINISTRADOR.getValor()){ newCodigoAdministrador = codigoAdministrador; }
        else if (codigoAdministrador.length() < EnumLayoutSiga.TAM_MAX_CODIGO_ADMINISTRADOR.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CODIGO_ADMINISTRADOR.getValor() - codigoAdministrador.length()); i++){
                newCodigoAdministrador += "0";
            }
            newCodigoAdministrador += codigoAdministrador;
        }
        else{ newCodigoAdministrador = "    "; }

        return newCodigoAdministrador;
    }

    private String getCredorFormatado(String codigoCredor) {
        String newCodigoCredor = "";

        if (codigoCredor.length() == EnumLayoutSiga.TAM_MAX_CODIGO_CREDOR.getValor()){ newCodigoCredor = codigoCredor; }
        else if (codigoCredor.length() < EnumLayoutSiga.TAM_MAX_CODIGO_CREDOR.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CODIGO_CREDOR.getValor() - codigoCredor.length()); i++){
                newCodigoCredor += "0";
            }
            newCodigoCredor += codigoCredor;
        }
        else{ newCodigoCredor = "    "; }

        return newCodigoCredor;
    }

    private String getProdutoFinanceiroFormatado(Map<String, Object> additionalProperties) {
        String newProdutoFinanceiro = "";
        String produtoFinanceiro = additionalProperties.get("prf").toString();

        if (produtoFinanceiro.length() == EnumLayoutSiga.TAM_MAX_PRODUTO_FINANCEIRO.getValor()){ newProdutoFinanceiro = produtoFinanceiro; }
        else if (produtoFinanceiro.length() < EnumLayoutSiga.TAM_MAX_PRODUTO_FINANCEIRO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_PRODUTO_FINANCEIRO.getValor() - produtoFinanceiro.length()); i++){
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

        if (grupoHabitacional.length() == EnumLayoutSiga.TAM_MAX_GRUPO_HABITACIONAL.getValor()){ newGrupoHabitacional = grupoHabitacional; }
        else if (grupoHabitacional.length() < EnumLayoutSiga.TAM_MAX_GRUPO_HABITACIONAL.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_GRUPO_HABITACIONAL.getValor() - grupoHabitacional.length()); i++){
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

        newIndicador = (indicador.length() == EnumLayoutSiga.TAM_MAX_INDICADOR_PMCMV.getValor()) ? indicador : " ";

        return newIndicador;
    }

    private String getTipoGarantiaFormatada(Map<String, Object> additionalProperties) {
        String newTipoGarantia = "";
        String tipoGarantia = additionalProperties.get("tg").toString();

        if (tipoGarantia.length() == EnumLayoutSiga.TAM_MAX_TIPO_GARANTIA.getValor()){ newTipoGarantia = tipoGarantia; }
        else if (tipoGarantia.length() < EnumLayoutSiga.TAM_MAX_TIPO_GARANTIA.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_TIPO_GARANTIA.getValor() - tipoGarantia.length()); i++){
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

        if (tipoFinanciamento.length() == EnumLayoutSiga.TAM_MAX_TIPO_FINANCIAMENTO.getValor()){ newTipoFinanciamento = tipoFinanciamento; }
        else if (tipoFinanciamento.length() < EnumLayoutSiga.TAM_MAX_TIPO_FINANCIAMENTO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_TIPO_FINANCIAMENTO.getValor() - tipoFinanciamento.length()); i++){
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

        if (linhaFinanciamento.length() == EnumLayoutSiga.TAM_MAX_LINHA_FINANCIAMENTO.getValor()){ newLinhaFinanciamento = linhaFinanciamento; }
        else if (linhaFinanciamento.length() < EnumLayoutSiga.TAM_MAX_LINHA_FINANCIAMENTO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_LINHA_FINANCIAMENTO.getValor() - linhaFinanciamento.length()); i++){
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

        if (origemRecurso.length() == EnumLayoutSiga.TAM_MAX_ORIGEM_RECURSO.getValor()){ newOrigemRecurso = origemRecurso; }
        else if (origemRecurso.length() < EnumLayoutSiga.TAM_MAX_ORIGEM_RECURSO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_ORIGEM_RECURSO.getValor() - origemRecurso.length()); i++){
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

        if (regenciaCritica.length() == EnumLayoutSiga.TAM_MAX_REGENCIA_CRITICA.getValor()){ newRegenciaCritica = regenciaCritica; }
        else if (regenciaCritica.length() < EnumLayoutSiga.TAM_MAX_REGENCIA_CRITICA.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_REGENCIA_CRITICA.getValor() - regenciaCritica.length()); i++){
                newRegenciaCritica += "0";
            }
            newRegenciaCritica += regenciaCritica;
        }
        else{ newRegenciaCritica = "    "; }

        return newRegenciaCritica;
    }

    private String getValorDividaAtrasoFormatado(Double valorDividaAtraso) {
        String newValorDividaAtraso = "";
        boolean isNegativo = valorDividaAtraso < 0;
        Double valorTratado;

        //trabalhar com positivo e acrescentar sinal de negativo se precisar somente na hora de gravar no arquivo
        if (isNegativo) valorTratado = valorDividaAtraso * (-1);
        else valorTratado = valorDividaAtraso;

        String [] arrayValores = (valorTratado + "").split("\\.");
        String parteInt = arrayValores[0];
        String parteDec = arrayValores[1];

        //tratar parte inteira
        if (!isNegativo) {
            if (parteInt.length() < EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor()) {
                for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor() - parteInt.length()); i++) {
                    newValorDividaAtraso += "0";
                }
                newValorDividaAtraso += parteInt;
            } else {
                newValorDividaAtraso =
                        String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor(), "0"));
            }
        }
        else{
            newValorDividaAtraso = "-";
            if (parteInt.length() < EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor()) {
                for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor() - parteInt.length() - 1); i++) {
                    newValorDividaAtraso += "0";
                }
                newValorDividaAtraso += parteInt;
            } else {
                newValorDividaAtraso =
                        String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_INT.getValor(), "0"));
            }
        }

        //tratar parte decimal
        if (parteDec.length() == EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_DEC.getValor()) {
            newValorDividaAtraso += "," + parteDec;
        } else if (parteDec.length() < EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_DEC.getValor()) {
            newValorDividaAtraso += ",";
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_DEC.getValor() - parteDec.length()); i++) {
                newValorDividaAtraso += "0";
            }
            newValorDividaAtraso += parteDec;
        } else {
            newValorDividaAtraso =
                    String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_DIVIDA_ATRASO_DEC.getValor(), "0"));
        }

        return newValorDividaAtraso;
    }

    private String getQuantPrestAtrasoFormatado(String quantPrestAtraso) {
        String newQuantPrestAtraso = "";

        if ((quantPrestAtraso + "").length() == EnumLayoutSiga.TAM_MAX_QUANT_PREST_ATRASO.getValor()){ newQuantPrestAtraso = quantPrestAtraso; }
        else if ((quantPrestAtraso + "").length() < EnumLayoutSiga.TAM_MAX_QUANT_PREST_ATRASO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_QUANT_PREST_ATRASO.getValor() - (quantPrestAtraso + "").length()); i++){
                newQuantPrestAtraso += "0";
            }
            newQuantPrestAtraso += quantPrestAtraso;
        }
        else {
            for (int i = ((quantPrestAtraso + "").length() - EnumLayoutSiga.TAM_MAX_QUANT_PREST_ATRASO.getValor()); i < (quantPrestAtraso + "").length(); i++){
                newQuantPrestAtraso += quantPrestAtraso.charAt(i);
            }
        }
        return newQuantPrestAtraso;
    }

    private String getDiasAtrasoFormatado(String diasAtraso) {
        String newDiasAtraso = "";

        if ((diasAtraso + "").length() == EnumLayoutSiga.TAM_MAX_DIAS_ATRASO.getValor()){ newDiasAtraso = diasAtraso; }
        else if ((diasAtraso + "").length() < EnumLayoutSiga.TAM_MAX_DIAS_ATRASO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_DIAS_ATRASO.getValor() - (diasAtraso + "").length()); i++){
                newDiasAtraso += "0";
            }
            newDiasAtraso += diasAtraso;
        }
        else {
            for (int i = ((diasAtraso + "").length() - EnumLayoutSiga.TAM_MAX_DIAS_ATRASO.getValor()); i < (diasAtraso + "").length(); i++){
                newDiasAtraso += diasAtraso.charAt(i);
            }
        }
        return newDiasAtraso;
    }

    private String getValorPrestAtrasoFormatado(Double valorUltimaPrestacaoAtraso) {
        String newValorUltimaPrestacaoAtraso = "";
        boolean isNegativo = valorUltimaPrestacaoAtraso < 0;
        Double valorTratado;

        //trabalhar com positivo e acrescentar sinal de negativo se precisar somente na hora de gravar no arquivo
        if (isNegativo) valorTratado = valorUltimaPrestacaoAtraso * (-1);
        else valorTratado = valorUltimaPrestacaoAtraso;

        String [] arrayValores = (valorTratado + "").split("\\.");
        String parteInt = arrayValores[0];
        String parteDec = arrayValores[1];

        //tratar parte inteira
        if (!isNegativo) {
            if (parteInt.length() < EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor()) {
                for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor() - parteInt.length()); i++) {
                    newValorUltimaPrestacaoAtraso += "0";
                }
                newValorUltimaPrestacaoAtraso += parteInt;
            } else {
                    newValorUltimaPrestacaoAtraso =
                            String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor(), "0"));
            }
        }
        else{
            newValorUltimaPrestacaoAtraso = "-";
            if (parteInt.length() < EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor()) {
                for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor() - parteInt.length() - 1); i++) {
                    newValorUltimaPrestacaoAtraso += "0";
                }
                newValorUltimaPrestacaoAtraso += parteInt;
            } else {
                newValorUltimaPrestacaoAtraso =
                        String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_INT.getValor(), "0"));
            }
        }

        //tratar parte decimal
        if (parteDec.length() == EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_DEC.getValor()) {
            newValorUltimaPrestacaoAtraso += "," + parteDec;
        } else if (parteDec.length() < EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_DEC.getValor()) {
            newValorUltimaPrestacaoAtraso += ",";
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_DEC.getValor() - parteDec.length()); i++) {
                newValorUltimaPrestacaoAtraso += "0";
            }
            newValorUltimaPrestacaoAtraso += parteDec;
        } else {
            newValorUltimaPrestacaoAtraso =
                    String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_VALOR_PREST_ATRASO_DEC.getValor(), "0"));
        }

        return newValorUltimaPrestacaoAtraso;
    }

    private Object getDataPrimPrestAbertaFormatada(String dataPrimeiraPrestacaoAberta) {
        String newDataPrimeiraPrestacaoAberta = "      "; //tamanho de 6 espacos em branco
        String mes = "";
        String ano = "";

        if (dataPrimeiraPrestacaoAberta.length() == EnumLayoutSiga.TAM_MAX_DATA_PRIM_PREST_ABERTA.getValor()){
            mes = dataPrimeiraPrestacaoAberta.substring(4, 6);
            ano = dataPrimeiraPrestacaoAberta.substring(0, 4);
            newDataPrimeiraPrestacaoAberta = mes + ano;
        }
        return newDataPrimeiraPrestacaoAberta;
    }

    private String getDiaVencimentoFormatado(int diaVencimento) {
        String newDiaVencimento = "";

        if ((diaVencimento + "").length() == EnumLayoutSiga.TAM_MAX_DIA_VENCIMENTO.getValor()){ newDiaVencimento = diaVencimento + ""; }
        else if ((diaVencimento + "").length() < EnumLayoutSiga.TAM_MAX_DIA_VENCIMENTO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_DIA_VENCIMENTO.getValor() - (diaVencimento + "").length()); i++){
                newDiaVencimento += "0";
            }
            newDiaVencimento += (diaVencimento + "");
        }
        else{
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_DIA_VENCIMENTO.getValor(); i++){
                newDiaVencimento += (diaVencimento + "").charAt(i);
            }
        }
        return newDiaVencimento;
    }

    private String getCodigoFaseFormatado(Map<String, Object> additionalProperties) {
        String codigoFase = additionalProperties.get("codFase").toString();
        String newCodigoFase = "";

        if (codigoFase.length() == EnumLayoutSiga.TAM_MAX_CODIGO_FASE.getValor()){ newCodigoFase = codigoFase; }
        else if (codigoFase.length() < EnumLayoutSiga.TAM_MAX_CODIGO_FASE.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CODIGO_FASE.getValor() - codigoFase.length()); i++){
                newCodigoFase += "0";
            }
            newCodigoFase += codigoFase;
        }
        else{
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_CODIGO_FASE.getValor(); i++){
                newCodigoFase += codigoFase.charAt(i);
            }
        }
        return newCodigoFase;
    }

    private String getUnidadeOperacionalFormatado(Map<String, Object> additionalProperties) {
        String unidadeOperacional = additionalProperties.get("uno").toString();
        String newUnidadeOperacional = "";

        if (unidadeOperacional.length() == EnumLayoutSiga.TAM_MAX_UNIDADE_OPERACIONAL.getValor()){ newUnidadeOperacional = unidadeOperacional; }
        else if (unidadeOperacional.length() < EnumLayoutSiga.TAM_MAX_UNIDADE_OPERACIONAL.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_UNIDADE_OPERACIONAL.getValor() - unidadeOperacional.length()); i++){
                newUnidadeOperacional += "0";
            }
            newUnidadeOperacional += unidadeOperacional;
        }
        else{
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_UNIDADE_OPERACIONAL.getValor(); i++){
                newUnidadeOperacional += unidadeOperacional.charAt(i);
            }
        }
        return newUnidadeOperacional;
    }

    private String getCodigoAcaoCobrancaFormatado(String codigoAcaoCobranca) {
        String newCodigoAcaoCobranca = "";

        if (codigoAcaoCobranca.length() == EnumLayoutSiga.TAM_MAX_CODIGO_ACAO_COBRANCA.getValor()){ newCodigoAcaoCobranca = codigoAcaoCobranca; }
        else if (codigoAcaoCobranca.length() < EnumLayoutSiga.TAM_MAX_CODIGO_ACAO_COBRANCA.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_CODIGO_ACAO_COBRANCA.getValor() - codigoAcaoCobranca.length()); i++){
                newCodigoAcaoCobranca += "0";
            }
            newCodigoAcaoCobranca += codigoAcaoCobranca;
        }
        else{ newCodigoAcaoCobranca = String.join("", Collections.nCopies(EnumLayoutSiga.TAM_MAX_CODIGO_ACAO_COBRANCA.getValor(), "0")); }

        return newCodigoAcaoCobranca;
    }

    private void gravarAcaoCobrancaTxt(FileWriter arq, List<AcaoCobranca> acoesCobranca, Long numeroContrato) {
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf("%n%s", "A");
        gravarArq.printf("%s", getNumeroContratoFormatado(numeroContrato));

        //se tiver acoes de cobranca...
        if (acoesCobranca.size() > 0) {
            for (AcaoCobranca acaoCobranca : acoesCobranca) {
                gravarArq.printf("%s", getCodigoAcaoCobrancaFormatado(acaoCobranca.getCodigoAcaoCobranca() + ""));
            }
        }

        //preenchendo com zeros o número de acoes inexistentes
        for (int i = 0; i < (EnumLayoutSiga.QUANT_MAX_CODIGOS_ACAO_COBRANCA.getValor() - acoesCobranca.size()); i++){
            gravarArq.printf("%s", getCodigoAcaoCobrancaFormatado("0"));
        }

    }

    private void gravarCoobrigadoTxt(FileWriter arq, List<Coobrigado> listCoobrigado, Long numeroContrato){
        PrintWriter gravarArq = new PrintWriter(arq);

        for (int i = 0; i < listCoobrigado.size(); i++){
            gravarArq.printf("%n%s", "C");  //gravando tipo registro
            gravarArq.printf("%s", getNumeroContratoFormatado(numeroContrato));
            gravarArq.printf("%s", getNomeFormatado(listCoobrigado.get(i).getNome()));
            gravarArq.printf("%s", getCpfFormatado(listCoobrigado.get(i).getCpf()));
            gravarArq.printf("%s", getDDDFormatado(listCoobrigado.get(i).getDddResidencia()));
            gravarArq.printf("%s", getTelFormatado(listCoobrigado.get(i).getTelResidencia()));
            gravarArq.printf("%s", getDDDFormatado(listCoobrigado.get(i).getDddComercial()));
            gravarArq.printf("%s", getTelFormatado(listCoobrigado.get(i).getTelComercial()));
            gravarArq.printf("%s", getDDDFormatado(listCoobrigado.get(i).getDddCelular()));
            gravarArq.printf("%s", getTelFormatado(listCoobrigado.get(i).getTelCelular()));
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
        for (int i = 0; i < (EnumLayoutSiga.QUANT_MAX_SES.getValor() - listSituacaoEspecial.size()); i++){
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

        //acao cobranca (iniciando vazio)
        contratoJsonElement.getAsJsonObject().add("acoesCobranca", new JsonArray());

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

        //email alternativo - não vem da alta - dado do sigma - inicia vazio na leitura do arquivo da alta
        newJsonMutuario.getAsJsonObject().addProperty("emailAlternativo", "");
        //ddd alternativo - não vem da alta - dado do sigma - inicia vazio na leitura do arquivo da alta
        newJsonMutuario.getAsJsonObject().addProperty("dddAlternativo", "");
        //telefone alternativo - não vem da alta - dado do sigma - inicia vazio na leitura do arquivo da alta
        newJsonMutuario.getAsJsonObject().addProperty("telAlternativo", "");

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

        //tratando valorGarantiaAtualizada
        partesNumber = newContratoJsonElement.getAsJsonObject().getAsJsonPrimitive("garAtu").toString().split(",");
        parteInteira = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[0])));
        parteDecimal = Integer.parseInt(removeAspas(removeEspacosBrancos(partesNumber[1])));
        newJsonSituacaoContrato.getAsJsonObject().addProperty("garAtu", Double.parseDouble(parteInteira + "." + parteDecimal));

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

        if ((numeroContrato + "").length() < EnumLayoutSiga.TAM_NUMERO_CONTRATO.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_NUMERO_CONTRATO.getValor() - (numeroContrato + "").length()); i++){
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

        if ((situacaoEspecial + "").length() < EnumLayoutSiga.TAM_SES.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_SES.getValor() - (situacaoEspecial + "").length()); i++){
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

        if (nome.length() == EnumLayoutSiga.TAM_MAX_NOME.getValor()){
            newNome = nome;
        }
        else if (nome.length() > EnumLayoutSiga.TAM_MAX_NOME.getValor()){
            newNome = nome.substring(0, EnumLayoutSiga.TAM_MAX_NOME.getValor());
        }
        else {
            newNome = nome;
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_NOME.getValor() - nome.length()); i++){
                newNome += " ";
            }
        }

        return newNome;
    }

    private String getCpfFormatado(String cpf){
        String newCpf = "";

        if (cpf.length() == EnumLayoutSiga.TAM_CPF.getValor()){
            newCpf = cpf;
        }
        else if (cpf.length() < EnumLayoutSiga.TAM_CPF.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_CPF.getValor() - cpf.length()); i++){
                newCpf += "0";
            }
            newCpf += cpf;
        }

        return newCpf;
    }

    private String getDDDFormatado(String ddd){
        String newDdd = "";

        if (ddd.length() == EnumLayoutSiga.TAM_MAX_DDD.getValor()){
            newDdd = ddd;
        }
        else if (ddd.length() < EnumLayoutSiga.TAM_MAX_DDD.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_DDD.getValor() - ddd.length()); i++){ newDdd += "0"; }
            newDdd += ddd;
        }
        else{
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_DDD.getValor(); i++){ newDdd += ddd.charAt(i); }
        }
        return newDdd;
    }

    private String getTelFormatado(String tel){
        String newTel = "";

        if (tel.length() == EnumLayoutSiga.TAM_MAX_TEL.getValor()){
            newTel = tel;
        }
        else if (tel.length() < EnumLayoutSiga.TAM_MAX_TEL.getValor()){
            for (int i = 0; i < (EnumLayoutSiga.TAM_MAX_TEL.getValor() - tel.length()); i++){ newTel += "0"; }
            newTel += tel;
        }
        else{
            for (int i = 0; i < EnumLayoutSiga.TAM_MAX_TEL.getValor(); i++){ newTel += tel.charAt(i); }
        }
        return newTel;
    }
}
