package fr.passpar2.api.customer;

import fr.passpar2.api.address.AddressDto;
import fr.passpar2.api.contact.ContactRequestDto;

import java.util.List;

public class CustomerRequestDto {
    private String name;
    private String description;
    private int userId;
    private Boolean isProspect;
    private List<ContactRequestDto> contacts;
    private AddressDto address;

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

    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Boolean getIsProspect() { return this.isProspect; }
    public void setIsProspect(Boolean isProspect) { this.isProspect = isProspect; }

    public List<ContactRequestDto> getContacts() { return this.contacts; }

    public void setContacts(List<ContactRequestDto> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactRequestDto contact) {
        this.contacts.add(contact);
    }

    public AddressDto getAddress() { return this.address; }
    public void setAddress(AddressDto address) { this.address = address; }
}
