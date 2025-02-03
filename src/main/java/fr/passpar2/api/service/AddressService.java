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
import java.util.Optional;

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
        Optional<AddressDao> existingAddressOpt = addressRepository.findById(id);

        if (existingAddressOpt.isPresent()) {
            AddressDao existingAddress = existingAddressOpt.get();

            if (updatedAddress.getStreet() != null) {
                existingAddress.setStreet(updatedAddress.getStreet());
            }
            if (updatedAddress.getCity() != null) {
                existingAddress.setCity(updatedAddress.getCity());
            }
            if (updatedAddress.getPostalCode() != null) {
                existingAddress.setPostalCode(updatedAddress.getPostalCode());
            }
            if (updatedAddress.getCountry() != null) {
                existingAddress.setCountry(updatedAddress.getCountry());
            }

            return addressRepository.save(existingAddress);
        }

        return null;
    }
}