package io.codementor.gtommee.rest_tutorial.controllers;

import io.codementor.gtommee.rest_tutorial.models.Pets;
import io.codementor.gtommee.rest_tutorial.repositories.PetsRepository;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class CompanyController {
    @Autowired
    private PetsRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pets> getAllPets() {
        return repository.findAll();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Pets getPetById(@PathVariable("id") ObjectId id) {
//        return repository.findBy_id(id);
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Pets pets) {
        pets.set_id(id);
        repository.save(pets);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pets createPet(@Valid @RequestBody Pets pets) {
        pets.set_id(ObjectId.get());
        repository.save(pets);
        return pets;
    }

    @RequestMapping(value = "/array", method = RequestMethod.POST)
    public List<Pets> createPet(@Valid @RequestBody List<Pets> pets) {
        pets.forEach(pet -> {
            pet.set_id(ObjectId.get());
            repository.save(pet);
        });

        return pets;
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deletePet(@PathVariable ObjectId id) {
//        repository.delete(repository.findBy_id(id));
//    }


    @RequestMapping(value = "/write_json", method = RequestMethod.GET)
    public String writeJson() {
        //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);

        //Write JSON file
        try (FileWriter file = new FileWriter("C:\\Users\\balbinth\\Documents\\employees.json")) {

            file.write(employeeList.toJSONString());
            file.flush();
            return "sucesso";

        } catch (IOException e) {
            e.printStackTrace();
            return "erro";
        }
    }


    @RequestMapping(value = "/read_json", method = RequestMethod.GET)
    public String readJson() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\employees.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );

            return "lido";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "erro 1";
        } catch (IOException e) {
            e.printStackTrace();
            return "erro 2";
        } catch (ParseException e) {
            e.printStackTrace();
            return "erro 3";
        }
    }

    private void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String website = (String) employeeObject.get("website");
        System.out.println(website);
    }

}
