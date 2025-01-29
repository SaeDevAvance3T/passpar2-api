package fr.passpar2.api.model;

import fr.passpar2.api.entity.ContactDao;

import java.util.List;

public class CustomerRequestDto {
    private String name;
    private String description;
    private int userId;
    private List<ContactDao> contacts;

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

    public List<ContactDao> getContacts() { return this.contacts; }

    public void setContacts(List<ContactDao> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactDao contact) {
        this.contacts.add(contact);
    }
}
