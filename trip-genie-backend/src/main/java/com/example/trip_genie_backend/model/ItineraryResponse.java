package com.example.trip_genie_backend.model;

import java.util.List;

public class ItineraryResponse {
    private boolean success;
    private String message;
    private List<ItineraryOption> options;
    private String generatedAt;
    
    public ItineraryResponse() {}
    
    public ItineraryResponse(boolean success, String message, List<ItineraryOption> options, String generatedAt) {
        this.success = success;
        this.message = message;
        this.options = options;
        this.generatedAt = generatedAt;
    }
    
    // Static factory methods
    public static ItineraryResponse success(List<ItineraryOption> options) {
        return new ItineraryResponse(true, "Itineraries generated successfully", options, 
                                   java.time.LocalDateTime.now().toString());
    }
    
    public static ItineraryResponse error(String message) {
        return new ItineraryResponse(false, message, null, 
                                   java.time.LocalDateTime.now().toString());
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public List<ItineraryOption> getOptions() {
        return options;
    }
    
    public void setOptions(List<ItineraryOption> options) {
        this.options = options;
    }
    
    public String getGeneratedAt() {
        return generatedAt;
    }
    
    public void setGeneratedAt(String generatedAt) {
        this.generatedAt = generatedAt;
    }
}