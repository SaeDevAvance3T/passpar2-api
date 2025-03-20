package fr.passpar2.api.utils;

import fr.passpar2.api.model.AddressDto;
import fr.passpar2.api.model.ItineraryPointDto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.List;

public class ItineraryManager {

    /**
     *
     * @param toVisit
     * @return
     */
    public static List<ItineraryPointDto> findItinerary(List<ItineraryPointDto> toVisit) {
        int itinerarySize = toVisit.size();

        if (itinerarySize > 3)
            return ItineraryUtils.findItineraryByStartegy(toVisit,
                    ItineraryUtils::NearestNeighbor);
        else if (itinerarySize > 6)
            return ItineraryUtils.findItineraryByStartegy(toVisit,
                    ItineraryUtils::Little);
        else
            return ItineraryUtils.findItineraryByStartegy(toVisit,
                    ItineraryUtils::BruteForce);
    }
}