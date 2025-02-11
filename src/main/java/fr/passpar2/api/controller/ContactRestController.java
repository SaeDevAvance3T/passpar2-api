package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.ContactDto;
import fr.passpar2.api.model.CustomerDto;
import fr.passpar2.api.service.ContactService;
import fr.passpar2.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {

    private final ContactService contactService;

    private final CustomerService customerService;

    public ContactRestController(
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
}
