package fr.passpar2.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.passpar2.api.entity.ContactDao;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ContactDto {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public ContactDto() { }

    public ContactDto(ContactDao model) {
        id = model.getId();
        firstName = model.getFirstName();
        lastName = model.getLastName();
        phone = model.getPhone();
    }

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
}
