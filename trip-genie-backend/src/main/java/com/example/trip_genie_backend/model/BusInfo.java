package com.example.trip_genie_backend.model;

import java.util.List;

public class BusInfo {
    private String busId;
    private String busName;
    private String busOperator;
    private String busType;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private Double fare;
    private String currency;
    private Integer availableSeats;
    private Integer totalSeats;
    private String fromTerminal;
    private String toTerminal;
    private List<String> boardingPoints;
    private List<String> droppingPoints;
    private List<String> amenities;
    private Double rating;
    private String cancellationPolicy;
    private Boolean isAC;
    private Boolean isSleeper;
    
    public BusInfo() {}
    
    // Getters and Setters
    public String getBusId() {
        return busId;
    }
    
    public void setBusId(String busId) {
        this.busId = busId;
    }
    
    public String getBusName() {
        return busName;
    }
    
    public void setBusName(String busName) {
        this.busName = busName;
    }
    
    public String getBusOperator() {
        return busOperator;
    }
    
    public void setBusOperator(String busOperator) {
        this.busOperator = busOperator;
    }
    
    public String getBusType() {
        return busType;
    }
    
    public void setBusType(String busType) {
        this.busType = busType;
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
    
    public Double getFare() {
        return fare;
    }
    
    public void setFare(Double fare) {
        this.fare = fare;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Integer getAvailableSeats() {
        return availableSeats;
    }
    
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
    
    public Integer getTotalSeats() {
        return totalSeats;
    }
    
    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
    
    public String getFromTerminal() {
        return fromTerminal;
    }
    
    public void setFromTerminal(String fromTerminal) {
        this.fromTerminal = fromTerminal;
    }
    
    public String getToTerminal() {
        return toTerminal;
    }
    
    public void setToTerminal(String toTerminal) {
        this.toTerminal = toTerminal;
    }
    
    public List<String> getBoardingPoints() {
        return boardingPoints;
    }
    
    public void setBoardingPoints(List<String> boardingPoints) {
        this.boardingPoints = boardingPoints;
    }
    
    public List<String> getDroppingPoints() {
        return droppingPoints;
    }
    
    public void setDroppingPoints(List<String> droppingPoints) {
        this.droppingPoints = droppingPoints;
    }
    
    public List<String> getAmenities() {
        return amenities;
    }
    
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
    
    public Double getRating() {
        return rating;
    }
    
    public void setRating(Double rating) {
        this.rating = rating;
    }
    
    public String getCancellationPolicy() {
        return cancellationPolicy;
    }
    
    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }
    
    public Boolean getIsAC() {
        return isAC;
    }
    
    public void setIsAC(Boolean isAC) {
        this.isAC = isAC;
    }
    
    public Boolean getIsSleeper() {
        return isSleeper;
    }
    
    public void setIsSleeper(Boolean isSleeper) {
        this.isSleeper = isSleeper;
    }
}