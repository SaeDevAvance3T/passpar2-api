package fr.passpar2.api.routes.course;

import fr.passpar2.api.routes.course.dto.CoursePointDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) { this.courseRepository = courseRepository; }

    public List<CourseDao> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public List<CourseDao> getAllUserCourses(int userId) {
        return this.courseRepository.findAllByUserId(userId);
    }

    public CourseDao getCourseById(String courseId) {
        return this.courseRepository.findById(courseId).orElse(null);
    }

    public CourseDao pointIsVisited(CourseDao course, int customerId) {
        int allPointsVisited = course.getPoints().size();
        boolean pointIsVisited = false;
        for (CoursePointDto point : course.getPoints()) {
            if (point.isVisited())
                allPointsVisited--;
            else if (point.getCustomerId() == customerId && !pointIsVisited) {
                point.setVisited(true);
                pointIsVisited = true;
            }
        }

        if (allPointsVisited == 0)
            course.setFinishedAt(LocalDateTime.now());

        return this.courseRepository.save(course);
    }

    public void deleteCourse(CourseDao courseDao) {
        this.courseRepository.delete(courseDao);
    }
}
