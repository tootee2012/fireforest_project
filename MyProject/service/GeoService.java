package com.example.MyProject.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
//
@Service
public class GeoService {

        private static final String API_KEY = "AIzaSyCE-KaxViCGvCOeh04r4S01EAW3Yj6JKw8";

        @SuppressWarnings("unchecked")
        public double[] getLatLngFromAddress(String address) {
            try {
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                        + address.replace(" ", "+") + "&key=" + API_KEY;

                RestTemplate restTemplate = new RestTemplate();
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

                if (results != null && !results.isEmpty()) {
                    Map<String, Object> geometry = (Map<String, Object>) results.get(0).get("geometry");
                    Map<String, Object> location = (Map<String, Object>) geometry.get("location");

                    double lat = ((Number) location.get("lat")).doubleValue();
                    double lng = ((Number) location.get("lng")).doubleValue();

                    return new double[]{lat, lng};
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null; // ถ้าหาไม่ได้
        }
    }

//    public double[] getLatLngFromAddress(String address) {
//        try {
//            String url = "https://nominatim.openstreetmap.org/search?q="
//                    + address.replace(" ", "+")
//                    + "&format=json&limit=1";
//
//            RestTemplate restTemplate = new RestTemplate();
//            String response = restTemplate.getForObject(url, String.class);
//
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response);
//
//            if (root.isArray() && root.size() > 0) {
//                double lat = root.get(0).get("lat").asDouble();
//                double lon = root.get(0).get("lon").asDouble();
//                return new double[]{lat, lon};
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
