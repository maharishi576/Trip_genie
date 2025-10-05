package com.example.trip_genie_backend.model;

public class DayItinerary {
    private int day;
    private String date;
    private String activities;
    private String accommodation;
    private String meals;
    private Double estimatedCost;
    
    public DayItinerary() {}
    
    public DayItinerary(int day, String date, String activities, String accommodation, 
                       String meals, Double estimatedCost) {
        this.day = day;
        this.date = date;
        this.activities = activities;
        this.accommodation = accommodation;
        this.meals = meals;
        this.estimatedCost = estimatedCost;
    }
    
    // Getters and Setters
    public int getDay() {
        return day;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getActivities() {
        return activities;
    }
    
    public void setActivities(String activities) {
        this.activities = activities;
    }
    
    public String getAccommodation() {
        return accommodation;
    }
    
    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }
    
    public String getMeals() {
        return meals;
    }
    
    public void setMeals(String meals) {
        this.meals = meals;
    }
    
    public Double getEstimatedCost() {
        return estimatedCost;
    }
    
    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
}