package fr.passpar2.api.controller;

import fr.passpar2.api.entity.AddressDao;
import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.model.ItineraryDto;
import fr.passpar2.api.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
