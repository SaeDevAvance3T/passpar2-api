package fr.passpar2.api.routes.course;

import fr.passpar2.api.routes.course.dto.CourseDto;
import fr.passpar2.api.routes.course.dto.CoursePoint;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "pp2_course")
public class CourseDao {
    @Id
    private String id;

    @Field("id_user")
    private int userId;

    @Field("id_itinerary")
    private String itineraryId;

    @Field("name_itinerary")
    private String itineraryName;

    private List<CoursePoint> points;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("finished_at")
    private LocalDateTime finishedAt;

    public CourseDao() {}

    public CourseDao(CourseDto model) {
        this.id = model.getId();
        this.userId = model.getUserId();
        this.itineraryId = model.getItineraryId();
        this.itineraryName = model.getItineraryName();
        this.points = model.getPoints();
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

    public List<CoursePoint> getPoints() {
        return points;
    }

    public void setPoints(List<CoursePoint> points) {
        this.points = points;
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
