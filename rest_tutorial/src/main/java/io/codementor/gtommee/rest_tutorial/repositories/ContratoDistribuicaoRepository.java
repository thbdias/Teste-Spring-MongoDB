package io.codementor.gtommee.rest_tutorial.repositories;

import io.codementor.gtommee.rest_tutorial.models.ContratoDistribuicao;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratoDistribuicaoRepository extends MongoRepository<ContratoDistribuicao, String> {
    ContratoDistribuicao findBy_id(ObjectId id);
}
