package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.*;
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
        List<ContactDao> savedContacts = new ArrayList<>();
        for (ContactRequestDto contact : request.getContacts()) {
            savedContacts.add(contactService.createContact(
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getPhone()
            ));
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerDto>> getCustomerById(@PathVariable Integer id) {
        CustomerDto customer = new CustomerDto(customerService.getCustomerById(id));
        ApiResponseDto<CustomerDto> response = new ApiResponseDto<>(customer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerDto>> uptdateCustomerById(@PathVariable Integer id, @RequestBody CustomerRequestDto request) {
        CustomerDto updatedCustomer = new CustomerDto(customerService.updateCustomerById(id, request));
        AddressDto updatedAddress = new AddressDto(addressService
                .updateAddress(request.getAddress().getId(), request.getAddress()));
        updatedCustomer.setAddress(updatedAddress);

        ApiResponseDto<CustomerDto> response = new ApiResponseDto<>(updatedCustomer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable Integer id) {
        CustomerDao customerToDelete = customerService.getCustomerById(id);
        AddressDao addressToDelete = addressService.getAddressByCustomerId(customerToDelete.getId());

        List<ContactDao> contactsToDelete = customerToDelete.getContacts();

        customerService.deleteCustomer(customerToDelete);
        addressService.deleteAddress(addressToDelete);
        for (ContactDao contactToDelete : contactsToDelete) {
            contactService.deleteContact(contactToDelete);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/contacts/{contact}")
    public ResponseEntity deleteCustomerContactById(@PathVariable Integer id, @PathVariable Integer contact) {
        CustomerDao customerToDelete = customerService.getCustomerById(id);
        ContactDao contactToDelete = contactService.getContactById(id);
        customerToDelete.removeContacts(contactToDelete);

        return ResponseEntity.ok().build();
    }
}
