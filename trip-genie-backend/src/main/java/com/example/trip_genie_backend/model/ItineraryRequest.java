package com.example.trip_genie_backend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItineraryRequest {
    
    @NotNull(message = "Budget is required")
    @Min(value = 100, message = "Budget must be at least $100")
    private Double budget;
    
    @NotBlank(message = "Destination is required")
    private String destination;
    
    @NotNull(message = "Trip start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tripStartDate;
    
    @NotNull(message = "Trip end date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tripEndDate;
    
    @NotNull(message = "Number of persons is required")
    @Min(value = 1, message = "Number of persons must be at least 1")
    @Max(value = 20, message = "Number of persons cannot exceed 20")
    private Integer numberOfPersons;
    
    // Optional fields for more personalized recommendations
    private String travelStyle; // luxury, budget, mid-range
    private String interests; // culture, adventure, relaxation, food, etc.
    private String accommodation; // hotel, hostel, airbnb, etc.
    
    // Constructors
    public ItineraryRequest() {}
    
    public ItineraryRequest(Double budget, String destination, LocalDate tripStartDate, 
                           LocalDate tripEndDate, Integer numberOfPersons) {
        this.budget = budget;
        this.destination = destination;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.numberOfPersons = numberOfPersons;
    }
    
    // Getters and Setters
    public Double getBudget() {
        return budget;
    }
    
    public void setBudget(Double budget) {
        this.budget = budget;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public LocalDate getTripStartDate() {
        return tripStartDate;
    }
    
    public void setTripStartDate(LocalDate tripStartDate) {
        this.tripStartDate = tripStartDate;
    }
    
    public LocalDate getTripEndDate() {
        return tripEndDate;
    }
    
    public void setTripEndDate(LocalDate tripEndDate) {
        this.tripEndDate = tripEndDate;
    }
    
    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }
    
    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
    
    public String getTravelStyle() {
        return travelStyle;
    }
    
    public void setTravelStyle(String travelStyle) {
        this.travelStyle = travelStyle;
    }
    
    public String getInterests() {
        return interests;
    }
    
    public void setInterests(String interests) {
        this.interests = interests;
    }
    
    public String getAccommodation() {
        return accommodation;
    }
    
    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }
    
    @Override
    public String toString() {
        return "ItineraryRequest{" +
                "budget=" + budget +
                ", destination='" + destination + '\'' +
                ", tripStartDate=" + tripStartDate +
                ", tripEndDate=" + tripEndDate +
                ", numberOfPersons=" + numberOfPersons +
                ", travelStyle='" + travelStyle + '\'' +
                ", interests='" + interests + '\'' +
                ", accommodation='" + accommodation + '\'' +
                '}';
    }
}