package fr.passpar2.api.model;

import java.util.List;

public class ItineraryRequestDto {
    private String name;
    private int userId;
    private List<Integer> itinerary;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public List<Integer> getItinerary() { return this.itinerary; }
    public void setItinerary(List<Integer> itinerary) { this.itinerary = itinerary; }
}
