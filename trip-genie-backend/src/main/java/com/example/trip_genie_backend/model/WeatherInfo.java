package com.example.trip_genie_backend.model;

import java.util.List;

public class WeatherInfo {
    private String cityName;
    private String country;
    private Double latitude;
    private Double longitude;
    private CurrentWeather current;
    private List<DailyForecast> forecast;
    private String timezone;
    private Long timestamp;
    
    public WeatherInfo() {}
    
    // Getters and Setters
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public CurrentWeather getCurrent() {
        return current;
    }
    
    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }
    
    public List<DailyForecast> getForecast() {
        return forecast;
    }
    
    public void setForecast(List<DailyForecast> forecast) {
        this.forecast = forecast;
    }
    
    public String getTimezone() {
        return timezone;
    }
    
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}