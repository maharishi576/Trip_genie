package com.example.trip_genie_backend.model;

import java.util.List;

public class RoomType {
    private String roomType;
    private String roomDescription;
    private Double pricePerNight;
    private Integer availableRooms;
    private List<String> amenities;
    
    public RoomType() {}
    
    // Getters and Setters
    public String getRoomType() {
        return roomType;
    }
    
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
    
    public String getRoomDescription() {
        return roomDescription;
    }
    
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    
    public Double getPricePerNight() {
        return pricePerNight;
    }
    
    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    public Integer getAvailableRooms() {
        return availableRooms;
    }
    
    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
    }
    
    public List<String> getAmenities() {
        return amenities;
    }
    
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}