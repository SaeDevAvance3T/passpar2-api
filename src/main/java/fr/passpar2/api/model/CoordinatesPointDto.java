package fr.passpar2.api.model;

import fr.passpar2.api.routes.address.dto.AddressBaseDto;

public class CoordinatesPointDto {
    private String type = "point";
    private AddressBaseDto address;
    private double latitude;
    private double longitude;

    public CoordinatesPointDto() { }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public AddressBaseDto getAddress() { return this.address; }
    public void setAddress(AddressBaseDto address) { this.address = address; }

    public double getLatitude() { return this.latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return this.longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
