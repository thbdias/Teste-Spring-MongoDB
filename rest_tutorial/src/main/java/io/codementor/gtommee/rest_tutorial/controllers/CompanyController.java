package io.codementor.gtommee.rest_tutorial.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.codementor.gtommee.rest_tutorial.models.Company;
import io.codementor.gtommee.rest_tutorial.models.Contact;
import io.codementor.gtommee.rest_tutorial.models.Product;
import io.codementor.gtommee.rest_tutorial.repositories.CompanyRepository;
import org.bson.types.ObjectId;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
import com.google.gson.JsonParser;
//import org.json.JSONArray;
//import org.json.simple.parser.ParseException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Company> getAllCompanys() {
        return repository.findAll();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Company getPetById(@PathVariable("id") ObjectId id) {
//        return repository.findBy_id(id);
//    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Company pets) {
//        pets.set_id(id);
//        repository.save(pets);
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Company createCompany(@Valid @RequestBody Company company) {
        company.set_id(ObjectId.get());
        repository.save(company);
        return company;
    }

    @RequestMapping(value = "/array", method = RequestMethod.POST)
    public List<Company> createCompany(@Valid @RequestBody List<Company> companys) {
        companys.forEach(company -> {
            company.set_id(ObjectId.get());
            repository.save(company);
        });

        return companys;
    }

    @RequestMapping(value = "/object_from_json", method = RequestMethod.GET)
    public String createCompany() {

//        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\companys_2.json"))
        {
            JsonElement jsonElement = JsonParser.parseReader(reader);
//            System.out.println("jsonElement: " + jsonElement);
            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//            System.out.println(obj);
//            JSONObject jsonObject = new JSONObject (obj);
//                        System.out.println("jsonObject: " + jsonObject);
            JsonArray companys = jsonElement.getAsJsonArray();
//            System.out.println("\n");
//            System.out.println(companys);
//            System.out.println("\n");
//
            companys.forEach(item -> {
//                repository.save(parseCompanyObject(item.getAsJsonObject()));
                repository.save(parseCompanyObject(item.getAsJsonObject()));
////                System.out.println(company);
            });

//            List<Company> companys = (List<Company>) employeeList;

//            companys.forEach(company -> {
//                company.set_id(ObjectId.get());
//                repository.save(company);
//            });

            return "Json gravado no MongoDB";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return "erro 3";
        }
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deletePet(@PathVariable ObjectId id) {
//        repository.delete(repository.findBy_id(id));
//    }


//    @RequestMapping(value = "/write_json", method = RequestMethod.GET)
//    public String writeJson() {
//        //First Employee
//        JSONObject employeeDetails = new JSONObject();
//        employeeDetails.put("firstName", "Lokesh");
//        employeeDetails.put("lastName", "Gupta");
//        employeeDetails.put("website", "howtodoinjava.com");
//
//        JSONObject employeeObject = new JSONObject();
//        employeeObject.put("employee", employeeDetails);
//
//        //Second Employee
//        JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
//        employeeDetails2.put("lastName", "Schultz");
//        employeeDetails2.put("website", "example.com");
//
//        JSONObject employeeObject2 = new JSONObject();
//        employeeObject2.put("employee", employeeDetails2);
//
//        //Add employees to list
//        JSONArray employeeList = new JSONArray();
//        employeeList.add(employeeObject);
//        employeeList.add(employeeObject2);
//
//        //Write JSON file
//        try (FileWriter file = new FileWriter("C:\\Users\\balbinth\\Documents\\employees.json")) {
//
//            file.write(employeeList.toJSONString());
//            file.flush();
//            return "sucesso";
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "erro";
//        }
//    }


//    @RequestMapping(value = "/read_json", method = RequestMethod.GET)
//    public String readJson() {
//        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//
////        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\employees.json"))
//        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\companys.json"))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray employeeList = (JSONArray) obj;
//            System.out.println(employeeList);
//
//            //Iterate over employee array
////            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
//
//            return "lido";
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return "erro 1";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "erro 2";
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return "erro 3";
//        }
//    }
//
    private Company parseCompanyObject(JsonObject companyJson)
    {
        System.out.println("\n:>>" + companyJson);
//        System.out.println(companyJson.get((Object) "name"));
//        System.out.println("\n");

        //criando contato
        Contact contact = new Contact();
        JsonObject contactJson = companyJson.getAsJsonObject("contact");
        contact.setAddress(contactJson.getAsJsonPrimitive("address").toString());
        contact.setPhone(contactJson.getAsJsonPrimitive("phone").toString());
//        System.out.println("end: " + contact.getAddress() + " --- phone: " + contact.getPhone() + " --- class: " + contact.getClass()); //TRATAR AQUI

        //criando produtos
        List<Product> productList = new LinkedList<>();
        JsonArray productsJsonArray = companyJson.getAsJsonArray("products");
        productsJsonArray.forEach(productItemJsonfromArray -> {
            Product product = new Product();
            JsonObject jsonObjectItem = productItemJsonfromArray.getAsJsonObject();
            product.setCode(jsonObjectItem.getAsJsonPrimitive("code").toString());
            product.setName(jsonObjectItem.getAsJsonPrimitive("name").toString());
            product.setDetails(jsonObjectItem.getAsJsonPrimitive("details").toString());
            productList.add(product);
        });
//        System.out.println(productList.get(0).getCode() + "----");
//        System.out.println(productList.get(1).getCode() + "++++");
//        System.out.println("---------------");
//
        Company company = new Company();
        company.set_id(ObjectId.get());
        company.setName(companyJson.getAsJsonPrimitive("name").toString());
//        System.out.println(company);
        company.setContact(contact);
//        System.out.println("\n" + company);
        company.setProducts(productList);

//        System.out.println("company id: " + company.get_id());

        return company;
//        System.out.println("------------------\nresultado: ");
//        System.out.println("company id: " + company.get_id());
//        System.out.println("company name: " + company.getName());
//        System.out.println("company-contact phone: " + company.getContact().getPhone());
//        System.out.println("company-product 1 name: " + company.getProducts().get(0).getName());
//        System.out.println("company-product 2 name: " + company.getProducts().get(1).getName());
//        System.out.println("------------------\n");

        //Get employee object within list
//        JSONObject employeeObject = (JSONObject) employee.get("employee");
//
//        //Get employee first name
//        String firstName = (String) employeeObject.get("firstName");
//        System.out.println(firstName);
//
//        //Get employee last name
//        String lastName = (String) employeeObject.get("lastName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        String website = (String) employeeObject.get("website");
//        System.out.println(website);
    }

}
