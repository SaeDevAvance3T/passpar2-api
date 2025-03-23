package fr.passpar2.api.routes.address;

import fr.passpar2.api.routes.address.dto.AddressBaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AddressService(AddressRepository addressRepository, MongoTemplate mongoTemplate) {
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

    public AddressDao createCustomerAddress(int customerId, AddressBaseDto request) {
        AddressDao address = new AddressDao(request);
        address.setCustomerId(customerId);
        return addressRepository.save(address);
    }

    public AddressDao createUserAddress(int userId, AddressBaseDto request) {
        AddressDao address = new AddressDao(request);
        address.setUserId(userId);
        return addressRepository.save(address);
    }

    public AddressDao updateAddress(String id, AddressBaseDto updatedAddress) {
        Optional<AddressDao> existingAddressOpt = addressRepository.findById(id);

        if (existingAddressOpt.isPresent()) {
            AddressDao existingAddress = existingAddressOpt.get();

            if (updatedAddress.getStreet() != null)
                existingAddress.setStreet(updatedAddress.getStreet());
            if (updatedAddress.getCity() != null)
                existingAddress.setCity(updatedAddress.getCity());
            if (updatedAddress.getPostalCode() != null)
                existingAddress.setPostalCode(updatedAddress.getPostalCode());
            if (updatedAddress.getCountry() != null)
                existingAddress.setCountry(updatedAddress.getCountry());
            if (updatedAddress.getSupplement() != null)
                existingAddress.setSupplement(updatedAddress.getSupplement());

            return addressRepository.save(existingAddress);
        }

        return null;
    }

    public void deleteAddress(AddressDao address) {
        addressRepository.delete(address);
    }
}