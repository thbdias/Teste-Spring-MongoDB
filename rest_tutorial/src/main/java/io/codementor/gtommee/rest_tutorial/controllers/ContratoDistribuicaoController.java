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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contrato_distribuicao")
public class ContratoDistribuicaoController {
    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

    @RequestMapping(value = "/object_from_json", method = RequestMethod.GET)
    public String createCompany() {
        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\cont3.json"))
        {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray contratoArray = jsonElement.getAsJsonArray();

            contratoArray.forEach(itemContratoArray -> {
//                try {
//                    contratoDistribuicaoRepository.save(
//                            parseContratoObject(
                                    refatorarContratoJson(itemContratoArray);
//                            )
//                    );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            });

            return "+ teste object from json +";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
        }
    }

    private ContratoDistribuicaoModel parseContratoObject2(JsonObject contratoJson) throws IOException
    {
        String companyJsonString = contratoJson.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        ContratoDistribuicaoModel contratoDistribuicaoModel = objectMapper.readValue(companyJsonString, ContratoDistribuicaoModel.class);
        return contratoDistribuicaoModel;
    }

    /**tratar aqui*/
    private ContratoDistribuicaoModel parseContratoObject(JsonObject contratoJson) throws IOException
    {
        //clone com atributos restantes que serão gravados no contrato atribuicao
        JsonObject clone = contratoJson.deepCopy();

        ContratoCobrancaTerceirizada contratoCobrancaTerceirizada = new ContratoCobrancaTerceirizada();
        contratoCobrancaTerceirizada.setNumeroContrato(contratoJson.getAsJsonPrimitive("contrato").toString());
        clone.remove("contrato");
        contratoCobrancaTerceirizada.setDiasAtraso(contratoJson.getAsJsonPrimitive("diasAtraso").getAsInt());
        clone.remove("diasAtraso");
        contratoCobrancaTerceirizada.setValorDivida(contratoJson.getAsJsonPrimitive("vlrDiv").toString());
        clone.remove("vlrDiv");
        contratoCobrancaTerceirizada.setValorDividaAtualizada(contratoJson.getAsJsonPrimitive("garAtu").toString());
        clone.remove("garAtu");

        Convenio convenio = new Convenio();
        convenio.setNumeroContrato(contratoJson.getAsJsonPrimitive("contrato").toString());
        clone.remove("contrato");

        ClienteCobrancaTerceirizada clienteCobrancaTerceirizada = new ClienteCobrancaTerceirizada();
        clienteCobrancaTerceirizada.setNome(contratoJson.getAsJsonPrimitive("nome").toString());
        clone.remove("nome");
        clienteCobrancaTerceirizada.setCredor(contratoJson.getAsJsonPrimitive("credor").getAsInt());
        clone.remove("credor");
        clienteCobrancaTerceirizada.setAdministrador(contratoJson.getAsJsonPrimitive("adm").getAsInt());
        clone.remove("adm");

        //colocando no contrato distribuição os atributos restantes que não foram para nenhuma instância
        String companyJsonString = clone.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        ContratoDistribuicaoModel contratoDistribuicaoModel = objectMapper.readValue(companyJsonString, ContratoDistribuicaoModel.class);

        contratoDistribuicaoModel.setContratoCobrancaTerceirizada(contratoCobrancaTerceirizada);
        contratoDistribuicaoModel.setConvenio(convenio);
        contratoDistribuicaoModel.setClienteCobrancaTerceirizada(clienteCobrancaTerceirizada);

        return contratoDistribuicaoModel;
    }


    /**
     * function que refatora o contrato vindo da alta em formato json
     * */
    private JsonObject refatorarContratoJson(JsonElement contratoJsonElement){
        JsonObject refatoredContrato = new JsonObject();
        JsonObject newContratoCopy = contratoJsonElement.getAsJsonObject().deepCopy();

        //ses
        System.out.println("\ncontrato xxxx");
        JsonArray arraySituacaoEspecial = contratoJsonElement.getAsJsonObject().getAsJsonArray("ses");
        JsonArray newArraySituacaoEspecial = arraySituacaoEspecial.deepCopy();
        arraySituacaoEspecial.forEach(itemSes -> {


//            if (!(isCodigoSesValido(itemSes))){
                isCodigoSesValido(itemSes);
//                newArraySituacaoEspecial.remove(itemSes);
//            }


        });

//        JsonArray arrayCoobrigados = contratoJsonElement.getAsJsonObject().getAsJsonArray("coobrigados");

        refatoredContrato.add("contrato", contratoJsonElement);

//        System.out.println("\n\n>>contratoJsonElement>> : " + contratoJsonElement);

        return refatoredContrato;
    }


//    private boolean isCodigoSesValido(JsonElement jsonElement){
    private boolean isCodigoSesValido(JsonElement jsonElement){
        boolean resp = false;

        if ((jsonElement.isJsonObject())) {
            String codigo = jsonElement.getAsJsonObject().get("codigo").getAsString();
            Integer codNumber = Integer.parseInt(codigo);
            resp = (codNumber > 0) ? true : false;
        }

        return resp;
    }

}
