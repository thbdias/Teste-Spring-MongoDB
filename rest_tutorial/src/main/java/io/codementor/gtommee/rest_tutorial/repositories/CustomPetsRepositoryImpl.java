package io.codementor.gtommee.rest_tutorial.repositories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.codementor.gtommee.rest_tutorial.models.Pets;

public class CustomPetsRepositoryImpl implements CustomPetsRepository {

    @Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Pets getPetsByNome(String nome) {
		
		return mongoTemplate.findOne(
				Query.query(Criteria.where("name").is(nome)),
				Pets.class);
	}

	@Override
	public List<Pets> groupPets() {
//		GroupOperation groupByStateAndSumPop = gr group("state").sum("pop").as("statePop"); 
		
		
		return null;
	}

	@Override
	public String gerarAtualizacaoDivida() {
		try {
			//{"contrato":111512010010,"vlrDiv":" 00000000017345,79","datRemessa":20200310},
			
			int tam = 10;
			FileWriter arq = new FileWriter("C:\\Users\\balbinth\\Documents\\atualizacao_divida\\novo.txt");
		    BufferedWriter gravarArq = new BufferedWriter(arq);
	    
		    for (int i = 1; i <= tam; i++) {			    
		    	gravarArq.write("{");
		    	gravarArq.write("\"contrato\":");
		    	gravarArq.write(newNumeoContrato(i) + ",");
		    	gravarArq.write("\"vlrDiv\":");
		    	gravarArq.write("\" 00000000017345,79\",");
		    	gravarArq.write("\"datRemessa\":");
		    	gravarArq.write(20200310+"");
		    	gravarArq.write("},");
		    	gravarArq.newLine();
		    }
		    
			gravarArq.close();
			arq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	private String newNumeoContrato(int numero) {
		int tamMax = 12;
		String newNumber = "";
		if ((numero + "").length() < tamMax) {
			newNumber = String.join("", Collections.nCopies(tamMax - (numero + "").length(), "0"));
			newNumber += numero + "";
		}
		else {
			newNumber = numero + "";
		}
		
		return newNumber;
	}

    
    
}