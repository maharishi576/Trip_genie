package com.example.trip_genie_backend.controller;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_genie_backend.model.ItineraryRequest;
import com.example.trip_genie_backend.model.ItineraryResponse;
import com.example.trip_genie_backend.service.GeminiItineraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itinerary")
@CrossOrigin(origins = "*")
public class ItineraryController {
    
    @Autowired
    private GeminiItineraryService geminiItineraryService;
    
    @PostMapping("/generate")
    public ResponseEntity<ItineraryResponse> generateItinerary(
            @Valid @RequestBody ItineraryRequest request,
            BindingResult bindingResult) {
        
        // Validate request
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest()
                    .body(ItineraryResponse.error("Validation failed: " + errorMessage));
        }
        
        // Additional business logic validation
        if (request.getTripStartDate().isBefore(LocalDate.now())) {
            return ResponseEntity.badRequest()
                    .body(ItineraryResponse.error("Trip start date cannot be in the past"));
        }
        
        if (request.getTripEndDate().isBefore(request.getTripStartDate())) {
            return ResponseEntity.badRequest()
                    .body(ItineraryResponse.error("Trip end date must be after start date"));
        }
        
        try {
            ItineraryResponse response = geminiItineraryService.generateItinerary(request);
            
            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ItineraryResponse.error("Internal server error: " + e.getMessage()));
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Itinerary service is running");
    }
    
    @PostMapping("/test")
    public ResponseEntity<ItineraryResponse> testEndpoint() {
        // Create a test request for quick testing
        ItineraryRequest testRequest = new ItineraryRequest();
        testRequest.setBudget(1500.0);
        testRequest.setDestination("Paris, France");
        testRequest.setTripStartDate(LocalDate.now().plusDays(30));
        testRequest.setTripEndDate(LocalDate.now().plusDays(35));
        testRequest.setNumberOfPersons(2);
        testRequest.setTravelStyle("mid-range");
        testRequest.setInterests("culture, food, museums");
        
        ItineraryResponse response = geminiItineraryService.generateItinerary(testRequest);
        return ResponseEntity.ok(response);
    }
}