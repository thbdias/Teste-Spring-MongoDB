package io.codementor.gtommee.rest_tutorial.repositories;

import io.codementor.gtommee.rest_tutorial.models.Pets;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PetsRepository extends MongoRepository<Pets, String> {
    Pets findBy_id(ObjectId _id);
    
    @Query(value = "{'dataAtual': {$gte: ?0}}")
    Pets findByData(Date data);
    
    @Query(value = "{'dataAtual': {$gt: ?0, $lt: ?1}}")
    List<Pets> findByData(Date datainicial, Date datafinal);
}
