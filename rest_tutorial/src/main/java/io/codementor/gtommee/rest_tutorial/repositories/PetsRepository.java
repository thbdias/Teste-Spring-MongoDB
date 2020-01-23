package io.codementor.gtommee.rest_tutorial.repositories;

import io.codementor.gtommee.rest_tutorial.models.Pets;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

//public interface PetsRepository extends CrudRepository<Pets, String> {
public interface PetsRepository extends MongoRepository<Pets, String>, CustomPetsRepository {
    Pets findBy_id(ObjectId _id);
    
    @Query(value = "{'dataAtual': {$gte: ?0}}")
    Pets findByData(Date data);
    
    @Query(value = "{'dataAtual': {$gt: ?0, $lt: ?1}}")
    List<Pets> findByData(Date datainicial, Date datafinal);
    
//    @Query( value = "{ $or : [ { $where: '?0 == null' } , { 'contrato.numero' : ?0 } ] }", fields = "{'_id':1}")
//    @Query("{'name': ?#{ [0] ? {$exists :true} : [1] }}")
//    List<Pets> findNameAndSpecies(boolean param0, String param1);
    
    
    
}
