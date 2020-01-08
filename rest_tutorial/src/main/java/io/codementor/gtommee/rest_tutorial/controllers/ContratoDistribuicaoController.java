package io.codementor.gtommee.rest_tutorial.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.codementor.gtommee.rest_tutorial.models.*;
import io.codementor.gtommee.rest_tutorial.repositories.ContratoDistribuicaoRepository;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

@RestController
@RequestMapping("/contrato_distribuicao")
public class ContratoDistribuicaoController {
    @Autowired
    private ContratoDistribuicaoRepository contratoDistribuicaoRepository;

    @RequestMapping(value = "/gravarContratoDistribuicao", method = RequestMethod.GET)
    public String gravarContratoDistribuicaoModel() {
        	try (FileReader reader = new FileReader("C:\\Users\\balbinth\\Documents\\othr\\Teste-Spring-MongoDB\\cont3-2.json")) {
            long tempo_inicio = System.currentTimeMillis();
            JsonElement jsonElement = JsonParser.parseReader(reader);

            ContratoDistribuicao contratoDistribuicao;

            for (JsonElement itemContratoArray : jsonElement.getAsJsonArray()){
                contratoDistribuicao = new ContratoDistribuicao();                

                contratoDistribuicao = new ObjectMapper()
                							.readerFor(ContratoDistribuicao.class)
                							.readValue(itemContratoArray.getAsJsonObject()
                									.getAsJsonObject("contratoDistribuicao")
                									.toString());
                try {
                	contratoDistribuicaoRepository.insert(contratoDistribuicao);
                	System.out.println("\ncontrato gravado: " + contratoDistribuicao.get_id());
				} catch (Exception e) {
					if (e instanceof org.springframework.dao.DuplicateKeyException) {
						System.out.println("\nchave duplicada: " + contratoDistribuicao.get_id());
					} 
					e.printStackTrace();
				}
                                
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

}
