package fr.passpar2.api.address.dto;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.customer.dto.CustomerBaseDto;
import fr.passpar2.api.customer.CustomerDao;
import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.user.UserDao;
import fr.passpar2.api.user.dto.UserBaseDto;

public class AddressDto extends AddressBaseDto implements IFullResponse<AddressBaseDto> {

    private CustomerBaseDto customer;
    private UserBaseDto user;

    public AddressDto() {
        super();
    }

    public AddressDto(AddressDao model) {
        super(model);
    }

    public CustomerBaseDto getCustomer() { return this.customer; }
    public void setCustomer(CustomerBaseDto customer) { this.customer = customer; }
    public void setCustomer(CustomerDao customer) { this.setCustomer(new CustomerBaseDto(customer)); }

    public UserBaseDto getUser() { return this.user; }
    public void setUser(UserBaseDto user) { this.user = user; }
    public void setUser(UserDao user) { this.setUser(new UserBaseDto(user)); }

}
