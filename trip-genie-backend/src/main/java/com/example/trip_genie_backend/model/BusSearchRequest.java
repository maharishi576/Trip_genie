package com.example.trip_genie_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class BusSearchRequest {
    
    @NotBlank(message = "From city is required")
    private String fromCity;
    
    @NotBlank(message = "To city is required")
    private String toCity;
    
    @NotNull(message = "Journey date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journeyDate;
    
    private String busType; // AC, Non-AC, Sleeper, Seater
    private String boardingPoint;
    private String droppingPoint;
    
    public BusSearchRequest() {}
    
    // Getters and Setters
    public String getFromCity() {
        return fromCity;
    }
    
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
    
    public String getToCity() {
        return toCity;
    }
    
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
    
    public LocalDate getJourneyDate() {
        return journeyDate;
    }
    
    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }
    
    public String getBusType() {
        return busType;
    }
    
    public void setBusType(String busType) {
        this.busType = busType;
    }
    
    public String getBoardingPoint() {
        return boardingPoint;
    }
    
    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }
    
    public String getDroppingPoint() {
        return droppingPoint;
    }
    
    public void setDroppingPoint(String droppingPoint) {
        this.droppingPoint = droppingPoint;
    }
}