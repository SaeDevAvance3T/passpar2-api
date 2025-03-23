package fr.passpar2.api.routes.course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<CourseDao, String> {
    @Query("{ 'id_user' : { $eq : ?0 } }")
    List<CourseDao> findAllByUserId(int userId);
}
