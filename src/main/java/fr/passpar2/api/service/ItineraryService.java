package fr.passpar2.api.service;

import fr.passpar2.api.entity.CustomerDao;
import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.entity.UserDao;
import fr.passpar2.api.repository.IItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItineraryService {

    private final IItineraryRepository itineraryRepository;

    public ItineraryService(
            IItineraryRepository itineraryRepository
    ) {
        this.itineraryRepository = itineraryRepository;
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

    public void deleteItinerary(ItineraryDao itinerary) {
        itineraryRepository.delete(itinerary);
    }

    public List<ItineraryDao> getAllItinerariesByUserId(int id) {
        return this.itineraryRepository.findByUserId(id);
    }

}
