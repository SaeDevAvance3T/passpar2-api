package fr.passpar2.api.entity;

import fr.passpar2.api.model.ItineraryDto;
import fr.passpar2.api.model.ItineraryPointDto;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "pp2_itinerary")
public class ItineraryDao {
    @Id
    private String id;
    @Field("id_user")
    private int userId;
    private String name;
    private List<ItineraryPointDto> itinerary;

    public ItineraryDto() { }

    public ItineraryDao(ItineraryDto model) {
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
