package fr.passpar2.api.routes.course;

import fr.passpar2.api.response.ApiResponse;
import fr.passpar2.api.routes.contact.ContactDao;
import fr.passpar2.api.routes.course.dto.CourseBaseDto;
import fr.passpar2.api.routes.course.dto.CourseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) { this.courseService = courseService; }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<CourseBaseDto>>> getAllCourses(){
        List<CourseDao> coursesFound = courseService.getAllCourses();
        List<CourseBaseDto> courses = new ArrayList<>();

        for (CourseDao itinerary: coursesFound) {
            courses.add(new CourseBaseDto(itinerary));
        }

        ApiResponse<List<CourseBaseDto>> response = new ApiResponse<>(courses, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CourseBaseDto>>> getAllUserCourses(@PathVariable int userId){
        List<CourseDao> coursesFound = courseService.getAllUserCourses(userId);
        List<CourseBaseDto> courses = new ArrayList<>();

        for (CourseDao itinerary: coursesFound) {
            courses.add(new CourseBaseDto(itinerary));
        }

        ApiResponse<List<CourseBaseDto>> response = new ApiResponse<>(courses, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/start/{itineraryId}")
    public ResponseEntity<ApiResponse<CourseDto>> startCourse(@PathVariable String itineraryId){
        CourseDao courseCreated = courseService.createCourse(itineraryId);
        CourseDto course = new CourseDto(courseCreated);

        ApiResponse<CourseDto> response = new ApiResponse<>(course, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseById(@PathVariable String id){
        CourseDao courseFound = courseService.getCourseById(id);
        CourseDto course = new CourseDto(courseFound);

        ApiResponse<CourseDto> response = new ApiResponse<>(course, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/points/{customerId}/visited")
    public ResponseEntity<ApiResponse<CourseDto>> updateCoursePointAsVisited(@PathVariable String id, @PathVariable int customerId) {
        CourseDao courseFound = courseService.getCourseById(id);
        if (courseFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        CourseDao courseUpdated = courseService.pointIsVisited(courseFound, customerId);
        CourseDto course = new CourseDto(courseUpdated);

        ApiResponse<CourseDto> response = new ApiResponse<>(course, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> deleteCourse(@PathVariable String id) {
        CourseDao courseFound = courseService.getCourseById(id);
        if (courseFound == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        courseService.deleteCourse(courseFound);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
