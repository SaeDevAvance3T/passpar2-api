package fr.passpar2.api.controller;

import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.ItineraryDto;
import fr.passpar2.api.model.ItineraryPointDto;
import fr.passpar2.api.model.ItineraryRequestDto;
import fr.passpar2.api.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryRestController {

    private final ItineraryService itineraryService;

    public ItineraryRestController(
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
        ItineraryDto itinerary = new ItineraryDto();
        itinerary.setName(request.getName());
        itinerary.setUserId(request.getUserId());

        for (Integer customerId : request.getItinerary()) {
            ItineraryPointDto itineraryPoint = new ItineraryPointDto();
            itineraryPoint.setCustomerId(customerId);
            itineraryPoint.isNotVisited();

            itinerary.addItineraryPoint(itineraryPoint);
        }

        ItineraryDao itineraryCreated = itineraryService.saveItinerary(new ItineraryDao(itinerary));

        ApiResponseDto<ItineraryDto> response = new ApiResponseDto<>(new ItineraryDto(itineraryCreated), HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
