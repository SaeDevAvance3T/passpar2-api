package fr.passpar2.api.routes;

import fr.passpar2.api.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InitController {

    @GetMapping()
    public ResponseEntity<ApiResponse<String>> initApi() {
        ApiResponse<String> response = new ApiResponse<>("Welcome on Passpar2's API !", HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}