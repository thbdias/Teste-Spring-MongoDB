package unisys.thbd.sigma.repositories;

import unisys.thbd.sigma.models.ContratoDistribuicaoModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratoDistribuicaoRepository extends MongoRepository<ContratoDistribuicaoModel, String> {
    ContratoDistribuicaoModel findBy_id(ObjectId id);
}
