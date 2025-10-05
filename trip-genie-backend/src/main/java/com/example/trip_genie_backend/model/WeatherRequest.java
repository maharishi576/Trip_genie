package com.example.trip_genie_backend.model;

import jakarta.validation.constraints.NotBlank;

public class WeatherRequest {
    
    @NotBlank(message = "City name is required")
    private String city;
    
    private String country; // Optional country code
    private String units; // metric, imperial, kelvin (default: metric)
    private String language; // Language for weather description
    
    public WeatherRequest() {
        this.units = "metric";
        this.language = "en";
    }
    
    public WeatherRequest(String city) {
        this.city = city;
        this.units = "metric";
        this.language = "en";
    }
    
    // Getters and Setters
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getUnits() {
        return units;
    }
    
    public void setUnits(String units) {
        this.units = units;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
}