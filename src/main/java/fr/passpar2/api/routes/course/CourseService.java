package fr.passpar2.api.routes.course;

import fr.passpar2.api.routes.address.AddressDao;
import fr.passpar2.api.routes.address.AddressService;
import fr.passpar2.api.routes.address.dto.AddressDto;
import fr.passpar2.api.routes.course.dto.CourseDto;
import fr.passpar2.api.routes.course.dto.CoursePointCoordinates;
import fr.passpar2.api.routes.course.dto.CoursePoint;
import fr.passpar2.api.routes.itinerary.ItineraryDao;
import fr.passpar2.api.routes.itinerary.ItineraryService;
import fr.passpar2.api.utils.CourseManager;
import fr.passpar2.api.utils.CourseUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ItineraryService itineraryService;
    private final AddressService addressService;

    public CourseService(CourseRepository courseRepository, ItineraryService itineraryService, AddressService addressService) { this.courseRepository = courseRepository;
        this.itineraryService = itineraryService;
        this.addressService = addressService;
    }

    public List<CourseDao> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public List<CourseDao> getAllUserCourses(int userId) {
        return this.courseRepository.findAllByUserId(userId);
    }

    public CourseDao getCourseById(String courseId) {
        return this.courseRepository.findById(courseId).orElse(null);
    }

    public CourseDao pointIsVisited(CourseDao course, Integer customerId) {
        int allPointsVisited = course.getPoints().size();
        boolean pointIsVisited = false;
        for (CoursePoint point : course.getPoints()) {
            if (point.isVisited())
                allPointsVisited--;
            else if (point.getCustomerId() == customerId && !pointIsVisited) {
                point.setVisited(true);
                pointIsVisited = true;
                allPointsVisited--;
            }
        }

        if (allPointsVisited == 0)
            course.setFinishedAt(LocalDateTime.now());

        return this.courseRepository.save(course);
    }

    public void deleteCourse(CourseDao courseDao) {
        this.courseRepository.delete(courseDao);
    }

    public CourseDao createCourse(String itineraryId) {
        ItineraryDao itineraryFound = itineraryService.getItineraryById(itineraryId);

        CourseDto course = new CourseDto();
        course.setUserId(itineraryFound.getUserId());
        course.setItineraryId(itineraryId);
        course.setItineraryName(itineraryFound.getName());
        course.setCreatedAt(LocalDateTime.now());

        AddressDao userAddressFound = addressService.getAddressByUserId(itineraryFound.getUserId());
        course.addPoints(createCoursePoint(userAddressFound, null));

        for (Integer customerId : itineraryFound.getItinerary()) {
            AddressDao customerAddressFound = addressService.getAddressByCustomerId(customerId);
            course.addPoints(createCoursePoint(customerAddressFound, customerId));
        }

        List<CoursePoint> bestCourse = CourseManager.findCourse(course.getPoints());
        course.setPoints(bestCourse);

        return saveCourse(new CourseDao(course));
    }

    private CoursePoint createCoursePoint(AddressDao addressDao, Integer customerId) {
        AddressDto addressDto = new AddressDto(addressDao);
        double[] coordinates = CourseUtils.getCoordinatesFromAddress(addressDto);

        CoursePointCoordinates pointCoordinates = new CoursePointCoordinates();
        pointCoordinates.setType("point");
        pointCoordinates.setLatitude(coordinates[0]);
        pointCoordinates.setLongitude(coordinates[1]);

        CoursePoint point = new CoursePoint();
        point.setCoordinates(pointCoordinates);
        point.setVisited(false);

        if (customerId != null) {
            point.setCustomerId(customerId);
        }

        return point;
    }

    public CourseDao saveCourse(CourseDao courseDao) {
        return this.courseRepository.save(courseDao);
    }
}
