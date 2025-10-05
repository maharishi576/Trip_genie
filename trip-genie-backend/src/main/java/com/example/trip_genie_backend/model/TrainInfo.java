package com.example.trip_genie_backend.model;

import java.util.List;

public class TrainInfo {
    private String trainNumber;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String fromStationCode;
    private String toStationCode;
    private List<TrainClass> availableClasses;
    private String runningDays;
    
    public TrainInfo() {}
    
    // Getters and Setters
    public String getTrainNumber() {
        return trainNumber;
    }
    
    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }
    
    public String getTrainName() {
        return trainName;
    }
    
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getFromStationCode() {
        return fromStationCode;
    }
    
    public void setFromStationCode(String fromStationCode) {
        this.fromStationCode = fromStationCode;
    }
    
    public String getToStationCode() {
        return toStationCode;
    }
    
    public void setToStationCode(String toStationCode) {
        this.toStationCode = toStationCode;
    }
    
    public List<TrainClass> getAvailableClasses() {
        return availableClasses;
    }
    
    public void setAvailableClasses(List<TrainClass> availableClasses) {
        this.availableClasses = availableClasses;
    }
    
    public String getRunningDays() {
        return runningDays;
    }
    
    public void setRunningDays(String runningDays) {
        this.runningDays = runningDays;
    }
}