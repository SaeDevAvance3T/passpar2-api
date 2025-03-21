package fr.passpar2.api.user.dto;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.address.dto.AddressBaseDto;
import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.user.UserDao;

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
