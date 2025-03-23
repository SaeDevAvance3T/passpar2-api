package fr.passpar2.api.routes.course.dto;

import fr.passpar2.api.response.IFullResponse;
import fr.passpar2.api.routes.course.CourseDao;

import java.util.ArrayList;
import java.util.List;

public class CourseDto extends CourseBaseDto implements IFullResponse<CourseBaseDto> {

    private List<CoursePointDto> points = new ArrayList<CoursePointDto>();

    public CourseDto() { super(); }

    public CourseDto(CourseDao model) {
        super(model);
        this.points.addAll(model.getPoints());
    }

    public List<CoursePointDto> getPoints() {
        return points;
    }

    public void setPoints(List<CoursePointDto> points) {
        this.points = points;
    }
}
