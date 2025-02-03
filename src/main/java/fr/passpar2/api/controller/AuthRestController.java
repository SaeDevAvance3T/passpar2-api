package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.*;
import fr.passpar2.api.service.UserService;
import fr.passpar2.api.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthRestController {

    private final UserService userService;
    private final AddressService addressService;

    public AuthRestController(
            UserService userService,
            AddressService addressService
    ) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<UserDto>> loginUser(@RequestBody LoginRequestDto request) {
        UserDao userLogin = userService.loginUser(request.getEmail(), request.getPassword());

        AddressDao userAddress = addressService.getAddressByUserId(userLogin.getId());

        UserDto user = new UserDto(userLogin);
        user.setAddress(new AddressDto(userAddress));

        ApiResponseDto<UserDto> response = new ApiResponseDto<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<UserDto>> registerUser(@RequestBody UserRequestDto request) {
        UserDao userRegister = userService.registerUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );

        AddressDao userAddress = new AddressDao(request.getAddress());
        userAddress.setUserId(userRegister.getId());
        addressService.saveAddress(userAddress);

        UserDto user = new UserDto(userRegister);
        user.setAddress(new AddressDto(userAddress));

        ApiResponseDto<UserDto> response = new ApiResponseDto<>(user, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
