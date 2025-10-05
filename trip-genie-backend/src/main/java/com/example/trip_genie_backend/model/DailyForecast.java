package com.example.trip_genie_backend.model;

public class DailyForecast {
    private String date;
    private Double maxTemp;
    private Double minTemp;
    private String description;
    private String icon;
    private Integer humidity;
    private Double windSpeed;
    private Double precipitation;
    private Integer cloudiness;
    
    public DailyForecast() {}
    
    // Getters and Setters
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public Double getMaxTemp() {
        return maxTemp;
    }
    
    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }
    
    public Double getMinTemp() {
        return minTemp;
    }
    
    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
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
    
    public Double getWindSpeed() {
        return windSpeed;
    }
    
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    public Double getPrecipitation() {
        return precipitation;
    }
    
    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }
    
    public Integer getCloudiness() {
        return cloudiness;
    }
    
    public void setCloudiness(Integer cloudiness) {
        this.cloudiness = cloudiness;
    }
}