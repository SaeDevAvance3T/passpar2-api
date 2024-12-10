package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.CustomerRequestDto;
import fr.passpar2.api.service.AddressService;
import fr.passpar2.api.service.ContactService;
import fr.passpar2.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class customerRestController {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final ContactService contactService;

    public customerRestController(CustomerService customerService,AddressService addressService, ContactService contactService) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<CustomerDao>>> getAllCustomers() {
        ApiResponseDto<List<CustomerDao>> response = new ApiResponseDto<>(customerService.getAllCustomers(), HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<CustomerDao>> addCustomer(@RequestBody CustomerRequestDto request) {
        AddressDao savedAddress = addressService.createAddress(request.getAddress());

        List<ContactDao> savedContacts = new ArrayList<ContactDao>();
        for (ContactDao contact : request.getContacts()) {
            savedContacts.add(contactService.createContact(contact));
        }

        CustomerDao newCustomer = customerService.createCustomer(
                request.getName(),
                request.getDescription(),
                savedAddress,
                savedContacts
        );

        ApiResponseDto<CustomerDao> response = new ApiResponseDto<CustomerDao>(newCustomer, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}