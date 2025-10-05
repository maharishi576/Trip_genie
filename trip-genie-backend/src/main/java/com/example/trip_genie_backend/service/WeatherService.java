package com.example.trip_genie_backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.trip_genie_backend.model.CurrentWeather;
import com.example.trip_genie_backend.model.DailyForecast;
import com.example.trip_genie_backend.model.WeatherInfo;
import com.example.trip_genie_backend.model.WeatherRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
    
    @Value("${openweather.api.key}")
    private String openWeatherApiKey;
    
    @Value("${openweather.api.url:https://api.openweathermap.org/data/2.5}")
    private String openWeatherApiUrl;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    public WeatherService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }
    
    public WeatherInfo getCurrentWeather(WeatherRequest request) {
        try {
            String url = buildWeatherUrl(request, "weather");
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                return parseCurrentWeatherResponse(response.getBody());
            } else {
                return createFallbackWeather(request.getCity());
            }
        } catch (Exception e) {
            return createFallbackWeather(request.getCity());
        }
    }
    
    public WeatherInfo getWeatherForecast(WeatherRequest request) {
        try {
            // Get current weather
            WeatherInfo weatherInfo = getCurrentWeather(request);
            
            // Get 5-day forecast
            String forecastUrl = buildWeatherUrl(request, "forecast");
            ResponseEntity<String> forecastResponse = restTemplate.getForEntity(forecastUrl, String.class);
            
            if (forecastResponse.getStatusCode().is2xxSuccessful()) {
                List<DailyForecast> forecasts = parseForecastResponse(forecastResponse.getBody());
                weatherInfo.setForecast(forecasts);
            }
            
            return weatherInfo;
        } catch (Exception e) {
            return createFallbackWeather(request.getCity());
        }
    }
    
    private String buildWeatherUrl(WeatherRequest request, String endpoint) {
        StringBuilder url = new StringBuilder();
        url.append(openWeatherApiUrl).append("/").append(endpoint);
        url.append("?q=").append(request.getCity());
        
        if (request.getCountry() != null && !request.getCountry().isEmpty()) {
            url.append(",").append(request.getCountry());
        }
        
        url.append("&appid=").append(openWeatherApiKey);
        url.append("&units=").append(request.getUnits());
        url.append("&lang=").append(request.getLanguage());
        
        return url.toString();
    }
    
    private WeatherInfo parseCurrentWeatherResponse(String responseBody) throws Exception {
        JsonNode root = objectMapper.readTree(responseBody);
        
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCityName(root.path("name").asText());
        weatherInfo.setCountry(root.path("sys").path("country").asText());
        weatherInfo.setLatitude(root.path("coord").path("lat").asDouble());
        weatherInfo.setLongitude(root.path("coord").path("lon").asDouble());
        weatherInfo.setTimezone(root.path("timezone").asText());
        weatherInfo.setTimestamp(root.path("dt").asLong());
        
        // Parse current weather
        CurrentWeather current = new CurrentWeather();
        JsonNode main = root.path("main");
        JsonNode weather = root.path("weather").get(0);
        JsonNode wind = root.path("wind");
        JsonNode sys = root.path("sys");
        
        current.setTemperature(main.path("temp").asDouble());
        current.setFeelsLike(main.path("feels_like").asDouble());
        current.setHumidity(main.path("humidity").asInt());
        current.setPressure(main.path("pressure").asDouble());
        current.setDescription(weather.path("description").asText());
        current.setIcon(weather.path("icon").asText());
        current.setWindSpeed(wind.path("speed").asDouble());
        current.setWindDirection(wind.path("deg").asInt());
        current.setCloudiness(root.path("clouds").path("all").asInt());
        current.setVisibility(root.path("visibility").asInt());
        
        // Convert timestamps to readable format
        long sunriseTimestamp = sys.path("sunrise").asLong();
        long sunsetTimestamp = sys.path("sunset").asLong();
        current.setSunrise(new Date(sunriseTimestamp * 1000).toString());
        current.setSunset(new Date(sunsetTimestamp * 1000).toString());
        
        weatherInfo.setCurrent(current);
        
        return weatherInfo;
    }
    
    private List<DailyForecast> parseForecastResponse(String responseBody) throws Exception {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode list = root.path("list");
        
        List<DailyForecast> forecasts = new ArrayList<>();
        
        for (JsonNode item : list) {
            DailyForecast forecast = new DailyForecast();
            
            JsonNode main = item.path("main");
            JsonNode weather = item.path("weather").get(0);
            JsonNode wind = item.path("wind");
            
            forecast.setDate(item.path("dt_txt").asText());
            forecast.setMaxTemp(main.path("temp_max").asDouble());
            forecast.setMinTemp(main.path("temp_min").asDouble());
            forecast.setDescription(weather.path("description").asText());
            forecast.setIcon(weather.path("icon").asText());
            forecast.setHumidity(main.path("humidity").asInt());
            forecast.setWindSpeed(wind.path("speed").asDouble());
            forecast.setCloudiness(item.path("clouds").path("all").asInt());
            
            // Precipitation (if available)
            if (item.has("rain")) {
                forecast.setPrecipitation(item.path("rain").path("3h").asDouble());
            } else if (item.has("snow")) {
                forecast.setPrecipitation(item.path("snow").path("3h").asDouble());
            } else {
                forecast.setPrecipitation(0.0);
            }
            
            forecasts.add(forecast);
        }
        
        return forecasts;
    }
    
    private WeatherInfo createFallbackWeather(String cityName) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCityName(cityName);
        weatherInfo.setCountry("Unknown");
        weatherInfo.setTimestamp(System.currentTimeMillis() / 1000);
        
        CurrentWeather current = new CurrentWeather();
        current.setTemperature(25.0);
        current.setFeelsLike(27.0);
        current.setDescription("Weather data temporarily unavailable");
        current.setHumidity(60);
        current.setPressure(1013.0);
        current.setWindSpeed(5.0);
        current.setCloudiness(30);
        
        weatherInfo.setCurrent(current);
        
        // Create basic forecast
        List<DailyForecast> forecasts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            DailyForecast forecast = new DailyForecast();
            forecast.setDate("Day " + i);
            forecast.setMaxTemp(25.0 + i);
            forecast.setMinTemp(20.0 + i);
            forecast.setDescription("Forecast unavailable");
            forecast.setHumidity(60);
            forecast.setWindSpeed(5.0);
            forecast.setPrecipitation(0.0);
            forecasts.add(forecast);
        }
        weatherInfo.setForecast(forecasts);
        
        return weatherInfo;
    }
}