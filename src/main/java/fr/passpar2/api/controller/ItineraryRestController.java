package fr.passpar2.api.controller;

import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.model.ApiResponseDto;
import fr.passpar2.api.service.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ApiResponseDto<List<ItineraryDao>>> getAllItineraries(){
        ApiResponseDto<List<ItineraryDao>> response = new ApiResponseDto<>(this.itineraryService.getAllItineraries(), HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
