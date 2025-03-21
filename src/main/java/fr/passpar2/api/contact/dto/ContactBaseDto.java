package fr.passpar2.api.contact.dto;

import fr.passpar2.api.contact.ContactDao;
import fr.passpar2.api.response.IBaseResponse;

public class ContactBaseDto implements IBaseResponse<ContactBaseDto> {

    private int id;
    private String firstName;
    private String lastName;
    private String phone;

    public ContactBaseDto() { }

    public ContactBaseDto(ContactDao model) {
        this.setId(model.getId());
        this.setFirstName(model.getFirstName());
        this.setLastName(model.getLastName());
        this.setPhone(model.getPhone());
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
