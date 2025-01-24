package fr.passpar2.api.model;

public class ItineraryPointDto extends CoordinatesPointDto {
    private boolean visited;

    public ItineraryPointDto() { }

    public boolean getVisited() { return this.visited; }
    public void isVisited() { this.visited = true; }
    public void isNotVisited() { this.visited = false; }
}
