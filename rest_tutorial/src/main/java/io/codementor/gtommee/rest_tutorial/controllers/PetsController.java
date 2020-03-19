package io.codementor.gtommee.rest_tutorial.controllers;

import io.codementor.gtommee.rest_tutorial.service.MeuService;
import io.codementor.gtommee.rest_tutorial.service.ServiceTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetsController {
	
	
	@Autowired 
	private MeuService meuService;

    
    @GetMapping(value = "/t1")
    public String t1(){    	
    	System.out.println("Antes.");
        meuService.t1();
        System.out.println("Depois.");
        return "ok";
    }        

}
