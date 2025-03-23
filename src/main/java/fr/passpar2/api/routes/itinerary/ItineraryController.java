package fr.passpar2.api.routes.itinerary;

import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.customer.CustomerDao;
import fr.passpar2.api.routes.customer.CustomerService;
import fr.passpar2.api.routes.customer.dto.CustomerBaseDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryBaseDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryRequestDto;
import fr.passpar2.api.routes.user.UserDao;
import fr.passpar2.api.routes.user.UserService;
import fr.passpar2.api.routes.user.dto.UserBaseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final UserService userService;

    private final ItineraryService itineraryService;

    public ItineraryController(
            UserService userService,
            ItineraryService itineraryService
    ) {
        this.userService = userService;
        this.itineraryService = itineraryService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<ItineraryBaseDto>>> getAllItineraries(){
        List<ItineraryDao> itinerariesFound = itineraryService.getAllItineraries();
        List<ItineraryBaseDto> itineraries = new ArrayList<>();

        for (ItineraryDao itinerary: itinerariesFound) {
            itineraries.add(new ItineraryBaseDto(itinerary));
        }

        ApiResponse<List<ItineraryBaseDto>> response = new ApiResponse<>(itineraries, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ItineraryBaseDto>>> getAllItinerariesForUser(@PathVariable Integer userId){
        List<ItineraryDao> itinerariesFound = itineraryService.getAllItinerariesByUserId(userId);
        List<ItineraryBaseDto> itineraries = new ArrayList<>();

        for (ItineraryDao itinerary: itinerariesFound) {
            itineraries.add(new ItineraryBaseDto(itinerary));
        }

        ApiResponse<List<ItineraryBaseDto>> response = new ApiResponse<>(itineraries, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<ItineraryDto>> addItinerary(@PathVariable Integer userId, @RequestBody ItineraryRequestDto request) {
        if (request.getItinerary().size() > 8)
            return new ResponseEntity<>(new ApiResponse<>(null, null), HttpStatus.BAD_REQUEST);

        ItineraryDao itineraryCreated = itineraryService.createUserItinerary(userId, request);
        ItineraryDto itinerary = new ItineraryDto(itineraryCreated);

        UserDao itineraryUserFound = userService.getUserById(userId);
        UserBaseDto itineraryUser = new UserBaseDto(itineraryUserFound);
        itinerary.setUser(itineraryUser);

        List<CustomerDao> itineraryCustomersFound = itineraryService.getCustomersByItineraryId(itinerary.getId());
        List<CustomerBaseDto> itineraryCustomers = new ArrayList<>();
        for (CustomerDao customer: itineraryCustomersFound) {
            itineraryCustomers.add(new CustomerBaseDto(customer));
        }
        itinerary.setCustomersToVisit(itineraryCustomers);

        ApiResponse<ItineraryDto> response = new ApiResponse<>(itinerary, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItineraryDto>> getItineraryById(@PathVariable String id) {
        ItineraryDao itineraryFound = itineraryService.getItineraryById(id);
        ItineraryDto itinerary = new ItineraryDto(itineraryFound);

        UserDao itineraryUserFound = userService.getUserById(itineraryFound.getUserId());
        UserBaseDto itineraryUser = new UserBaseDto(itineraryUserFound);
        itinerary.setUser(itineraryUser);

        List<CustomerDao> itineraryCustomersFound = itineraryService.getCustomersByItineraryId(itinerary.getId());
        List<CustomerBaseDto> itineraryCustomers = new ArrayList<>();
        for (CustomerDao customer: itineraryCustomersFound) {
            itineraryCustomers.add(new CustomerBaseDto(customer));
        }
        itinerary.setCustomersToVisit(itineraryCustomers);

        ApiResponse<ItineraryDto> response = new ApiResponse<>(itinerary, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItineraryDto>> updateItineraryById(@PathVariable String id, @RequestBody ItineraryRequestDto request) {
        ItineraryDao itineraryUpdated = itineraryService.updateItineraryById(id, request);
        ItineraryDto itinerary = new ItineraryDto(itineraryUpdated);

        UserDao itineraryUserFound = userService.getUserById(itineraryUpdated.getUserId());
        UserBaseDto itineraryUser = new UserBaseDto(itineraryUserFound);
        itinerary.setUser(itineraryUser);

        List<CustomerDao> itineraryCustomersFound = itineraryService.getCustomersByItineraryId(itinerary.getId());
        List<CustomerBaseDto> itineraryCustomers = new ArrayList<>();
        for (CustomerDao customer: itineraryCustomersFound) {
            itineraryCustomers.add(new CustomerBaseDto(customer));
        }
        itinerary.setCustomersToVisit(itineraryCustomers);

        ApiResponse<ItineraryDto> response = new ApiResponse<>(itinerary, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItineraryById(@PathVariable String id) {
        ItineraryDao itineraryToDelete = itineraryService.getItineraryById(id);
        itineraryService.deleteItinerary(itineraryToDelete);

        return ResponseEntity.ok().build();
    }
}
