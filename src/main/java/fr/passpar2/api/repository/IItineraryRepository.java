package fr.passpar2.api.repository;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ItineraryDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IItineraryRepository extends MongoRepository<ItineraryDao, String> {
    @Query("{ 'id_user' : { $eq: ?0 } }")
    List<ItineraryDao> findByUserId(int userId);
}
