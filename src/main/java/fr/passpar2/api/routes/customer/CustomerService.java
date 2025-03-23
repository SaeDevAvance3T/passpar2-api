package fr.passpar2.api.routes.customer;

import fr.passpar2.api.routes.contact.ContactDao;
import fr.passpar2.api.routes.contact.dto.ContactRequestDto;
import fr.passpar2.api.routes.customer.dto.CustomerRequestDto;
import fr.passpar2.api.routes.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    public CustomerService(
            CustomerRepository customerRepository,
            UserService userService) {
        this.customerRepository = customerRepository;
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
        return customerRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Client introuvable")
        );
    }

    public CustomerDao updateCustomerById(int id, CustomerRequestDto customer) {
        CustomerDao existingCustomer = getCustomerById(id);

        if (customer.getName() != null && !customer.getName().isEmpty())
            existingCustomer.setName(customer.getName());

        if (customer.getDescription() != null && !customer.getDescription().isEmpty())
            existingCustomer.setDescription(customer.getDescription());

        if (customer.getIsProspect() != null)
            existingCustomer.setIsProspect(customer.getIsProspect());

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
        customer.addContacts(contact);

        customerRepository.save(customer);
        return contact;
    }

    public void saveCustomer(CustomerDao customer) {
        customerRepository.save(customer);
    }

}
