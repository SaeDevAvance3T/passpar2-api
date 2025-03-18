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
    public static List<AddressDto> findItinerary(List<ItineraryPointDto> toVisit) {
        int itinerarySize = toVisit.size();

        if (itinerarySize > 12)
            return ItineraryUtils.findItineraryByStartegy(toVisit,
                    ItineraryUtils::NearestNeighbor);
        else
            return ItineraryUtils.findItineraryByStartegy(toVisit,
                    ItineraryUtils::BruteForce);
    }
}