package fr.passpar2.api.utils;

import fr.passpar2.api.routes.course.dto.CoursePoint;

import java.util.List;

public class CourseManager {

    public static List<CoursePoint> findCourse(List<CoursePoint> toVisit) {
        int itinerarySize = toVisit.size();

        if (itinerarySize > 6)
            return CourseUtils.findItineraryByStartegy(toVisit,
                    CourseUtils::Little);
        else if (itinerarySize > 3)
            return CourseUtils.findItineraryByStartegy(toVisit,
                    CourseUtils::NearestNeighbor);
        else
            return CourseUtils.findItineraryByStartegy(toVisit,
                    CourseUtils::BruteForce);
    }

}
