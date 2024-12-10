package fr.passpar2.api.service;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDao createCustomer(String name, String description, AddressDao address, List<ContactDao> contacts) {
        if (address == null)
            throw new IllegalArgumentException("L'adresse ne peut pas être nulle.");

        if (1 > contacts.size())
            throw new IllegalArgumentException("Un contact doit être renseigné.");

        CustomerDao newCustomer = new CustomerDao();
        newCustomer.setName(name);
        newCustomer.setName(description);
        newCustomer.setAddress(address);
        newCustomer.setContacts(contacts);

        return customerRepository.save(newCustomer);
    }

    public List<CustomerDao> getAllCustomers() {
        return customerRepository.findAll();
    }
}
