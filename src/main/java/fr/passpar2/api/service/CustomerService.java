package fr.passpar2.api.service;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.IAddressRepository;
import fr.passpar2.api.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerService(ICustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public List<CustomerDao> getCustomersByUserId(int id) {
        return customerRepository.findAllByUserId(id);
    }

    public CustomerDao createCustomer(String name, String description, List<ContactDao> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            throw new IllegalArgumentException("Un contact doit être renseigné.");
        }

        CustomerDao newCustomer = new CustomerDao();
        newCustomer.setName(name);
        newCustomer.setDescription(description);
        newCustomer.setContacts(contacts);

        return customerRepository.save(newCustomer);
    }

    public List<CustomerDao> getAllCustomers() {
        return customerRepository.findAll();
    }
}
