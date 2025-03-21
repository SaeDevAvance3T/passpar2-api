package fr.passpar2.api.customer.dto;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.address.dto.AddressBaseDto;
import fr.passpar2.api.contact.ContactDao;
import fr.passpar2.api.contact.ContactDto;
import fr.passpar2.api.customer.CustomerDao;
import fr.passpar2.api.response.IFullResponse;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto extends CustomerBaseDto implements IFullResponse<CustomerBaseDto> {

    private AddressBaseDto address;
    private List<ContactDto> contacts = new ArrayList<>();

    public CustomerDto() {
        super();
    }

    public CustomerDto(CustomerDao model) {
        super(model);
        for(ContactDao contact: model.getContacts()) {
            this.addContacts(contact);
        }
    }

    public AddressBaseDto getAddress() { return this.address; }
    public void setAddress(AddressBaseDto address) { this.address = address; }
    public void setAddress(AddressDao address) { this.setAddress(new AddressBaseDto(address)); }

    public List<ContactDto> getContacts() { return this.contacts; }
    public void setContacts(List<ContactDto> contacts) { this.contacts = contacts; }
    public void addContacts(ContactDto contact) { this.contacts.add(contact); }
    public void addContacts(ContactDao contact) { this.addContacts(new ContactDto(contact)); }
    public void removeContacts(ContactDto contact) { this.contacts.remove(contact); }
    public void removeContacts(ContactDao contact) { this.removeContacts(new ContactDto(contact)); }
}
