package fr.passpar2.api.user;

import fr.passpar2.api.address.AddressDto;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.address.AddressService;
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
    public ResponseEntity<ApiResponseDto<List<UserV0Dto>>> getAllUsers() {
        List<UserDao> users = userService.getAllUsers();
        List<UserV0Dto> usersResult = new ArrayList<>();
        for(UserDao user: users) {
            usersResult.add(new UserV0Dto(user));
        }
        ApiResponseDto<List<UserV0Dto>> response = new ApiResponseDto<>(usersResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserV0Dto>> getUserById(@PathVariable Integer id) {
        UserV0Dto user = new UserV0Dto(userService.getUserById(id));
        user.setAddress(new AddressDto(addressService.getAddressByUserId(user.getId())));

        ApiResponseDto<UserV0Dto> response = new ApiResponseDto<>(user, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserV0Dto>> putUserById(
            @PathVariable Integer id, @RequestBody UserRequestDto request) {
        UserV0Dto updatedUser = new UserV0Dto(userService.updateUserById(id, request));
        AddressDto uptadedAddress = new AddressDto(addressService
                .updateAddress(request.getAddress().getId(), request.getAddress()));
        updatedUser.setAddress(uptadedAddress);

        ApiResponseDto<UserV0Dto> response = new ApiResponseDto<>(updatedUser, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
