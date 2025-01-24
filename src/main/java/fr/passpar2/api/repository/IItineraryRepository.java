package fr.passpar2.api.repository;

import fr.passpar2.api.entity.ItineraryDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IItineraryRepository extends MongoRepository<ItineraryDao, String> {
}
