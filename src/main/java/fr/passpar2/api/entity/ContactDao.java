package fr.passpar2.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pp2_contact")
public class ContactDao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    @ManyToMany(mappedBy="contacts")
    private List<CustomerDao> customers ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CustomerDao> getCustomers() { return this.customers; }

    public void setCustomers(List<CustomerDao> customers) { this.customers = customers; }

    public void addCustomers(CustomerDao customer) { this.customers.add(customer); }
    public void removeCustomers(CustomerDao customer) { this.customers.remove(customer); }
}
