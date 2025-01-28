package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.LoginRequestDto;
import fr.passpar2.api.model.RegisterRequestDto;
import fr.passpar2.api.service.AddressService;
import fr.passpar2.api.service.UserService;
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
    public ResponseEntity<ApiResponseDto<UserDao>> loginUser(@RequestBody LoginRequestDto request) {
        UserDao userLogin = userService.loginUser(request.getEmail(), request.getPassword());
        ApiResponseDto<UserDao> response = new ApiResponseDto<>(userLogin, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<UserDao>> registerUser(@RequestBody RegisterRequestDto request) {
        UserDao userRegister = userService.registerUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()
        );

        AddressDao address = new AddressDao(request.getAddress());
        address.setCustomerId(userRegister.getId());

        addressService.saveAddress(address);

        ApiResponseDto<UserDao> response = new ApiResponseDto<>(userRegister, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
