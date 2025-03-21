package fr.passpar2.api.contact;

import fr.passpar2.api.customer.CustomerDao;
import fr.passpar2.api.model.*;
import fr.passpar2.api.customer.CustomerService;
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
    public ResponseEntity<ApiResponseDto<List<ContactDto>>> getAllContacts(@RequestParam(required = false) Integer customer) {
        List<ContactDto> contactsResult = new ArrayList<ContactDto>();
        List<ContactDao> contacts;

        if (customer != null) {
            CustomerDao customerToAnalize = customerService.getCustomerById(customer);
            contacts = customerToAnalize.getContacts();
        }
        else
            contacts = contactService.getAllContacts();

        for (ContactDao contact: contacts) {
            contactsResult.add(new ContactDto(contact));
        }

        ApiResponseDto<List<ContactDto>> response = new ApiResponseDto<>(contactsResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ContactDto>> getCustomerById(@PathVariable Integer id) {
        ContactDto contact = new ContactDto(contactService.getContactById(id));
        ApiResponseDto<ContactDto> response = new ApiResponseDto<>(contact, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/customer/{id}")
    public ResponseEntity<ApiResponseDto<ContactDto>> createContactForCustomer(@PathVariable Integer id, @RequestBody ContactRequestDto request) {
        ContactDto contact = new ContactDto(customerService.addContact(id, request));

        ApiResponseDto<ContactDto> response = new ApiResponseDto<>(contact, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ContactDto>> updateContactById(@PathVariable Integer id, @RequestBody ContactRequestDto request) {
        ContactDao contactToUpdate = contactService.getContactById(id);
        contactToUpdate.setFirstName(request.getFirstName());
        contactToUpdate.setLastName(request.getLastName());
        contactToUpdate.setPhone(request.getPhone());

        contactService.saveContact(contactToUpdate);

        ContactDto contact = new ContactDto(contactToUpdate);
        ApiResponseDto<ContactDto> response = new ApiResponseDto<>(contact, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<String>> deleteContactById(@PathVariable Integer id) {
        ContactDao contactToDelete = contactService.getContactById(id);

        List<CustomerDao> customers = customerService.getCustomersByContact(contactToDelete);
        for (CustomerDao customer : customers) {
            customer.removeContacts(contactToDelete);
            customerService.saveCustomer(customer);
        }

        contactService.deleteContact(contactToDelete);

        ApiResponseDto<String> response = new ApiResponseDto<>("Contact deleted successfully", HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
