package fr.passpar2.api.user.dto;

import fr.passpar2.api.response.IBaseResponse;
import fr.passpar2.api.user.UserDao;

public class UserBaseDto implements IBaseResponse<UserBaseDto> {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public UserBaseDto() { }

    public UserBaseDto(UserDao model) {
        this.setId(model.getId());
        this.setFirstName(getFirstName());
        this.setLastName(model.getLastName());
        this.setEmail(model.getEmail());
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

}
