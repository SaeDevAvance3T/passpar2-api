package fr.passpar2.api.model;

import fr.passpar2.api.entity.ContactDao;

import java.util.List;

public class CustomerRequestDto {
    private String name;
    private String description;
    private int userId;
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
