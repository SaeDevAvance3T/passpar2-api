package fr.passpar2.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.passpar2.api.entity.UserDao;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    private String passwordHash;

    public UserDto(UserDao model) {
        this.id = model.getId();
        this.firstName = model.getFirstName();
        this.lastName = model.getLastName();
        this.email = model.getEmail();
        this.passwordHash = model.getPasswordHash();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() { return this.address; }

    public void setAddress(AddressDto address) { this.address = address; }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
