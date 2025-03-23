package fr.passpar2.api.routes.customer.dto;

import fr.passpar2.api.routes.address.dto.AddressBaseDto;
import fr.passpar2.api.routes.contact.dto.ContactRequestDto;

import java.util.List;

public class CustomerRequestDto {
    private String name;
    private String description;
    private Boolean isProspect;
    private List<ContactRequestDto> contacts;
    private AddressBaseDto address;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return this.description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsProspect() { return this.isProspect; }
    public void setIsProspect(Boolean isProspect) { this.isProspect = isProspect; }

    public List<ContactRequestDto> getContacts() { return this.contacts; }
    public void setContacts(List<ContactRequestDto> contacts) {
        this.contacts = contacts;
    }
    public void addContacts(ContactRequestDto contact) {
        this.contacts.add(contact);
    }
    public void removeContacts(ContactRequestDto contact) { this.contacts.remove(contact); }

    public AddressBaseDto getAddress() { return this.address; }
    public void setAddress(AddressBaseDto address) { this.address = address; }
}
