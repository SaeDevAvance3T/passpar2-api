package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.CustomerDto;
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
public class CustomerRestController {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final ContactService contactService;

    public CustomerRestController(
            CustomerService customerService,
            AddressService addressService,
            ContactService contactService
    ) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<CustomerDto>>> getAllCustomers(@RequestParam(required = false) Integer user) {
        List<CustomerDto> customersResult = new ArrayList<CustomerDto>();
        List<CustomerDao> customers;

        if (user != null)
            customers = customerService.getCustomersByUserId(user);
        else
            customers = customerService.getAllCustomers();


        for (CustomerDao customer: customers) {
            AddressDao addressCustomer = addressService.getAddressByCustomerId(customer.getId());

            CustomerDto customerResult = new CustomerDto(customer);

            if (addressCustomer != null)
                customerResult.setAddress(new AddressDto(addressCustomer));

            customersResult.add(customerResult);
        }

        ApiResponseDto<List<CustomerDto>> response = new ApiResponseDto<>(customersResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<CustomerDto>> addCustomer(@RequestBody CustomerRequestDto request) {
        List<ContactDao> savedContacts = new ArrayList<ContactDao>();
        for (ContactDao contact : request.getContacts()) {
            savedContacts.add(contactService.createContact(contact));
        }

        CustomerDao newCustomer = customerService.createCustomer(
                request.getName(),
                request.getDescription(),
                savedContacts,
                request.getUserId()
        );

        AddressDao customerAddress = new AddressDao(request.getAddress());
        customerAddress.setCustomerId(newCustomer.getId());
        addressService.saveAddress(customerAddress);

        CustomerDto customer = new CustomerDto(newCustomer);
        customer.setAddress(new AddressDto(customerAddress));

        ApiResponseDto<CustomerDto> response = new ApiResponseDto<>(customer, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
