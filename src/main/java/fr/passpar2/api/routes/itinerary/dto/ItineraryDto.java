package fr.passpar2.api.routes.itinerary.dto;

import fr.passpar2.api.routes.customer.CustomerDao;
import fr.passpar2.api.routes.customer.dto.CustomerBaseDto;
import fr.passpar2.api.routes.itinerary.ItineraryDao;
import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.routes.user.UserDao;
import fr.passpar2.api.routes.user.dto.UserBaseDto;

import java.util.ArrayList;
import java.util.List;

public class ItineraryDto extends ItineraryBaseDto implements IFullResponse<ItineraryBaseDto> {

    private UserBaseDto user;
    private List<CustomerBaseDto> customersToVisit = new ArrayList<>();

    public ItineraryDto() { super(); }

    public ItineraryDto(ItineraryDao model) { super(model); }

    public UserBaseDto getUser() { return this.user; }
    public void setUser(UserBaseDto user) { this.user = user; }
    public void setUser(UserDao user) { this.setUser(new UserBaseDto(user)); }

    public List<CustomerBaseDto> getCustomersToVisit() { return this.customersToVisit; }
    public void setCustomersToVisit(List<CustomerBaseDto> customersToVisit) { this.customersToVisit = customersToVisit; }
    public void addCustomersToVisit(CustomerBaseDto customer) { this.customersToVisit.add(customer); }
    public void addCustomersToVisit(CustomerDao customer) { this.addCustomersToVisit(new CustomerBaseDto(customer)); }
    public void removeCustomersToVisit(CustomerBaseDto customer) { this.customersToVisit.remove(customer); }
    public void removeCustomersToVisit(CustomerDao customer) { this.removeCustomersToVisit(new CustomerBaseDto(customer)); }

}
