package com.example.trip_genie_backend.model;

public class CurrentWeather {
    private Double temperature;
    private Double feelsLike;
    private String description;
    private String icon;
    private Integer humidity;
    private Double pressure;
    private Double windSpeed;
    private Integer windDirection;
    private Integer visibility;
    private Integer uvIndex;
    private Integer cloudiness;
    private String sunrise;
    private String sunset;
    
    public CurrentWeather() {}
    
    // Getters and Setters
    public Double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    
    public Double getFeelsLike() {
        return feelsLike;
    }
    
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public Integer getHumidity() {
        return humidity;
    }
    
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
    
    public Double getPressure() {
        return pressure;
    }
    
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
    
    public Double getWindSpeed() {
        return windSpeed;
    }
    
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    public Integer getWindDirection() {
        return windDirection;
    }
    
    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }
    
    public Integer getVisibility() {
        return visibility;
    }
    
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }
    
    public Integer getUvIndex() {
        return uvIndex;
    }
    
    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }
    
    public Integer getCloudiness() {
        return cloudiness;
    }
    
    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }
    
    public String getSunrise() {
        return sunrise;
    }
    
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    
    public String getSunset() {
        return sunset;
    }
    
    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}