package com.example.trip_genie_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trip_genie_backend.model.BusInfo;
import com.example.trip_genie_backend.model.BusSearchRequest;
import com.example.trip_genie_backend.model.HotelInfo;
import com.example.trip_genie_backend.model.HotelSearchRequest;
import com.example.trip_genie_backend.model.TrainInfo;
import com.example.trip_genie_backend.model.TrainSearchRequest;
import com.example.trip_genie_backend.model.WeatherInfo;
import com.example.trip_genie_backend.model.WeatherRequest;
import com.example.trip_genie_backend.service.BusService;
import com.example.trip_genie_backend.service.HotelService;
import com.example.trip_genie_backend.service.TrainService;
import com.example.trip_genie_backend.service.WeatherService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/travel")
@CrossOrigin(origins = "*")
public class TravelController {
    
    @Autowired
    private TrainService trainService;
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private BusService busService;
    
    @Autowired
    private WeatherService weatherService;
    
    // Train APIs
    @PostMapping("/trains/search")
    public ResponseEntity<List<TrainInfo>> searchTrains(@Valid @RequestBody TrainSearchRequest request) {
        try {
            List<TrainInfo> trains = trainService.searchTrains(request);
            return ResponseEntity.ok(trains);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/trains/{trainNumber}")
    public ResponseEntity<TrainInfo> getTrainDetails(@PathVariable String trainNumber) {
        try {
            TrainInfo train = trainService.getTrainDetails(trainNumber);
            return ResponseEntity.ok(train);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Hotel APIs
    @PostMapping("/hotels/search")
    public ResponseEntity<List<HotelInfo>> searchHotels(@Valid @RequestBody HotelSearchRequest request) {
        try {
            List<HotelInfo> hotels = hotelService.searchHotels(request);
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<HotelInfo> getHotelDetails(@PathVariable String hotelId) {
        try {
            HotelInfo hotel = hotelService.getHotelDetails(hotelId);
            return ResponseEntity.ok(hotel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Bus APIs
    @PostMapping("/buses/search")
    public ResponseEntity<List<BusInfo>> searchBuses(@Valid @RequestBody BusSearchRequest request) {
        try {
            List<BusInfo> buses = busService.searchBuses(request);
            return ResponseEntity.ok(buses);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/buses/{busId}")
    public ResponseEntity<BusInfo> getBusDetails(@PathVariable String busId) {
        try {
            BusInfo bus = busService.getBusDetails(busId);
            return ResponseEntity.ok(bus);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Weather APIs
    @PostMapping("/weather/current")
    public ResponseEntity<WeatherInfo> getCurrentWeather(@Valid @RequestBody WeatherRequest request) {
        try {
            WeatherInfo weather = weatherService.getCurrentWeather(request);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/weather/forecast")
    public ResponseEntity<WeatherInfo> getWeatherForecast(@Valid @RequestBody WeatherRequest request) {
        try {
            WeatherInfo weather = weatherService.getWeatherForecast(request);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/weather/{city}")
    public ResponseEntity<WeatherInfo> getWeatherByCity(@PathVariable String city) {
        try {
            WeatherRequest request = new WeatherRequest(city);
            WeatherInfo weather = weatherService.getCurrentWeather(request);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Health check endpoints
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Travel services are running");
    }
    
    @GetMapping("/trains/health")
    public ResponseEntity<String> trainHealthCheck() {
        return ResponseEntity.ok("Train service is running");
    }
    
    @GetMapping("/hotels/health")
    public ResponseEntity<String> hotelHealthCheck() {
        return ResponseEntity.ok("Hotel service is running");
    }
    
    @GetMapping("/buses/health")
    public ResponseEntity<String> busHealthCheck() {
        return ResponseEntity.ok("Bus service is running");
    }
    
    @GetMapping("/weather/health")
    public ResponseEntity<String> weatherHealthCheck() {
        return ResponseEntity.ok("Weather service is running");
    }
}