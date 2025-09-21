package com.example.trip_genie_backend.controller;

import com.example.trip_genie_backend.model.Trip;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

@RestController
@RequestMapping("/api/trips")
@CrossOrigin(origins = "*") // Allow React Native app to access
public class TripController {
    
    private final List<Trip> trips = new ArrayList<>();
    private Long nextId = 1L;
    
    // Initialize with some sample data
    public TripController() {
        trips.add(new Trip(nextId++, "Tokyo Adventure", "Tokyo, Japan", 
                          "Explore the vibrant culture and technology of Tokyo", 
                          "2024-03-15", "2024-03-22", 2500.0));
        trips.add(new Trip(nextId++, "European Backpacking", "Multiple Cities, Europe", 
                          "Backpack through major European capitals", 
                          "2024-06-01", "2024-06-30", 3500.0));
        trips.add(new Trip(nextId++, "Bali Retreat", "Bali, Indonesia", 
                          "Relax and rejuvenate in tropical paradise", 
                          "2024-08-10", "2024-08-20", 1800.0));
    }
    
    @GetMapping
    public List<Trip> getAllTrips() {
        return trips;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        return trips.stream()
                .filter(trip -> trip.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        trip.setId(nextId++);
        trips.add(trip);
        return trip;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip updatedTrip) {
        for (int i = 0; i < trips.size(); i++) {
            if (trips.get(i).getId().equals(id)) {
                updatedTrip.setId(id);
                trips.set(i, updatedTrip);
                return ResponseEntity.ok(updatedTrip);
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        boolean removed = trips.removeIf(trip -> trip.getId().equals(id));
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/search")
    public List<Trip> searchTrips(@RequestParam(required = false) String destination,
                                 @RequestParam(required = false) Double maxCost) {
        return trips.stream()
                .filter(trip -> destination == null || 
                        trip.getDestination().toLowerCase().contains(destination.toLowerCase()))
                .filter(trip -> maxCost == null || trip.getEstimatedCost() <= maxCost)
                .toList();
    }
}