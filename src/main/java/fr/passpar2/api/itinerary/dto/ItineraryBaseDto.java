package fr.passpar2.api.itinerary.dto;

import fr.passpar2.api.itinerary.ItineraryDao;
import fr.passpar2.api.response.IBaseResponse;

public class ItineraryBaseDto implements IBaseResponse<ItineraryBaseDto> {

    private String id;
    private String name;

    public ItineraryBaseDto() { }

    public ItineraryBaseDto(ItineraryDao model) {
        this.setId(model.getId());
        this.setName(model.getName());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
}
