package fr.passpar2.api.service;

import fr.passpar2.api.entity.ItineraryDao;
import fr.passpar2.api.repository.IItineraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryService {

    private final IItineraryRepository itineraryRepository;

    public ItineraryService(
            IItineraryRepository itineraryRepository
    ) {
        this.itineraryRepository = itineraryRepository;
    }

    public List<ItineraryDao> getAllItineraries() {
        return this.itineraryRepository.findAll();
    }

    public List<ItineraryDao> getAllItinerariesByUserId(int id) {
        return this.itineraryRepository.findByUserId(id);
    }

}
