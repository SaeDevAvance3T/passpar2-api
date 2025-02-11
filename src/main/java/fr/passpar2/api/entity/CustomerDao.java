package fr.passpar2.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "pp2_customer")
public class CustomerDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private UserDao user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pp2_customer_contact",
            joinColumns = @JoinColumn(name = "fk_customer"),
            inverseJoinColumns = @JoinColumn(name = "fk_contact")
    )
    private List<ContactDao> contacts = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDao getUser() {
        return user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }

    public List<ContactDao> getContacts() {
        return contacts;
    }

    public void addContact(ContactDao contact) {
        this.contacts.add(contact);
    }

    public void setContacts(List<ContactDao> contacts) {
        this.contacts = contacts;
    }

    public void removeContacts(ContactDao contact) {
        this.contacts.remove(contact);
    }

    public void removeAllContacts() { this.contacts.clear(); }
}
