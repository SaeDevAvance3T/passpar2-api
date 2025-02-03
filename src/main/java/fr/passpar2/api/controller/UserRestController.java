package fr.passpar2.api.controller;

import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.UserDto;
import fr.passpar2.api.model.UserRequestDto;
import fr.passpar2.api.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fr.passpar2.api.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final AddressService addressService;

    public UserRestController(
            UserService userService,
            AddressService addressService
    ) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<UserDao>>> getAllUsers() {
        ApiResponseDto<List<UserDao>> response = new ApiResponseDto<>(userService.getAllUsers(), HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserDto>> getUserById(@PathVariable Integer id) {
        UserDto user = new UserDto(userService.getUserById(id));
        user.setAddress(new AddressDto(addressService.getAddressByUserId(user.getId())));

        ApiResponseDto<UserDto> response = new ApiResponseDto<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserDto>> putUserById(
            @PathVariable Integer id, @RequestBody UserRequestDto request) {
        UserDto updatedUser = new UserDto(userService.updateUserById(id, request));
        ApiResponseDto<UserDto> response = new ApiResponseDto<>(updatedUser, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
