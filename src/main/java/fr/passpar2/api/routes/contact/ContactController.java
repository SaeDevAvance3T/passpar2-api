package fr.passpar2.api.routes.contact;

import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.contact.dto.ContactBaseDto;
import fr.passpar2.api.routes.contact.dto.ContactDto;
import fr.passpar2.api.routes.contact.dto.ContactRequestDto;
import fr.passpar2.api.routes.customer.CustomerDao;
import fr.passpar2.api.routes.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    private final CustomerService customerService;

    public ContactController(
            ContactService contactService,
            CustomerService customerService
    ) {
        this.contactService = contactService;
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ContactBaseDto>>> getAllContacts() {
        List<ContactDao> contactsFound = contactService.getAllContacts();
        List<ContactBaseDto> contacts = new ArrayList<>();

        for (ContactDao contact: contactsFound) {
            contacts.add(new ContactBaseDto(contact));
        }

        ApiResponse<List<ContactBaseDto>> response = new ApiResponse<>(contacts, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactDto>> getCustomerById(@PathVariable Integer id) {
        ContactDao contactFound = contactService.getContactById(id);
        ContactDto contact = new ContactDto(contactFound);

        ApiResponse<ContactDto> response = new ApiResponse<>(contact, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<ContactBaseDto>>> getAllContactsForCustomer(@PathVariable int customerId) {
        CustomerDao customerFound = customerService.getCustomerById(customerId);
        List<ContactDao> contactsFound = customerFound.getContacts();
        List<ContactBaseDto> contacts = new ArrayList<>();

        for (ContactDao contact: contactsFound) {
            contacts.add(new ContactBaseDto(contact));
        }

        ApiResponse<List<ContactBaseDto>> response = new ApiResponse<>(contacts, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<ContactDto>> createContactForCustomer(@PathVariable int customerId, @RequestBody ContactRequestDto request) {
        CustomerDao customerFound = customerService.getCustomerById(customerId);

        ContactDao contactCreated = contactService.createContact(request.getFirstName(), request.getLastName(), request.getPhone());
        customerService.addContact(customerFound, contactCreated);
        ContactDto contact = new ContactDto(contactCreated);

        ApiResponse<ContactDto> response = new ApiResponse<>(contact, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactDto>> updateContactById(@PathVariable Integer id, @RequestBody ContactRequestDto request) {
        ContactDao contactUpdated = contactService.updateContactById(id, request);
        ContactDto contact = new ContactDto(contactUpdated);

        ApiResponse<ContactDto> response = new ApiResponse<>(contact, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteContactById(@PathVariable Integer id) {
        ContactDao contactToDelete = contactService.getContactById(id);

        List<CustomerDao> customers = customerService.getCustomersByContact(contactToDelete);
        for (CustomerDao customer : customers) {
            customer.removeContacts(contactToDelete);
            customerService.saveCustomer(customer);
        }

        contactService.deleteContact(contactToDelete);

        ApiResponse<String> response = new ApiResponse<>("Contact deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
