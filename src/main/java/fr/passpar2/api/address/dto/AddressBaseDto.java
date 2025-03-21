package fr.passpar2.api.address.dto;

import fr.passpar2.api.address.AddressDao;
import fr.passpar2.api.response.IBaseResponse;

public class AddressBaseDto implements IBaseResponse<AddressBaseDto> {

    private String id;
    private String country;
    private String street;
    private String city;
    private Integer postalCode;
    private String supplement;

    public AddressBaseDto() { }

    public AddressBaseDto(AddressDao model) {
        this.setId(model.getId());
        this.setCountry(model.getCountry());
        this.setStreet(model.getStreet());
        this.setCity(model.getCity());
        this.setPostalCode(model.getPostalCode());
        this.setSupplement(model.getSupplement());
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public Integer getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getSupplement() { return this.supplement; }
    public void setSupplement(String supplement) { this.supplement = supplement; }

}
