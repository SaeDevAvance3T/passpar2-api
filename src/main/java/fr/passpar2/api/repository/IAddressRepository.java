package fr.passpar2.api.repository;

import fr.passpar2.api.entity.AddressDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IAddressRepository extends MongoRepository<AddressDao, String> {

    @Query("{ 'id_customer' : ?0 }")
    AddressDao findAddressByIdCustomer(int idCustomer);

}
