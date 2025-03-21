package fr.passpar2.api.address;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "pp2_address")
public class AddressDao {
    @Id
    private String id;
    @Field("id_customer")
    private int customerId;
    @Field("id_user")
    private int userId;
    private String country;
    private String street;
    private String city;
    @Field("postal_code")
    private Integer postalCode;
    private String supplement;

    public AddressDao() {}

    public AddressDao(AddressDto model) {
        this.id = model.getId();
        this.customerId = model.getCustomerId();
        this.userId = model.getUserId();
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

    public int getUserId() { return this.userId; }
    public void setUserId(int userId) { this.userId = userId; }

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