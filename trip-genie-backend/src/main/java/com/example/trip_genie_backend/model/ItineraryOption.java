package com.example.trip_genie_backend.model;

import java.util.List;

public class ItineraryOption {
    private String title;
    private String description;
    private Double totalCost;
    private String travelStyle;
    private List<DayItinerary> dailyItinerary;
    private String transportation;
    private String notes;
    private List<String> highlights;
    
    public ItineraryOption() {}
    
    public ItineraryOption(String title, String description, Double totalCost, 
                          String travelStyle, List<DayItinerary> dailyItinerary,
                          String transportation, String notes, List<String> highlights) {
        this.title = title;
        this.description = description;
        this.totalCost = totalCost;
        this.travelStyle = travelStyle;
        this.dailyItinerary = dailyItinerary;
        this.transportation = transportation;
        this.notes = notes;
        this.highlights = highlights;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getTotalCost() {
        return totalCost;
    }
    
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    
    public String getTravelStyle() {
        return travelStyle;
    }
    
    public void setTravelStyle(String travelStyle) {
        this.travelStyle = travelStyle;
    }
    
    public List<DayItinerary> getDailyItinerary() {
        return dailyItinerary;
    }
    
    public void setDailyItinerary(List<DayItinerary> dailyItinerary) {
        this.dailyItinerary = dailyItinerary;
    }
    
    public String getTransportation() {
        return transportation;
    }
    
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public List<String> getHighlights() {
        return highlights;
    }
    
    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }
}