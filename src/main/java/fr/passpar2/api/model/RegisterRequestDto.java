package fr.passpar2.api.model;

import fr.passpar2.api.entity.AddressDao;

public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AddressDao address;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressDao getAddress() {
        return address;
    }

    public void setAddress(AddressDao address) {
        this.address = address;
    }
}
