package io.codementor.gtommee.rest_tutorial.repositories;

import io.codementor.gtommee.rest_tutorial.models.Company;
import io.codementor.gtommee.rest_tutorial.models.Pets;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PetsRepository extends MongoRepository<Pets, String> {
    List<Company> findByName(String name);

    @Query("{'contact.address': ?0}")
    List<Company> findByAddress(String address);
}
