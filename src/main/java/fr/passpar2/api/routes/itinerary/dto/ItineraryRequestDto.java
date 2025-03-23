package fr.passpar2.api.routes.itinerary.dto;

import java.util.List;

public class ItineraryRequestDto {
    private String name;
    private List<Integer> itinerary;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public List<Integer> getItinerary() { return this.itinerary; }
    public void setItinerary(List<Integer> itinerary) { this.itinerary = itinerary; }
}
