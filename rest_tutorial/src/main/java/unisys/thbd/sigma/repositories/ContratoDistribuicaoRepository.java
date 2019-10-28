package unisys.thbd.sigma.repositories;

import unisys.thbd.sigma.models.ContratoDistiruicaoModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratoDistribuicaoRepository extends MongoRepository<ContratoDistiruicaoModel, String> {
    ContratoDistiruicaoModel findBy_id(ObjectId id);
}
