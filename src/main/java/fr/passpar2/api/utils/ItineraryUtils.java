package fr.passpar2.api.utils;

import fr.passpar2.api.address.AddressDto;
import fr.passpar2.api.itinerary.ItineraryPointDto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ItineraryUtils {

    public static List<ItineraryPointDto> findItineraryByStartegy(
            List<ItineraryPointDto> toVisit,
            Function<List<List<Double>>, List<Integer>> strategy)
    {
        List<List<Double>> distanceMatrix  = buildDistanceMatrix(toVisit);
        List<Integer> itineraryIndexes = strategy.apply(distanceMatrix);
        List<ItineraryPointDto> itineraryAddresses = buildItineraryFromIndexes(toVisit, itineraryIndexes);
        return itineraryAddresses;
    }

    public static List<Integer> NearestNeighbor(List<List<Double>> distanceMatrix) {
        int itinerarySize = distanceMatrix.size();
        boolean[] visited = new boolean[itinerarySize];
        List<Integer> itinerary = new ArrayList<>();

        itinerary.add(0);
        visited[0] = true;

        for (int i = 0; i < itinerarySize - 1; i++) {
            int last = itinerary.get(itinerary.size() - 1);
            int nearestAddress = -1;
            double minDistance = Double.MAX_VALUE;

            for (int address = 0; address < itinerarySize; address++) {
                if (!visited[address] && distanceMatrix.get(last).get(address) < minDistance) {
                    nearestAddress = address;
                    minDistance = distanceMatrix.get(last).get(address);
                }
            }

            if (nearestAddress != -1) {
                itinerary.add(nearestAddress);
                visited[nearestAddress] = true;
            }
        }

        itinerary.add(0);
        return itinerary;
    }

    public static List<Integer> Little(List<List<Double>> distanceMatrix) {
        int n = distanceMatrix.size();
        boolean[] visited = new boolean[n];
        List<Integer> itinerary = new ArrayList<>();

        itinerary.add(0);
        visited[0] = true;

        while (itinerary.size() < n) {
            int last = itinerary.get(itinerary.size() - 1);
            int nextCity = -1;
            double minCost = Double.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!visited[i] && distanceMatrix.get(last).get(i) < minCost) {
                    minCost = distanceMatrix.get(last).get(i);
                    nextCity = i;
                }
            }

            if (nextCity != -1) {
                itinerary.add(nextCity);
                visited[nextCity] = true;
            }
        }

        itinerary.add(0);
        return itinerary;
    }

    private static List<List<Integer>> generatePermutations(List<Integer> list) {
        List<List<Integer>> permutations = new ArrayList<>();
        generatePermutationsHelper(list, 0, permutations);
        return permutations;
    }

    private static void generatePermutationsHelper(List<Integer> list, int start, List<List<Integer>> permutations) {
        if (start == list.size() - 1) {
            permutations.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            generatePermutationsHelper(list, start + 1, permutations);
            Collections.swap(list, start, i);
        }
    }

    public static List<Integer> BruteForce(List<List<Double>> distanceMatrix) {
        int n = distanceMatrix.size();
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(i);
        }

        List<List<Integer>> permutations = generatePermutations(cities);
        double minDistance = Double.MAX_VALUE;
        List<Integer> bestItinerary = null;

        for (List<Integer> permutation : permutations) {
            double distance = 0;
            for (int i = 0; i < permutation.size() - 1; i++) {
                distance += distanceMatrix.get(permutation.get(i)).get(permutation.get(i + 1));
            }
            distance += distanceMatrix.get(permutation.get(permutation.size() - 1)).get(permutation.get(0));

            if (distance < minDistance) {
                minDistance = distance;
                bestItinerary = new ArrayList<>(permutation);
            }
        }

        bestItinerary.add(bestItinerary.get(0));
        return bestItinerary;
    }

    private static List<List<Double>> buildDistanceMatrix(List<ItineraryPointDto> toVisit) {
        List<List<Double>> distanceMatrix = new ArrayList<>();

        for(ItineraryPointDto addressOrigin: toVisit) {
            List<Double> distances = new ArrayList<>();

            for(ItineraryPointDto addressDestination: toVisit) {
                if (addressOrigin == addressDestination)
                    distances.add(0.0);
                else
                    distances.add(distanceBetweenAddresses(addressOrigin.getAddress(), addressDestination.getAddress()));
            }

            distanceMatrix.add(distances);
        }

        return distanceMatrix;
    }

    public static double distanceBetweenAddresses(AddressDto origin, AddressDto destination) {
        try {
            final String API_KEY = "vhfbL74u1cDYXZ74zNFXD2jtl86oW3q1m4Z6XtuEGKfTPYqmJ8oon0cTuYGkkOht";
            final String API_URL = "https://api.distancematrix.ai/maps/api/distancematrix/json";

            String encodedOrigin = URLEncoder.encode(origin.getFullAddress(), "UTF-8");
            String encodedDestination = URLEncoder.encode(destination.getFullAddress(), "UTF-8");

            String urlString
                    = API_URL + "?origins=" + encodedOrigin + "&destinations=" + encodedDestination + "&key=" + API_KEY;
            URL url = new URL(urlString);
            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache.iut-rodez.fr", 8080));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (!jsonResponse.getString("status").equals("OK")) {
                throw new RuntimeException("Erreur API: " + jsonResponse.getString("status"));
            }

            JSONArray rows = jsonResponse.getJSONArray("rows");
            if (rows.length() > 0) {
                JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");
                if (elements.length() > 0 && elements.getJSONObject(0).getString("status").equals("OK")) {
                    return elements.getJSONObject(0).getJSONObject("distance").getDouble("value") / 1000.0;
                }
            }

            return -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static List<ItineraryPointDto> buildItineraryFromIndexes(List<ItineraryPointDto> toVisit, List<Integer> itinerary) {
        List<ItineraryPointDto> itineraryAddress = new ArrayList<>();
        for(Integer i: itinerary) {
            itineraryAddress.add(toVisit.get(i));
        }
        return itineraryAddress;
    }

    public static double[] getCoordinatesFromAddress(AddressDto address) {
        try {
            final String API_KEY = "vhfbL74u1cDYXZ74zNFXD2jtl86oW3q1m4Z6XtuEGKfTPYqmJ8oon0cTuYGkkOht";
            final String API_URL = "https://api.distancematrix.ai/maps/api/geocode/json";

            String encodedAddress = URLEncoder.encode(address.getFullAddress(), "UTF-8");
            String urlString = API_URL + "?address=" + encodedAddress + "&key=" + API_KEY;

            URL url = new URL(urlString);
            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("cache.iut-rodez.fr", 8080));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (!jsonResponse.getString("status").equals("OK")) {
                throw new RuntimeException("Erreur API: " + jsonResponse.getString("status"));
            }

            JSONArray results = jsonResponse.getJSONArray("result");
            if (results.length() > 0) {
                JSONObject geometry = results.getJSONObject(0).getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");

                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");

                return new double[]{lat, lng};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[]{-1, -1};
    }
}
