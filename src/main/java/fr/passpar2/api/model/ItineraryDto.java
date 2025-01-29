package fr.passpar2.api.model;

import fr.passpar2.api.entity.ItineraryDao;

import java.util.List;

public class ItineraryDto {
    private String id;
    private int userId;
    private String name;
    private List<ItineraryPointDto> itinerary;

    public ItineraryDto(ItineraryDao model) {
        this.id = model.getId();
        this.userId = model.getUserId();
        this.name = model.getName();
        this.itinerary = model.getItinerary();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public List<ItineraryPointDto> getItinerary() { return this.itinerary; }
    public ItineraryPointDto getItineraryPoint(int index) { return this.itinerary.get(index); }
    public void setItinerary(List<ItineraryPointDto> itinerary) { this.itinerary = itinerary; }
    public void addItineraryPoint(ItineraryPointDto itineraryPoint) { this.itinerary.add(itineraryPoint); }
}
