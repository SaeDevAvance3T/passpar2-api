package fr.passpar2.api.routes.itinerary;

import fr.passpar2.api.routes.address.AddressService;
import fr.passpar2.api.routes.customer.CustomerDao;
import fr.passpar2.api.routes.customer.CustomerService;
import fr.passpar2.api.routes.customer.dto.CustomerBaseDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryPointDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryRequestDto;
import fr.passpar2.api.routes.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final CustomerService customerService;
    private final UserService userService;

    public ItineraryService(
            ItineraryRepository itineraryRepository,
            CustomerService customerService, UserService userService) {
        this.itineraryRepository = itineraryRepository;
        this.customerService = customerService;
        this.userService = userService;
    }

    public ItineraryDao createUserItinerary(int userId, ItineraryRequestDto request) {
        ItineraryDto itinerary = new ItineraryDto();
        itinerary.setUser(userService.getUserById(userId));
        itinerary.setName(request.getName());
        for (Integer customerId : request.getItinerary()) {
            CustomerDao customerFound = customerService.getCustomerById(customerId);
            itinerary.addCustomersToVisit(customerFound);
        }
        ItineraryDao itineraryToSave = new ItineraryDao(itinerary);
        return saveItinerary(itineraryToSave);
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
            itinerary.setItinerary(request.getItinerary());
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
