package fr.passpar2.api.itinerary;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItineraryRepository extends MongoRepository<ItineraryDao, String> {
    @Query("{ 'id_user' : { $eq: ?0 } }")
    List<ItineraryDao> findByUserId(int userId);
}
