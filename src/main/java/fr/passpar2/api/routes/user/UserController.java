package fr.passpar2.api.routes.user;

import fr.passpar2.api.routes.address.AddressDao;
import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.address.AddressService;
import fr.passpar2.api.routes.user.dto.UserBaseDto;
import fr.passpar2.api.routes.user.dto.UserDto;
import fr.passpar2.api.routes.user.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AddressService addressService;

    public UserController(
            UserService userService,
            AddressService addressService
    ) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserBaseDto>>> getAllUsers() {
        List<UserDao> usersFound = userService.getAllUsers();
        List<UserBaseDto> users = new ArrayList<>();

        for (UserDao user : usersFound) {
            users.add(new UserBaseDto(user));
        }

        ApiResponse<List<UserBaseDto>> response = new ApiResponse<>(users, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Integer id) {
        UserDao userFound = userService.getUserById(id);
        UserDto user = new UserDto(userFound);

        AddressDao userAddressFound = addressService.getAddressByUserId(userFound.getId());
        user.setAddress(userAddressFound);

        ApiResponse<UserDto> response = new ApiResponse<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> putUserById(
            @PathVariable Integer id, @RequestBody UserRequestDto request) {
        UserDao userUpdated = userService.updateUserById(id, request);
        UserDto user = new UserDto(userUpdated);

        AddressDao userAddressUpdated = addressService.updateAddress(request.getAddress().getId(), request.getAddress());
        user.setAddress(userAddressUpdated);

        ApiResponse<UserDto> response = new ApiResponse<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
