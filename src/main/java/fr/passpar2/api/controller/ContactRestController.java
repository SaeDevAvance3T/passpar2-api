package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.ContactDto;
import fr.passpar2.api.model.CustomerDto;
import fr.passpar2.api.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactRestController {

    private final ContactService contactService;

    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<ContactDto>>> getAllContacts(@RequestParam(required = false) Integer customer) {
        List<ContactDto> contactsResult = new ArrayList<ContactDto>();
        List<ContactDao> contacts;

        // if (customer != null)
            // contacts = contactService.getContactsByCustomerId(customer);
        // else
        contacts = contactService.getAllContacts();

        for (ContactDao contact: contacts) {
            contactsResult.add(new ContactDto(contact));
        }

        ApiResponseDto<List<ContactDto>> response = new ApiResponseDto<>(contactsResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
