package fr.passpar2.api.service;

import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final AddressService addressService;
    private final UserService userService;

    public CustomerService(
            ICustomerRepository customerRepository,
            AddressService addressService,
            UserService userService
    ) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.userService = userService;
    }

    public List<CustomerDao> getCustomersByUserId(int id) {
        return customerRepository.findAllByUserId(id);
    }

    public CustomerDao createCustomer(String name, String description, List<ContactDao> contacts, int userId) {
        if (contacts == null || contacts.isEmpty()) {
            throw new IllegalArgumentException("Un contact doit être renseigné.");
        }

        CustomerDao newCustomer = new CustomerDao();
        newCustomer.setName(name);
        newCustomer.setDescription(description);
        newCustomer.setContacts(contacts);
        newCustomer.setUser(userService.getUserById(userId));

        return customerRepository.save(newCustomer);
    }

    public List<CustomerDao> getAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDao getCustomerById(int id) {
        Optional<CustomerDao> customerOpt = customerRepository.findById(id);

        return customerOpt.orElseThrow(() ->
                new RuntimeException("Client introuvable")
        );
    }

    public void deleteCustomer(CustomerDao customer) {
        customerRepository.delete(customer);
    }
}
