package fr.passpar2.api.itinerary;

import fr.passpar2.api.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    private final ItineraryService itineraryService;

    public ItineraryController(
            ItineraryService itineraryService
    ) {
        this.itineraryService = itineraryService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponseDto<List<ItineraryDto>>> getAllItineraries(@RequestParam(required = false) Integer user){
        List<ItineraryDto> itinerariesResult = new ArrayList<>();
        List<ItineraryDao> itineraries;

        if (user != null)
            itineraries = itineraryService.getAllItinerariesByUserId(user);
        else
            itineraries = itineraryService.getAllItineraries();

        for (ItineraryDao itinerary: itineraries) {
            itinerariesResult.add(new ItineraryDto(itinerary));
        }

        ApiResponseDto<List<ItineraryDto>> response = new ApiResponseDto<>(itinerariesResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ApiResponseDto<ItineraryDto>> addItinerary(@RequestBody ItineraryRequestDto request) {
        if (request.getItinerary().size() > 8)
            return new ResponseEntity<>(new ApiResponseDto<>(null, null), HttpStatus.CREATED);

        ItineraryDao itineraryCreated = itineraryService.createItinerary(request);

        ApiResponseDto<ItineraryDto> response = new ApiResponseDto<>(new ItineraryDto(itineraryCreated), HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ItineraryDto>> getItineraryById(@PathVariable String id) {
        ItineraryDao itinerary = itineraryService.getItineraryById(id);
        ItineraryDto itineraryResult = new ItineraryDto(itinerary);

        ApiResponseDto<ItineraryDto> response = new ApiResponseDto<>(itineraryResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ItineraryDto>> updateItineraryById(@PathVariable String id, @RequestBody ItineraryRequestDto request) {
        ItineraryDao itinerary = itineraryService.updateItineraryById(id, request);
        ItineraryDto itineraryResult = new ItineraryDto(itinerary);

        ApiResponseDto<ItineraryDto> response = new ApiResponseDto<>(itineraryResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/visited/{customerId}")
    public ResponseEntity<ApiResponseDto<ItineraryDto>> updateItineraryVisitedById(@PathVariable String id, @PathVariable int customerId) {
        ItineraryDao itinerary = itineraryService.updateItineraryVisitedById(id, customerId);
        ItineraryDto itineraryResult = new ItineraryDto(itinerary);

        ApiResponseDto<ItineraryDto> response = new ApiResponseDto<>(itineraryResult, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItineraryById(@PathVariable String id) {
        ItineraryDao itineraryToDelete = itineraryService.getItineraryById(id);
        itineraryService.deleteItinerary(itineraryToDelete);

        return ResponseEntity.ok().build();
    }
}
