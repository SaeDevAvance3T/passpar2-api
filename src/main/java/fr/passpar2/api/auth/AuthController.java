package fr.passpar2.api.auth;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.address.AddressDto;
import fr.passpar2.api.user.UserDao;
import fr.passpar2.api.model.*;
import fr.passpar2.api.user.UserService;
import fr.passpar2.api.address.AddressService;
import fr.passpar2.api.user.UserV0Dto;
import fr.passpar2.api.user.UserRequestDto;
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
    public ResponseEntity<ApiResponseDto<UserV0Dto>> loginUser(@RequestBody LoginRequestDto request) {
        UserDao userLogin = userService.loginUser(request.getEmail(), request.getPassword());

        AddressDao userAddress = addressService.getAddressByUserId(userLogin.getId());

        UserV0Dto user = new UserV0Dto(userLogin);
        user.setAddress(new AddressDto(userAddress));

        ApiResponseDto<UserV0Dto> response = new ApiResponseDto<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<UserV0Dto>> registerUser(@RequestBody UserRequestDto request) {
        UserDao userRegister = userService.registerUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );

        AddressDao userAddress = new AddressDao(request.getAddress());
        userAddress.setUserId(userRegister.getId());
        addressService.saveAddress(userAddress);

        UserV0Dto user = new UserV0Dto(userRegister);
        user.setAddress(new AddressDto(userAddress));

        ApiResponseDto<UserV0Dto> response = new ApiResponseDto<>(user, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
