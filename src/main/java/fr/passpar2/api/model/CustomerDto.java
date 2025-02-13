package fr.passpar2.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ContactDao;
import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.UserDao;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerDto {
    private int id;
    private String name;
    private String description;
    private AddressDto address;
    private UserDao user;
    private List<ContactDao> contacts;
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Boolean isProspect;

    public CustomerDto() { }

    public CustomerDto(CustomerDao model){
        this.id = model.getId();
        this.name = model.getName();
        this.description = model.getDescription();
        this.user = model.getUser();
        this.contacts = model.getContacts();
        this.isProspect = model.getIsProspect();
    }

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

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        if (address == null) return;
        this.address = address;
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

    public void setContacts(List<ContactDao> contacts) {
        this.contacts = contacts;
    }

    public Boolean getIsProspect() { return this.isProspect; }

    public void setIsProspect(Boolean isProspect) { this.isProspect = isProspect; }
}
