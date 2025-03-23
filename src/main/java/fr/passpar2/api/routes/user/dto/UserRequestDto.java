package fr.passpar2.api.routes.user.dto;

import fr.passpar2.api.routes.address.dto.AddressBaseDto;

public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AddressBaseDto address;

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

    public AddressBaseDto getAddress() { return this.address; }

    public void setAddress(AddressBaseDto address) { this.address = address; }
}
