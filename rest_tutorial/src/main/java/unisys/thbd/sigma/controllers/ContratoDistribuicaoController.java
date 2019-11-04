package unisys.thbd.sigma.controllers;

import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bson.types.ObjectId;
import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unisys.thbd.sigma.repositories.ContratoDistribuicaoRepository;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
            System.out.println("jsonElement: " + jsonElement);

            JsonArray companys = jsonElement.getAsJsonArray();
//            System.out.println("\n");
//            System.out.println(companys);
//            System.out.println("\n");

//            companys.forEach(item -> {
//                contratoDistribuicaoRepository.save(parseCompanyObject(item.getAsJsonObject()));
//////                System.out.println(company);
//            });
//            return "Json gravado no MongoDB";
            return "+ teste object from json +";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
        }
    }

//    private Company parseCompanyObject(JsonObject companyJson)
//    {
//        System.out.println("\n:>>" + companyJson);
//
//        //criando contato
//        Contact contact = new Contact();
//        JsonObject contactJson = companyJson.getAsJsonObject("contact");
//        contact.setAddress(contactJson.getAsJsonPrimitive("address").toString());
//        contact.setPhone(contactJson.getAsJsonPrimitive("phone").toString());
////        System.out.println("end: " + contact.getAddress() + " --- phone: " + contact.getPhone() + " --- class: " + contact.getClass()); //TRATAR AQUI
//
//        //criando produtos
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
////        System.out.println(productList.get(0).getCode() + "----");
////        System.out.println(productList.get(1).getCode() + "++++");
////        System.out.println("---------------");
////
//        Company company = new Company();
//        company.set_id(ObjectId.get());
//        company.setName(companyJson.getAsJsonPrimitive("name").toString());
////        System.out.println(company);
//        company.setContact(contact);
////        System.out.println("\n" + company);
//        company.setProducts(productList);
//
////        System.out.println("company id: " + company.get_id());
//
//        return company;
////        System.out.println("------------------\nresultado: ");
////        System.out.println("company id: " + company.get_id());
////        System.out.println("company name: " + company.getName());
////        System.out.println("company-contact phone: " + company.getContact().getPhone());
////        System.out.println("company-product 1 name: " + company.getProducts().get(0).getName());
////        System.out.println("company-product 2 name: " + company.getProducts().get(1).getName());
////        System.out.println("------------------\n");
//
//    }

}
