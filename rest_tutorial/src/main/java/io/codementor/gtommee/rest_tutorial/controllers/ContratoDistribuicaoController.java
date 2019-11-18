package io.codementor.gtommee.rest_tutorial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.codementor.gtommee.rest_tutorial.models.*;
import io.codementor.gtommee.rest_tutorial.repositories.ContratoDistribuicaoRepository;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        //numeracao que aparecera no inicio de cada linha de cada contrato
        int tipoInfo = 0;

        for (ContratoDistribuicaoModel contratoDistribuicaoModel: listContratoDistribuicaoModel){
            tipoInfo = 0;
            gravarArq.printf("%d %s%n", tipoInfo++, formatter.format(date));
            //dados do contrato
            gravarArq.printf("%d %s", tipoInfo++,
                    contratoDistribuicaoModel.getContrato().getNumeroContrato() + " " + //num contrato
                    contratoDistribuicaoModel.getContrato().getCodigoCredor() + " " +   //cod credor
                    contratoDistribuicaoModel.getContrato().getCodigoAdminitrador()     //cod admin
                    );
            //coobrigados
            gravarCoobrigadoTxt(arq, contratoDistribuicaoModel.getContrato().getCoobrigados(), tipoInfo++, contratoDistribuicaoModel.getContrato().getNumeroContrato());
            //ses
            gravarSituacaoEspecialTxt(arq, contratoDistribuicaoModel.getContrato().getSituacoesEspeciais(), tipoInfo++, contratoDistribuicaoModel.getContrato().getNumeroContrato());
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

    private void gravarCoobrigadoTxt(FileWriter arq, List<Coobrigado> listCoobrigado, int tipoInfo, Long numeroContrato){
        PrintWriter gravarArq = new PrintWriter(arq);
        Coobrigado coobrigado;

        for (int i = 0; i < listCoobrigado.size(); i++){
            gravarArq.printf("%n%d ", tipoInfo);
            gravarArq.printf("%d ",numeroContrato);
            coobrigado = new Coobrigado();
            coobrigado = listCoobrigado.get(i);
            if ( i < listCoobrigado.size() -1){
                gravarArq.printf("%d ", coobrigado.getTipoPessoa());
                gravarArq.printf("\"%s\" ", coobrigado.getNome());
                gravarArq.printf("%s ", coobrigado.getCpf());
                gravarArq.printf("%s ", coobrigado.getDddResidencia());
                gravarArq.printf("%s ", coobrigado.getTelResidencia());
                gravarArq.printf("%s ", coobrigado.getDddCelular());
                gravarArq.printf("%s ", coobrigado.getTelCelular());
                gravarArq.printf("%s ", coobrigado.getDddComercial());
                gravarArq.printf("%s ", coobrigado.getTelComercial());
                gravarArq.printf("%s", coobrigado.getRamalComercial());
            }
            else{
                gravarArq.printf("%d ", coobrigado.getTipoPessoa());
                gravarArq.printf("\"%s\" ", coobrigado.getNome());
                gravarArq.printf("%s ", coobrigado.getCpf());
                gravarArq.printf("%s ", coobrigado.getDddResidencia());
                gravarArq.printf("%s ", coobrigado.getTelResidencia());
                gravarArq.printf("%s ", coobrigado.getDddCelular());
                gravarArq.printf("%s ", coobrigado.getTelCelular());
                gravarArq.printf("%s ", coobrigado.getDddComercial());
                gravarArq.printf("%s ", coobrigado.getTelComercial());
                gravarArq.printf("%s", coobrigado.getRamalComercial());
            }
        }
    }

    private void gravarSituacaoEspecialTxt(FileWriter arq, List<SituacaoEspecial> listSituacaoEspecial, int tipoInfo, Long numeroContrato){
        PrintWriter gravarArq = new PrintWriter(arq);
        SituacaoEspecial situacaoEspecial;
        for (int i = 0; i < listSituacaoEspecial.size(); i++){
            gravarArq.printf("%n%d ", tipoInfo);
            gravarArq.printf("%d ", numeroContrato);
            situacaoEspecial = new SituacaoEspecial();
            situacaoEspecial = listSituacaoEspecial.get(i);
            if ( i < listSituacaoEspecial.size() -1){
                gravarArq.printf("%d, ", situacaoEspecial.getCodigoSituacaoEspecial());
            }
            else{
                gravarArq.printf("%d", situacaoEspecial.getCodigoSituacaoEspecial());
            }
        }
    }

    private ContratoDistribuicaoModel parseContratoObject2(JsonObject contratoJson) throws IOException
    {
        String companyJsonString = contratoJson.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        ContratoDistribuicaoModel contratoDistribuicaoModel = objectMapper.readValue(companyJsonString, ContratoDistribuicaoModel.class);
        return contratoDistribuicaoModel;
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


}
