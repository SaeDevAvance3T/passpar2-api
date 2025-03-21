package fr.passpar2.api.address;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AddressRepository extends MongoRepository<AddressDao, String> {

    @Query("{ 'id_customer' : { $eq: ?0 } }")
    AddressDao findAddressByCustomerId(int customerId);

    @Query("{ 'id_user' : { $eq: ?0 } }")
    AddressDao findAddressByUserId(int userId);

}
