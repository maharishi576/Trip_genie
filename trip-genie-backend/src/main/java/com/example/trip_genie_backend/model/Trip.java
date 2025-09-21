package com.example.trip_genie_backend.model;

public class Trip {
    private Long id;
    private String title;
    private String destination;
    private String description;
    private String startDate;
    private String endDate;
    private Double estimatedCost;
    
    // Default constructor
    public Trip() {}
    
    // Constructor with parameters
    public Trip(Long id, String title, String destination, String description, 
                String startDate, String endDate, Double estimatedCost) {
        this.id = id;
        this.title = title;
        this.destination = destination;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedCost = estimatedCost;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    
    public Double getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(Double estimatedCost) { this.estimatedCost = estimatedCost; }
}