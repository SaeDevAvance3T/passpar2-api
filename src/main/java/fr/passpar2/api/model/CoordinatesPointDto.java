package fr.passpar2.api.model;

public class CoordinatesPointDto {
    private String type;
    private double latitude;
    private double longitude;

    public CoordinatesPointDto() { }

    public String getType() { return this.type; }
    public void setType(String type) { this.type = type; }

    public double getLatitude() { return this.latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return this.longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
