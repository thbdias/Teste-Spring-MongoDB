package io.codementor.gtommee.rest_tutorial.controllers;

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

        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\cont1.json"))
        {
            JsonElement jsonElement = JsonParser.parseReader(reader);
//            System.out.println("****************************INICIO ARQUIVO************************************");
//            System.out.println("jsonElement: " + jsonElement);
//            System.out.println("jsonElement: " + jsonElement);
//            System.out.println("*****************************FIM ARQUIVO***********************************");

//            JsonArray companys = jsonElement.getAsJsonArray();
//            System.out.println("\n");
//            System.out.println(companys);
//            System.out.println("\n");

//            companys.forEach(item -> {
//                contratoDistribuicaoRepository.save(parseCompanyObject(item.getAsJsonObject()));
//////                System.out.println(company);
//            });
//            return "Json gravado no MongoDB";

            JsonObject controtoObject = jsonElement.getAsJsonObject();
//            System.out.println("\n JSON OBJECT LIDO");
//            System.out.println(companys);
//            System.out.println("\n FIM JSON OBJECT LIDO");

            parseContratoObject(controtoObject);

            return "+ teste object from json +";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
        }
    }

    private void parseContratoObject(JsonObject contratoJson)
    {
//        System.out.println("\n:>>" + companyJson);

//        Map<String, Object> additionalProperties = null;

        ContratoCobrancaTerceirizada contratoCobrancaTerceirizada = new ContratoCobrancaTerceirizada();
        contratoCobrancaTerceirizada.setNumeroContrato(contratoJson.getAsJsonPrimitive("contrato").toString());
        contratoCobrancaTerceirizada.setDiasAtraso(contratoJson.getAsJsonPrimitive("diasAtraso").getAsInt());
        contratoCobrancaTerceirizada.setValorDivida(contratoJson.getAsJsonPrimitive("vlrDiv").toString());
        contratoCobrancaTerceirizada.setValorDividaAtualizada(contratoJson.getAsJsonPrimitive("garAtu").toString());

        Integer situacao = null;

        Convenio convenio = new Convenio();
        convenio.setNumeroContrato(contratoJson.getAsJsonPrimitive("contrato").toString());

        ClienteCobrancaTerceirizada clienteCobrancaTerceirizada = new ClienteCobrancaTerceirizada();
        clienteCobrancaTerceirizada.setNome(contratoJson.getAsJsonPrimitive("nome").toString());
        clienteCobrancaTerceirizada.setCredor(contratoJson.getAsJsonPrimitive("credor").getAsInt());
        clienteCobrancaTerceirizada.setAdministrador(contratoJson.getAsJsonPrimitive("adm").getAsInt());

        Date dataDistribuicao =  null;
        List<ContratoDistribuicaoRegra> contratoDistribuicaoRegras =  null;
        List<Pagamento> pagamentos =  null;
        Date dataTerminoPermancencia =  null;
        Date dataEncerramento =  null;
        Date dataRemessaEncerramento =  null;
        String usuarioResponsavel =  null;
        Integer motivoEncerramento =  null;
        Date dataIncorporacao =  null;
        Date dataApuracao =  null;
        String descricaoEncerramento =  null;
        List<Integer> filtroSituacao =  null;
        Date dataIncio =  null;
        Date dataFim =  null;
        Map<TipoRegraEnum, List<ContratoDistribuicaoRegra>> mapaContratoDistribuicaoRegraPorTipo = null;

        ContratoDistribuicaoModel contratoDistribuicaoModel =
                new ContratoDistribuicaoModel(
                                                new ObjectId(),
//                                                additionalProperties,
                                                contratoCobrancaTerceirizada,
                                                situacao,
                                                convenio,
                                                clienteCobrancaTerceirizada,
                                                dataDistribuicao,
                                                contratoDistribuicaoRegras,
                                                pagamentos,
                                                dataTerminoPermancencia,
                                                dataEncerramento,
                                                dataRemessaEncerramento,
                                                usuarioResponsavel,
                                                motivoEncerramento,
                                                dataIncorporacao,
                                                dataApuracao,
                                                descricaoEncerramento,
                                                filtroSituacao,
                                                dataIncio,
                                                dataFim,
                                                mapaContratoDistribuicaoRegraPorTipo
                                            ) {
            @Override
            public Serializable getId() {
                return null;
            }
        };

        //teste
        contratoDistribuicaoRepository.save(contratoDistribuicaoModel);
        imprimir(contratoDistribuicaoModel);

        //criando contato
//        Contact contact = new Contact();
//        JsonObject contactJson = companyJson.getAsJsonObject("contact");
//        contact.setAddress(contactJson.getAsJsonPrimitive("address").toString());
//        contact.setPhone(contactJson.getAsJsonPrimitive("phone").toString());
//        System.out.println("end: " + contact.getAddress() + " --- phone: " + contact.getPhone() + " --- class: " + contact.getClass()); //TRATAR AQUI

        //criando produtos
//        List<Product> productList = new LinkedList<>();
//        JsonArray productsJsonArray = companyJson.getAsJsonArray("products");
//        productsJsonArray.forEach(productItemJsonfromArray -> {
//            Product product = new Product();
//            JsonObject jsonObjectItem = productItemJsonfromArray.getAsJsonObject();
//            product.setCode(jsonObjectItem.getAsJsonPrimitive("code").toString());
//            product.setName(jsonObjectItem.getAsJsonPrimitive("name").toString());
//            product.setDetails(jsonObjectItem.getAsJsonPrimitive("details").toString());
//            productList.add(product);
//        });
//        System.out.println(productList.get(0).getCode() + "----");
//        System.out.println(productList.get(1).getCode() + "++++");
//        System.out.println("---------------");
//
//        Company company = new Company();
//        company.set_id(ObjectId.get());
//        company.setName(companyJson.getAsJsonPrimitive("name").toString());
//        System.out.println(company);
//        company.setContact(contact);
//        System.out.println("\n" + company);
//        company.setProducts(productList);

//        System.out.println("company id: " + company.get_id());

//        return company;
//        System.out.println("------------------\nresultado: ");
//        System.out.println("company id: " + company.get_id());
//        System.out.println("company name: " + company.getName());
//        System.out.println("company-contact phone: " + company.getContact().getPhone());
//        System.out.println("company-product 1 name: " + company.getProducts().get(0).getName());
//        System.out.println("company-product 2 name: " + company.getProducts().get(1).getName());
//        System.out.println("------------------\n");
    }

    private void imprimir(ContratoDistribuicaoModel contratoDistribuicaoModel){
        System.out.println("\ncontrato salvo");
        System.out.println("\ninicio contrato");
        System.out.println("id => " + contratoDistribuicaoModel.get_id());
        System.out.println("convenio => " + contratoDistribuicaoModel.getConvenio());
        System.out.println("contratoDistribuicaoRegras => " + contratoDistribuicaoModel.getContratoDistribuicaoRegras());
        System.out.println("fim contrato");
    }

}
