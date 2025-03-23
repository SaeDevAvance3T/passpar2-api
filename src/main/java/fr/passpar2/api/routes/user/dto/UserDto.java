package fr.passpar2.api.routes.user.dto;

import fr.passpar2.api.routes.address.AddressDao;
import fr.passpar2.api.routes.address.dto.AddressBaseDto;
import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.routes.user.UserDao;

public class UserDto extends UserBaseDto implements IFullResponse<UserBaseDto> {
    private AddressBaseDto address;

    public UserDto() { super(); }

    public UserDto(UserDao model) {
        super(model);
    }

    public AddressBaseDto getAddress() { return this.address; }
    public void setAddress(AddressBaseDto address) { this.address = address; }
    public void setAddress(AddressDao address) { this.setAddress(new AddressBaseDto(address)); }
}
