package fr.passpar2.api.routes.course.dto;

import fr.passpar2.api.response.IBaseResponse;
import fr.passpar2.api.routes.course.CourseDao;

import java.time.LocalDateTime;
import java.util.List;

public class CourseBaseDto implements IBaseResponse<CourseBaseDto> {

    private String id;
    private int userId;
    private String itineraryId;
    private String itineraryName;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public CourseBaseDto() { }

    public CourseBaseDto(CourseDao model) {
        this.id = model.getId();
        this.userId = model.getUserId();
        this.itineraryId = model.getItineraryId();
        this.itineraryName = model.getItineraryName();
        this.createdAt = model.getCreatedAt();
        this.finishedAt = model.getFinishedAt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(String itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
