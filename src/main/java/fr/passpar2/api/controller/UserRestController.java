package fr.passpar2.api.controller;

import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import fr.passpar2.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<UserDao>>> getAllUsers() {
        ApiResponseDto<List<UserDao>> response = new ApiResponseDto<>(userService.getAllUsers(), HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserDao>> getUserByEmail(@PathVariable Integer id) {
        ApiResponseDto<UserDao> response = new ApiResponseDto<>(userService.getUserById(id), HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
