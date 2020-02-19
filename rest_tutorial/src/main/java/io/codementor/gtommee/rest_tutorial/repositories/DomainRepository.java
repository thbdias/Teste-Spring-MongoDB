package io.codementor.gtommee.rest_tutorial.repositories;

import io.codementor.gtommee.rest_tutorial.models.Domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DomainRepository extends MongoRepository<Domain, Integer>, CustomDomainRepository {
	
	@Query(value = "{'domainName': {$in: ?0}}")
    List<Domain> operadorIn(List<String> list);
    
}
