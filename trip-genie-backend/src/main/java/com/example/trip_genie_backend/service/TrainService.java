package com.example.trip_genie_backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.trip_genie_backend.model.TrainClass;
import com.example.trip_genie_backend.model.TrainInfo;
import com.example.trip_genie_backend.model.TrainSearchRequest;

@Service
public class TrainService {
    
    public List<TrainInfo> searchTrains(TrainSearchRequest request) {
        // Since free Indian Railway APIs are limited and often require API keys,
        // I'm providing a mock service that returns sample data
        // You can integrate with RailwayAPI.com or similar services by adding API keys
        
        return createMockTrainData(request);
    }
    
    private List<TrainInfo> createMockTrainData(TrainSearchRequest request) {
        List<TrainInfo> trains = new ArrayList<>();
        
        // Sample train data
        String[] trainNumbers = {"12951", "12307", "12615", "16031", "22691"};
        String[] trainNames = {
            "Mumbai Rajdhani Express",
            "Howrah Jodhpur Express", 
            "Grand Trunk Express",
            "Andaman Express",
            "Rajdhani Express"
        };
        String[] departureTimes = {"15:55", "22:50", "13:30", "06:20", "17:35"};
        String[] arrivalTimes = {"08:35", "16:20", "05:10", "11:45", "09:50"};
        String[] durations = {"16h 40m", "17h 30m", "15h 40m", "5h 25m", "16h 15m"};
        
        for (int i = 0; i < trainNumbers.length; i++) {
            TrainInfo train = new TrainInfo();
            train.setTrainNumber(trainNumbers[i]);
            train.setTrainName(trainNames[i]);
            train.setDepartureTime(departureTimes[i]);
            train.setArrivalTime(arrivalTimes[i]);
            train.setDuration(durations[i]);
            train.setFromStationCode(getStationCode(request.getFromStation()));
            train.setToStationCode(getStationCode(request.getToStation()));
            train.setRunningDays("Daily");
            
            // Create sample class information
            List<TrainClass> classes = createSampleClasses();
            train.setAvailableClasses(classes);
            
            trains.add(train);
        }
        
        return trains;
    }
    
    private List<TrainClass> createSampleClasses() {
        List<TrainClass> classes = new ArrayList<>();
        
        // Sleeper Class
        TrainClass sl = new TrainClass();
        sl.setClassCode("SL");
        sl.setClassName("Sleeper");
        sl.setFare(450.0);
        sl.setAvailability("Available-15");
        sl.setWaitingList("WL/5");
        classes.add(sl);
        
        // Third AC
        TrainClass ac3 = new TrainClass();
        ac3.setClassCode("3A");
        ac3.setClassName("Third AC");
        ac3.setFare(1200.0);
        ac3.setAvailability("Available-8");
        ac3.setWaitingList("WL/2");
        classes.add(ac3);
        
        // Second AC
        TrainClass ac2 = new TrainClass();
        ac2.setClassCode("2A");
        ac2.setClassName("Second AC");
        ac2.setFare(1800.0);
        ac2.setAvailability("Available-3");
        ac2.setWaitingList("WL/8");
        classes.add(ac2);
        
        // First AC
        TrainClass ac1 = new TrainClass();
        ac1.setClassCode("1A");
        ac1.setClassName("First AC");
        ac1.setFare(3200.0);
        ac1.setAvailability("Available-1");
        ac1.setWaitingList("WL/15");
        classes.add(ac1);
        
        return classes;
    }
    
    private String getStationCode(String stationName) {
        // Simple mapping for common stations
        Map<String, String> stationCodes = new HashMap<>();
        stationCodes.put("Mumbai", "CSMT");
        stationCodes.put("Delhi", "NDLS");
        stationCodes.put("Bangalore", "SBC");
        stationCodes.put("Chennai", "MAS");
        stationCodes.put("Kolkata", "HWH");
        stationCodes.put("Hyderabad", "SC");
        stationCodes.put("Pune", "PUNE");
        stationCodes.put("Ahmedabad", "ADI");
        stationCodes.put("Jaipur", "JP");
        stationCodes.put("Lucknow", "LKO");
        
        return stationCodes.getOrDefault(stationName.toLowerCase(), 
                stationName.substring(0, Math.min(4, stationName.length())).toUpperCase());
    }
    
    public TrainInfo getTrainDetails(String trainNumber) {
        // Mock implementation for getting specific train details
        TrainSearchRequest mockRequest = new TrainSearchRequest();
        mockRequest.setFromStation("Mumbai");
        mockRequest.setToStation("Delhi");
        
        List<TrainInfo> trains = createMockTrainData(mockRequest);
        return trains.stream()
                .filter(train -> train.getTrainNumber().equals(trainNumber))
                .findFirst()
                .orElse(trains.get(0)); // Return first train if not found
    }
}