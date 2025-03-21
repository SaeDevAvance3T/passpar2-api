package fr.passpar2.api.customer;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.address.AddressDto;
import fr.passpar2.api.contact.ContactDao;
import fr.passpar2.api.contact.ContactRequestDto;
import fr.passpar2.api.model.*;
import fr.passpar2.api.address.AddressService;
import fr.passpar2.api.contact.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;
    private final ContactService contactService;

    public CustomerController(
            CustomerService customerService,
            AddressService addressService,
            ContactService contactService
    ) {
        this.customerService = customerService;
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<CustomerV0Dto>>> getAllCustomers(@RequestParam(required = false) Integer user) {
        List<CustomerV0Dto> customersResult = new ArrayList<CustomerV0Dto>();
        List<CustomerDao> customers;

        if (user != null)
            customers = customerService.getCustomersByUserId(user);
        else
            customers = customerService.getAllCustomers();


        for (CustomerDao customer: customers) {
            AddressDao addressCustomer = addressService.getAddressByCustomerId(customer.getId());

            CustomerV0Dto customerResult = new CustomerV0Dto(customer);

            if (addressCustomer != null)
                customerResult.setAddress(new AddressDto(addressCustomer));

            customersResult.add(customerResult);
        }

        ApiResponseDto<List<CustomerV0Dto>> response = new ApiResponseDto<>(customersResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<CustomerV0Dto>> addCustomer(@RequestBody CustomerRequestDto request) {
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
                request.getIsProspect(),
                savedContacts,
                request.getUserId()
        );

        AddressDao customerAddress = new AddressDao(request.getAddress());
        customerAddress.setCustomerId(newCustomer.getId());
        addressService.saveAddress(customerAddress);

        CustomerV0Dto customer = new CustomerV0Dto(newCustomer);
        customer.setAddress(new AddressDto(customerAddress));

        ApiResponseDto<CustomerV0Dto> response = new ApiResponseDto<>(customer, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerV0Dto>> getCustomerById(@PathVariable Integer id) {
        CustomerV0Dto customer = new CustomerV0Dto(customerService.getCustomerById(id));
        AddressDto address = new AddressDto(addressService.getAddressByCustomerId(customer.getId()));
        customer.setAddress(address);
        ApiResponseDto<CustomerV0Dto> response = new ApiResponseDto<>(customer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CustomerV0Dto>> uptdateCustomerById(@PathVariable Integer id, @RequestBody CustomerRequestDto request) {
        CustomerV0Dto updatedCustomer = new CustomerV0Dto(customerService.updateCustomerById(id, request));
        AddressDto updatedAddress = new AddressDto(addressService
                .updateAddress(request.getAddress().getId(), request.getAddress()));
        updatedCustomer.setAddress(updatedAddress);

        ApiResponseDto<CustomerV0Dto> response = new ApiResponseDto<>(updatedCustomer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable Integer id) {
        CustomerDao customerToDelete = customerService.getCustomerById(id);
        AddressDao addressToDelete = addressService.getAddressByCustomerId(customerToDelete.getId());

        List<ContactDao> contactsToDelete = customerToDelete.getContacts();

        customerService.deleteCustomer(customerToDelete);
        addressService.deleteAddress(addressToDelete);

        return ResponseEntity.ok().build();
    }
}
