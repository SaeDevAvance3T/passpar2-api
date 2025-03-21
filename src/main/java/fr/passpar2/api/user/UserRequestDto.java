package fr.passpar2.api.user;

import fr.passpar2.api.address.AddressDto;

public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AddressDto address;

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

    public AddressDto getAddress() { return this.address; }

    public void setAddress(AddressDto address) { this.address = address; }
}
