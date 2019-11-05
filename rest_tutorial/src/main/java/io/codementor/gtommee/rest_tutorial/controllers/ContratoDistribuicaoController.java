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
//            JsonObject controtoObject = jsonElement.getAsJsonObject();

            contratoArray.forEach(itemContratoArray -> {
                try {
                    contratoDistribuicaoRepository.save(parseContratoObject(itemContratoArray.getAsJsonObject()));
    //                contratoDistribuicaoRepository.save(parseContratoObject2(controtoObject));
    //                teste(controtoObject);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    //teste para retirar elemento do json
    private void teste(JsonObject contratoJson) throws IOException
    {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++inicio");
        JsonObject clone = contratoJson.deepCopy();
        clone.remove("contrato");
        System.out.println("original              : >>>>");
        System.out.println(contratoJson.toString());
        System.out.println("clone sem contrato    : >>>>");
        System.out.println(clone.toString());
        clone.remove("nome");
        System.out.println("clone sem nome        : >>>>");
        System.out.println(clone.toString());
        System.out.println("+++++++++++++++++++++++++++++++++++++fim");
    }

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


}
