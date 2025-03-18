package fr.passpar2.api.service;

import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.model.*;
import fr.passpar2.api.repository.IItineraryRepository;
import fr.passpar2.api.utils.ItineraryManager;
import fr.passpar2.api.utils.ItineraryUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    private final AddressService addressService;
    private final IItineraryRepository itineraryRepository;

    public ItineraryService(
            AddressService addressService,
            IItineraryRepository itineraryRepository
    ) {
        this.addressService = addressService;
        this.itineraryRepository = itineraryRepository;
    }

    public ItineraryDao createItinerary(ItineraryRequestDto itineraryRequest) {
        ItineraryDto itinerary = new ItineraryDto();
        itinerary.setName(itineraryRequest.getName());
        itinerary.setUserId(itineraryRequest.getUserId());

        for (Integer customerId : itineraryRequest.getItinerary()) {
            ItineraryPointDto itineraryPoint = new ItineraryPointDto();
            itineraryPoint.setCustomerId(customerId);

            AddressDto address = new AddressDto(addressService.getAddressByCustomerId(customerId));
            itineraryPoint.setAddress(address);

            double[] coordinates = ItineraryUtils.getCoordinatesFromAddress(address);
            itineraryPoint.setLatitude(coordinates[0]);
            itineraryPoint.setLongitude(coordinates[1]);

            itineraryPoint.isNotVisited();

            itinerary.addItineraryPoint(itineraryPoint);
        }

        return saveItinerary(new ItineraryDao(itinerary));
    }

    public ItineraryDao saveItinerary(ItineraryDao itinerary) {
        return itineraryRepository.save(itinerary);
    }

    public List<ItineraryDao> getAllItineraries() {
        return this.itineraryRepository.findAll();
    }

    public ItineraryDao getItineraryById(String id) {
        Optional<ItineraryDao> itineraryOptional = itineraryRepository.findById(id);

        return itineraryOptional.orElseThrow(() ->
                new RuntimeException("Itineraire introuvable")
        );
    }

    public ItineraryDao updateItineraryById(String id, ItineraryRequestDto request) {
        return itineraryRepository.findById(id).map(itinerary -> {
            itinerary.setName(request.getName());
            itinerary.setUserId(request.getUserId());
            itinerary.setItinerary(new ArrayList<>());
            for (Integer customerId : request.getItinerary()) {
                ItineraryPointDto itineraryPoint = new ItineraryPointDto();
                itineraryPoint.setCustomerId(customerId);
                itineraryPoint.isNotVisited();

                itinerary.addItineraryPoint(itineraryPoint);
            }
            return itineraryRepository.save(itinerary);
        }).orElseThrow(() -> new RuntimeException("Itineraire introuvable"));
    }

    public void deleteItinerary(ItineraryDao itinerary) {
        itineraryRepository.delete(itinerary);
    }

    public List<ItineraryDao> getAllItinerariesByUserId(int id) {
        return this.itineraryRepository.findByUserId(id);
    }

}
