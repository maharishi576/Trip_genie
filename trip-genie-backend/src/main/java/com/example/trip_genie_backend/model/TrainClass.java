package com.example.trip_genie_backend.model;

public class TrainClass {
    private String className;
    private String classCode;
    private Double fare;
    private String availability;
    private String waitingList;
    
    public TrainClass() {}
    
    // Getters and Setters
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getClassCode() {
        return classCode;
    }
    
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    
    public Double getFare() {
        return fare;
    }
    
    public void setFare(Double fare) {
        this.fare = fare;
    }
    
    public String getAvailability() {
        return availability;
    }
    
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    
    public String getWaitingList() {
        return waitingList;
    }
    
    public void setWaitingList(String waitingList) {
        this.waitingList = waitingList;
    }
}