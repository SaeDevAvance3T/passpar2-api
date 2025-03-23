package fr.passpar2.api.routes.auth;

import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.address.AddressDao;
import fr.passpar2.api.routes.auth.dto.LoginRequestDto;
import fr.passpar2.api.routes.user.UserDao;
import fr.passpar2.api.model.*;
import fr.passpar2.api.routes.user.UserService;
import fr.passpar2.api.routes.address.AddressService;
import fr.passpar2.api.routes.user.dto.UserDto;
import fr.passpar2.api.routes.user.dto.UserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final AddressService addressService;

    public AuthController(
            UserService userService,
            AddressService addressService
    ) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDto>> loginUser(@RequestBody LoginRequestDto request) {
        UserDao userLogin = userService.loginUser(request.getEmail(), request.getPassword());
        UserDto user = new UserDto(userLogin);

        AddressDao userAddress = addressService.getAddressByUserId(userLogin.getId());
        user.setAddress(userAddress);

        ApiResponse<UserDto> response = new ApiResponse<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> registerUser(@RequestBody UserRequestDto request) {
        UserDao userRegister = userService.registerUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );
        UserDto user = new UserDto(userRegister);

        AddressDao userAddressCreated = addressService.createUserAddress(user.getId(), request.getAddress());
        user.setAddress(userAddressCreated);

        ApiResponse<UserDto> response = new ApiResponse<>(user, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
