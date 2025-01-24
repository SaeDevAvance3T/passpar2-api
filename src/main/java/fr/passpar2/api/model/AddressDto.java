package fr.passpar2.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.passpar2.api.entity.AddressDao;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AddressDto {
    private String id;
    private int idCustomer;
    private String street;
    private String city;
    private int postalCode;

    public AddressDto() { }

    public AddressDto(AddressDao model) {
        this.id = model.getId();
        this.idCustomer = model.getCustomerId();
        this.street = model.getStreet();
        this.city = model.getCity();
        this.postalCode = model.getPostalCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

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
}
