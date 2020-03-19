package io.codementor.gtommee.rest_tutorial.controllers;

import io.codementor.gtommee.rest_tutorial.service.ServiceTest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetsController {
	
	
	ServiceTest serviceTest = new ServiceTest();
    
    @GetMapping(value = "/testeAsync")
    public String testeAsync(){    	
    	serviceTest.testeAsync();
    	return "";
    }

    
    @GetMapping(value = "/t1")
    public String t1(){    	
    	serviceTest.t1();
    	return "";
    }
    
    @GetMapping(value = "/asyncMethodWithVoidReturnType")
    public String asyncMethodWithVoidReturnType(){    	
    	serviceTest.asyncMethodWithVoidReturnType();
    	return "";
    }
    
    @GetMapping(value = "/t2")
    public void t2(){ 
    	for (int i = 0; i < 50; i++) {
    		serviceTest.t2();
    	}    	
    }
    
    @GetMapping(value = "/t3")
    public void t3(){ 
    	for (int i = 0; i < 50; i++) {
    		serviceTest.t3(i);
    	}
    }

}
