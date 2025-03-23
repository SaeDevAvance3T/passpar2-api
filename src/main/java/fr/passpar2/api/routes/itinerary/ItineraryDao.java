package fr.passpar2.api.routes.itinerary;

import fr.passpar2.api.routes.customer.dto.CustomerBaseDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryDto;
import fr.passpar2.api.routes.itinerary.dto.ItineraryPointDto;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "pp2_itinerary")
public class ItineraryDao {
    @Id
    private String id;
    @Field("id_user")
    private int userId;
    private String name;
    private List<Integer> itinerary = new ArrayList<Integer>();

    public ItineraryDao() { }

    public ItineraryDao(ItineraryDto model) {
        this.id = model.getId();
        this.userId = model.getUser().getId();
        this.name = model.getName();
        for (CustomerBaseDto customer : model.getCustomersToVisit()) {
            this.itinerary.add(customer.getId());
        }
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public List<Integer> getItinerary() { return this.itinerary; }
    public Integer getItinerary(int index) { return this.itinerary.get(index); }
    public void setItinerary(List<Integer> itinerary) { this.itinerary = itinerary; }
    public void addItinerary(Integer itineraryPoint) { this.itinerary.add(itineraryPoint); }
}
