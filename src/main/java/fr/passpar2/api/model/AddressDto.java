package fr.passpar2.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.passpar2.api.entity.AddressDao;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddressDto {
    private String id;
    private int customerId;
    private String country;
    private String street;
    private String city;
    private int postalCode;
    private String supplement;

    public AddressDto() { }

    public AddressDto(AddressDao model) {
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

    public void setCustomerId(int idCustomer) {
        this.customerId = idCustomer;
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
