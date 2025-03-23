package fr.passpar2.api.routes.course.dto;

public class CoursePointDto {

    private int customerId;

    private CoursePointCoordinatesDto coordinates;

    private boolean isVisited;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public CoursePointCoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoursePointCoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
