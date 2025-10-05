package com.example.trip_genie_backend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HotelSearchRequest {
    
    @NotBlank(message = "Destination is required")
    private String destination;
    
    @NotNull(message = "Check-in date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;
    
    @NotNull(message = "Check-out date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
    
    @NotNull(message = "Number of adults is required")
    @Min(value = 1, message = "At least 1 adult is required")
    private Integer adults;
    
    private Integer children;
    private Integer rooms;
    private String currency;
    private Double maxPrice;
    private Double minPrice;
    private String starRating; // 1,2,3,4,5
    private String amenities; // wifi,pool,gym,etc
    
    // Constructors
    public HotelSearchRequest() {
        this.children = 0;
        this.rooms = 1;
        this.currency = "USD";
    }
    
    // Getters and Setters
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    public Integer getAdults() {
        return adults;
    }
    
    public void setAdults(Integer adults) {
        this.adults = adults;
    }
    
    public Integer getChildren() {
        return children;
    }
    
    public void setChildren(Integer children) {
        this.children = children;
    }
    
    public Integer getRooms() {
        return rooms;
    }
    
    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Double getMaxPrice() {
        return maxPrice;
    }
    
    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public Double getMinPrice() {
        return minPrice;
    }
    
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
    
    public String getStarRating() {
        return starRating;
    }
    
    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }
    
    public String getAmenities() {
        return amenities;
    }
    
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
}