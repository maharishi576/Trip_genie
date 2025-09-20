package com.example.trip_genie_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = {"http://localhost:19006", "http://localhost:3000"})
public class TripController {

    // Demo data for trip destinations
    private static final List<Map<String, Object>> DEMO_DESTINATIONS = Arrays.asList(
        createDestination("paris", "Paris, France", "The City of Light", "paris.jpg", Arrays.asList("Eiffel Tower", "Louvre Museum", "Notre-Dame")),
        createDestination("tokyo", "Tokyo, Japan", "Modern metropolis meets tradition", "tokyo.jpg", Arrays.asList("Senso-ji Temple", "Tokyo Tower", "Shibuya Crossing")),
        createDestination("newyork", "New York, USA", "The Big Apple", "newyork.jpg", Arrays.asList("Central Park", "Statue of Liberty", "Times Square")),
        createDestination("london", "London, UK", "Royal heritage and modern culture", "london.jpg", Arrays.asList("Big Ben", "Tower Bridge", "British Museum")),
        createDestination("bali", "Bali, Indonesia", "Tropical paradise", "bali.jpg", Arrays.asList("Ubud Rice Terraces", "Tanah Lot Temple", "Mount Batur"))
    );

    private static Map<String, Object> createDestination(String id, String name, String description, String image, List<String> attractions) {
        Map<String, Object> destination = new HashMap<>();
        destination.put("id", id);
        destination.put("name", name);
        destination.put("description", description);
        destination.put("image", image);
        destination.put("attractions", attractions);
        destination.put("rating", 4.0 + Math.random()); // Random rating between 4.0-5.0
        return destination;
    }

    @GetMapping("/destinations")
    public ResponseEntity<Map<String, Object>> getDestinations() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("destinations", DEMO_DESTINATIONS);
        response.put("total", DEMO_DESTINATIONS.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/destinations/{id}")
    public ResponseEntity<Map<String, Object>> getDestination(@PathVariable String id) {
        Optional<Map<String, Object>> destination = DEMO_DESTINATIONS.stream()
            .filter(d -> d.get("id").equals(id))
            .findFirst();
        
        if (destination.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("destination", destination.get());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Destination not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/plan")
    public ResponseEntity<Map<String, Object>> planTrip(@RequestBody Map<String, Object> tripRequest) {
        // Demo trip planning logic
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Trip planned successfully!");
        
        Map<String, Object> tripPlan = new HashMap<>();
        tripPlan.put("id", "trip_" + System.currentTimeMillis());
        tripPlan.put("destination", tripRequest.get("destination"));
        tripPlan.put("startDate", tripRequest.get("startDate"));
        tripPlan.put("endDate", tripRequest.get("endDate"));
        tripPlan.put("travelers", tripRequest.get("travelers"));
        tripPlan.put("budget", tripRequest.get("budget"));
        tripPlan.put("status", "planned");
        tripPlan.put("createdAt", new Date());
        
        response.put("tripPlan", tripPlan);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Trip Genie Backend");
        response.put("timestamp", new Date());
        return ResponseEntity.ok(response);
    }
}