package fr.passpar2.api.customer;

import fr.passpar2.api.address.AddressService;
import fr.passpar2.api.contact.ContactDao;
import fr.passpar2.api.contact.ContactRequestDto;
import fr.passpar2.api.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final UserService userService;

    public CustomerService(
            CustomerRepository customerRepository,
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

    public CustomerDao createCustomer(String name, String description, Boolean isProspect, List<ContactDao> contacts, int userId) {
        if (contacts == null || contacts.isEmpty()) {
            throw new IllegalArgumentException("Un contact doit être renseigné.");
        }

        CustomerDao newCustomer = new CustomerDao();
        newCustomer.setName(name);
        newCustomer.setDescription(description);
        newCustomer.setIsProspect(isProspect);
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

    public CustomerDao updateCustomerById(int id, CustomerRequestDto    customer) {
        CustomerDao existingCustomer = getCustomerById(id);

        if (customer.getName() != null && !customer.getName().isEmpty())
            existingCustomer.setName(customer.getName());

        if (customer.getDescription() != null && !customer.getDescription().isEmpty())
            existingCustomer.setDescription(customer.getDescription());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(CustomerDao customer) {
        customerRepository.delete(customer);
    }

    public List<CustomerDao> getCustomersByContact(ContactDao contact) {
        return customerRepository.findByContactsContaining(contact);
    }

    public ContactDao addContact(Integer customerId, ContactRequestDto request) {
        CustomerDao customer = getCustomerById(customerId);
        ContactDao contact = new ContactDao();

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhone(request.getPhone());
        customer.addContact(contact);

        customerRepository.save(customer);
        return contact;
    }

    public void saveCustomer(CustomerDao customer) {
        customerRepository.save(customer);
    }
}
