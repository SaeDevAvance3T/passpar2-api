package fr.passpar2.api.routes.customer;

import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.address.AddressDao;
import fr.passpar2.api.routes.contact.ContactDao;
import fr.passpar2.api.routes.contact.dto.ContactRequestDto;
import fr.passpar2.api.routes.customer.dto.CustomerBaseDto;
import fr.passpar2.api.routes.customer.dto.CustomerDto;
import fr.passpar2.api.routes.customer.dto.CustomerRequestDto;
import fr.passpar2.api.routes.address.AddressService;
import fr.passpar2.api.routes.contact.ContactService;
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
    public ResponseEntity<ApiResponse<List<CustomerBaseDto>>> getAllCustomers() {
        List<CustomerDao> customerFound = customerService.getAllCustomers();
        List<CustomerBaseDto> customers = new ArrayList<>();

        for (CustomerDao customer: customerFound) {
            customers.add(new CustomerBaseDto(customer));
        }

        ApiResponse<List<CustomerBaseDto>> response = new ApiResponse<>(customers, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CustomerBaseDto>>> getAllCustomersForUser(@PathVariable int userId) {
        List<CustomerDao> customerFound = customerService.getAllCustomers();
        List<CustomerBaseDto> customers = new ArrayList<>();

        for (CustomerDao customer: customerFound) {
            if (customer.getUser().getId() == userId)
                customers.add(new CustomerBaseDto(customer));
        }

        ApiResponse<List<CustomerBaseDto>> response = new ApiResponse<>(customers, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("user/{userId}")
    public ResponseEntity<ApiResponse<CustomerDto>> addCustomer(@PathVariable int userId, @RequestBody CustomerRequestDto request) {
        List<ContactDao> customerContactsCreated = new ArrayList<>();
        for (ContactRequestDto contact : request.getContacts()) {
            ContactDao contactCreated = contactService.createContact(
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getPhone()
            );
            customerContactsCreated.add(contactCreated);
        }

        CustomerDao customerCreated = customerService.createCustomer(
                request.getName(),
                request.getDescription(),
                request.getIsProspect(),
                customerContactsCreated,
                userId
        );
        CustomerDto customer = new CustomerDto(customerCreated);

        AddressDao customerAddressCreated = addressService.createCustomerAddress(customerCreated.getId(), request.getAddress());
        customer.setAddress(customerAddressCreated);

        ApiResponse<CustomerDto> response = new ApiResponse<>(customer, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDto>> getCustomerById(@PathVariable Integer id) {
        CustomerDao customerFound = customerService.getCustomerById(id);
        CustomerDto customer = new CustomerDto(customerFound);

        AddressDao customerAddressFound = addressService.getAddressByCustomerId(id);
        customer.setAddress(customerAddressFound);

        ApiResponse<CustomerDto> response = new ApiResponse<>(customer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDto>> uptdateCustomerById(@PathVariable Integer id, @RequestBody CustomerRequestDto request) {
        CustomerDao customerUpdated = customerService.updateCustomerById(id, request);
        CustomerDto customer = new CustomerDto(customerUpdated);

        String customerAddressId = request.getAddress().getId();
        AddressDao customerAddressUpdated = addressService.updateAddress(customerAddressId, request.getAddress());
        customer.setAddress(customerAddressUpdated);

        ApiResponse<CustomerDto> response = new ApiResponse<>(customer, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomerById(@PathVariable Integer id) {
        CustomerDao customerToDelete = customerService.getCustomerById(id);
        AddressDao addressToDelete = addressService.getAddressByCustomerId(customerToDelete.getId());

        customerService.deleteCustomer(customerToDelete);
        addressService.deleteAddress(addressToDelete);

        return ResponseEntity.ok().build();
    }
}
