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
    private static final int TAM_NUMERO_CONTRATO = 12; //tamanho máximo de caracteres de um contrato
    private static final int TAM_SES = 3; //tamanho máximo de caracteres de uma situacao especial
    private static final int QUANT_MAX_SES = 10; //quantidade máxima de situações especiais que devem conter no layout SIGA
    private static final int QUANT_MAX_NOME_COOBRIGADO = 36; //quantidade máxima de situações especiais que devem conter no layout SIGA
    private static final int TAM_CPF_COOBRIGADO = 14; //tamanho máximo de caracteres do cpf de um coobrigado que deve conter no layout SIGA

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
            gravarArq.printf("%s%n", formatter.format(date)); //gravando data ok
            //dados do contrato
            gravarArq.printf("%d %s", tipoInfo++,
                    contratoDistribuicaoModel.getContrato().getNumeroContrato() + " " + //num contrato
                    contratoDistribuicaoModel.getContrato().getCodigoCredor() + " " +   //cod credor
                    contratoDistribuicaoModel.getContrato().getCodigoAdminitrador()     //cod admin
                    );
            //coobrigados
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
            gravarArq.printf("%s", getNomeCoobrigadoFormatado(listCoobrigado.get(i).getNome())); //ok
            gravarArq.printf("%s ", getCpfCoobrigadoFormatado(listCoobrigado.get(i).getCpf())); //ok
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

    private String getNomeCoobrigadoFormatado(String nomeCoobrigado){
        String newNome = "";

        if (nomeCoobrigado.length() == QUANT_MAX_NOME_COOBRIGADO){
            newNome = nomeCoobrigado;
        }
        else if (nomeCoobrigado.length() > QUANT_MAX_NOME_COOBRIGADO){
            newNome = nomeCoobrigado.substring(0, QUANT_MAX_NOME_COOBRIGADO);
        }
        else {
            newNome = nomeCoobrigado;
            for (int i = 0; i < (QUANT_MAX_NOME_COOBRIGADO - nomeCoobrigado.length()); i++){
                newNome += " ";
            }
        }

        return newNome;
    }

    private String getCpfCoobrigadoFormatado(String cpfCoobrigado){
        String newCpf = "";

        if (cpfCoobrigado.length() == TAM_CPF_COOBRIGADO){
            newCpf = cpfCoobrigado;
        }
        else if (cpfCoobrigado.length() < TAM_CPF_COOBRIGADO){
            for (int i = 0; i < (TAM_CPF_COOBRIGADO - cpfCoobrigado.length()); i++){
                newCpf += "0";
            }
            newCpf += cpfCoobrigado;
        }

        return newCpf;
    }
}
