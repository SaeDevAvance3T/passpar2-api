package fr.passpar2.api.entity;

import fr.passpar2.api.model.AddressDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pp2_address")
public class AddressDao {
    @Id
    private String id;
    @Field("id_customer")
    private int customerId;
    private String country;
    private String street;
    private String city;
    @Field("postal_code")
    private int postalCode;
    private String supplement;

    public AddressDao(AddressDto model) {
        this.id = model.getId();
        this.customerId = model.getCustomerId();
        this.country = model.getCountry();
        this.street = model.getStreet();
        this.city = model.getCity();
        this.postalCode = model.getPostalCode();
        this.supplement = model.getSupplement();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCountry() { return this.country; }
    public void setCountry(String country) { this.country = country; }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getSupplement() { return this.supplement; }
    public void setSupplement(String supplement) { this.supplement = supplement; }
}