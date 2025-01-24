package fr.passpar2.api.service;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final IAddressRepository addressRepository;

    @Autowired
    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDao saveAddress(AddressDao address) {
        return addressRepository.save(address);
    }

    public AddressDao getAddressByIdCustomer(CustomerDao customer) {
        return addressRepository.findAddressByIdCustomer(customer.getId());
    }
}