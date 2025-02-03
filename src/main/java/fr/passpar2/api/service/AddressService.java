package fr.passpar2.api.service;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final IAddressRepository addressRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AddressService(IAddressRepository addressRepository, MongoTemplate mongoTemplate) {
        this.addressRepository = addressRepository;
        this.mongoTemplate = mongoTemplate;
    }
    public AddressDao saveAddress(AddressDao address) {
        return addressRepository.save(address);
    }

    public AddressDao getAddressByCustomerId(int id) {
        return addressRepository.findAddressByCustomerId(id);
    }

    public AddressDao getAddressByUserId(int id) {
        return addressRepository.findAddressByUserId(id);
    }

    public AddressDao updateAddress(String id, AddressDto updatedAddress) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();

        if (updatedAddress.getStreet() != null) {
            update.set("street", updatedAddress.getStreet());
        }
        if (updatedAddress.getCity() != null) {
            update.set("city", updatedAddress.getCity());
        }
        if (updatedAddress.getPostalCode() != null) {
            update.set("postalCode", updatedAddress.getPostalCode());
        }
        if (updatedAddress.getCountry() != null) {
            update.set("country", updatedAddress.getCountry());
        }

        return mongoTemplate.findAndModify(query, update, AddressDao.class);
    }
}