package fr.passpar2.api.routes.course.dto;

public class CoursePoint {

    private int customerId;
    private CoursePointCoordinates coordinates;
    private boolean isVisited;
    private boolean isPast;

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {this.customerId = customerId;}

    public CoursePointCoordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(CoursePointCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isVisited() {return isVisited;}
    public void setVisited(boolean visited) {isVisited = visited;}

    public boolean isPast() {return isPast;}
    public void setPast(boolean past) {isPast = past;}
}
