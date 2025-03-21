package fr.passpar2.api.customer.dto;

import fr.passpar2.api.customer.CustomerDao;
import fr.passpar2.api.response.IBaseResponse;
import fr.passpar2.api.user.dto.UserBaseDto;
import fr.passpar2.api.user.UserDao;

public class CustomerBaseDto implements IBaseResponse<CustomerBaseDto> {

    private int id;
    private String name;
    private String description;
    private Boolean isProspect;
    private UserBaseDto user;

    public CustomerBaseDto() { }

    public CustomerBaseDto(CustomerDao model) {
        this.setId(model.getId());
        this.setDescription(model.getDescription());
        this.setIsProspect(model.getIsProspect());
        this.setUser(model.getUser());
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

    public Boolean getIsProspect() { return this.isProspect; }
    public void setIsProspect(Boolean isProspect) { this.isProspect = isProspect; }

    public UserBaseDto getUser() {
        return user;
    }
    public void setUser(UserBaseDto user) {
        this.user = user;
    }
    public void setUser(UserDao user) { this.setUser(new UserBaseDto(user)); }

}
