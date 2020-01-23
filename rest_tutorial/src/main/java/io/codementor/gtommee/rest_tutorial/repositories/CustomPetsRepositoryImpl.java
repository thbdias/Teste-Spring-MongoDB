package io.codementor.gtommee.rest_tutorial.repositories;

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

    
    
}