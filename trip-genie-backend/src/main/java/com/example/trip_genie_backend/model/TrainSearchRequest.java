package com.example.trip_genie_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TrainSearchRequest {
    
    @NotBlank(message = "From station is required")
    private String fromStation;
    
    @NotBlank(message = "To station is required")
    private String toStation;
    
    @NotNull(message = "Journey date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate journeyDate;
    
    private String trainClass; // SL, 3A, 2A, 1A, CC, etc.
    private String quota; // GN, TQ, etc.
    
    // Constructors
    public TrainSearchRequest() {}
    
    public TrainSearchRequest(String fromStation, String toStation, LocalDate journeyDate) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.journeyDate = journeyDate;
    }
    
    // Getters and Setters
    public String getFromStation() {
        return fromStation;
    }
    
    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }
    
    public String getToStation() {
        return toStation;
    }
    
    public void setToStation(String toStation) {
        this.toStation = toStation;
    }
    
    public LocalDate getJourneyDate() {
        return journeyDate;
    }
    
    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }
    
    public String getTrainClass() {
        return trainClass;
    }
    
    public void setTrainClass(String trainClass) {
        this.trainClass = trainClass;
    }
    
    public String getQuota() {
        return quota;
    }
    
    public void setQuota(String quota) {
        this.quota = quota;
    }
}