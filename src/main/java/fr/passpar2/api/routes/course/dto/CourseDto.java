package fr.passpar2.api.routes.course.dto;

import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.routes.course.CourseDao;

import java.util.ArrayList;
import java.util.List;

public class CourseDto extends CourseBaseDto implements IFullResponse<CourseBaseDto> {

    private List<CoursePoint> points = new ArrayList<CoursePoint>();

    public CourseDto() { super(); }

    public CourseDto(CourseDao model) {
        super(model);
        this.points.addAll(model.getPoints());
    }

    public List<CoursePoint> getPoints() {
        return points;
    }

    public void setPoints(List<CoursePoint> points) {
        this.points = points;
    }

    public void addPoints(CoursePoint point) {
        this.points.add(point);
    }
}
