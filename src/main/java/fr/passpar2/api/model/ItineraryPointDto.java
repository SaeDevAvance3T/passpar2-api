package fr.passpar2.api.model;

public class ItineraryPointDto extends CoordinatesPointDto {
    private int customerId;
    private boolean visited;

    public ItineraryPointDto() { }

    public int getCustomerId() { return this.customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public boolean getVisited() { return this.visited; }
    public void isVisited() { this.visited = true; }
    public void isNotVisited() { this.visited = false; }
}
