package com.example.trip_genie_backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.trip_genie_backend.model.BusInfo;
import com.example.trip_genie_backend.model.BusSearchRequest;

@Service
public class BusService {
    
    public List<BusInfo> searchBuses(BusSearchRequest request) {
        // Since free bus APIs are limited, providing mock data
        // You can integrate with RedBus API, AbhiBus API, or similar services
        // by adding proper API keys and authentication
        
        return createMockBusData(request);
    }
    
    private List<BusInfo> createMockBusData(BusSearchRequest request) {
        List<BusInfo> buses = new ArrayList<>();
        
        // Sample bus data
        String[] busOperators = {
            "Volvo Travels", 
            "SRS Travels", 
            "Orange Travels",
            "National Travels",
            "Parveen Travels"
        };
        
        String[] busTypes = {"AC Seater", "AC Sleeper", "Non-AC Seater", "Volvo AC", "Multi-Axle"};
        String[] departureTimes = {"22:30", "21:00", "23:15", "20:45", "22:00"};
        String[] arrivalTimes = {"06:30", "05:15", "07:00", "05:45", "06:15"};
        String[] durations = {"8h 0m", "8h 15m", "7h 45m", "9h 0m", "8h 15m"};
        Double[] fares = {800.0, 650.0, 450.0, 950.0, 700.0};
        Integer[] availableSeats = {12, 8, 15, 5, 10};
        Integer[] totalSeats = {40, 32, 45, 35, 38};
        
        for (int i = 0; i < busOperators.length; i++) {
            BusInfo bus = new BusInfo();
            bus.setBusId("BUS00" + (i + 1));
            bus.setBusName(busOperators[i] + " - " + busTypes[i]);
            bus.setBusOperator(busOperators[i]);
            bus.setBusType(busTypes[i]);
            bus.setDepartureTime(departureTimes[i]);
            bus.setArrivalTime(arrivalTimes[i]);
            bus.setDuration(durations[i]);
            bus.setFare(fares[i]);
            bus.setCurrency("INR");
            bus.setAvailableSeats(availableSeats[i]);
            bus.setTotalSeats(totalSeats[i]);
            bus.setFromTerminal(request.getFromCity() + " Bus Station");
            bus.setToTerminal(request.getToCity() + " Bus Terminal");
            bus.setRating(4.0 + (i * 0.1));
            bus.setCancellationPolicy("Cancellation allowed up to 2 hours before departure");
            bus.setIsAC(busTypes[i].contains("AC"));
            bus.setIsSleeper(busTypes[i].contains("Sleeper"));
            
            // Set boarding and dropping points
            List<String> boardingPoints = Arrays.asList(
                request.getFromCity() + " Bus Station - " + departureTimes[i],
                request.getFromCity() + " Railway Station - " + getTimeAfter(departureTimes[i], 15),
                request.getFromCity() + " Airport - " + getTimeAfter(departureTimes[i], 30)
            );
            bus.setBoardingPoints(boardingPoints);
            
            List<String> droppingPoints = Arrays.asList(
                request.getToCity() + " Bus Terminal - " + arrivalTimes[i],
                request.getToCity() + " Railway Station - " + getTimeAfter(arrivalTimes[i], 15),
                request.getToCity() + " City Center - " + getTimeAfter(arrivalTimes[i], 30)
            );
            bus.setDroppingPoints(droppingPoints);
            
            // Set amenities based on bus type
            List<String> amenities = createBusAmenities(busTypes[i]);
            bus.setAmenities(amenities);
            
            buses.add(bus);
        }
        
        // Filter by bus type if specified
        if (request.getBusType() != null && !request.getBusType().isEmpty()) {
            buses = buses.stream()
                    .filter(bus -> bus.getBusType().toLowerCase().contains(request.getBusType().toLowerCase()))
                    .collect(ArrayList::new, (list, bus) -> list.add(bus), (list1, list2) -> list1.addAll(list2));
        }
        
        return buses;
    }
    
    private String getTimeAfter(String time, int minutesToAdd) {
        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]) + minutesToAdd;
            
            if (minutes >= 60) {
                hours += minutes / 60;
                minutes = minutes % 60;
            }
            
            if (hours >= 24) {
                hours = hours % 24;
            }
            
            return String.format("%02d:%02d", hours, minutes);
        } catch (Exception e) {
            return time;
        }
    }
    
    private List<String> createBusAmenities(String busType) {
        List<String> amenities = new ArrayList<>();
        
        // Basic amenities for all buses
        amenities.addAll(Arrays.asList("Safe Travel", "Professional Driver", "GPS Tracking"));
        
        if (busType.contains("AC")) {
            amenities.addAll(Arrays.asList("Air Conditioning", "Comfortable Seating", "Reading Light"));
        }
        
        if (busType.contains("Sleeper")) {
            amenities.addAll(Arrays.asList("Sleeping Berths", "Blankets", "Pillows", "Privacy Curtains"));
        }
        
        if (busType.contains("Volvo") || busType.contains("Multi-Axle")) {
            amenities.addAll(Arrays.asList("Entertainment System", "USB Charging", "Extra Legroom", "Water Bottle"));
        }
        
        if (!busType.contains("Non-AC")) {
            amenities.addAll(Arrays.asList("CCTV", "Emergency Exit", "First Aid Kit"));
        }
        
        return amenities;
    }
    
    public BusInfo getBusDetails(String busId) {
        // Mock implementation for getting specific bus details
        BusSearchRequest mockRequest = new BusSearchRequest();
        mockRequest.setFromCity("Mumbai");
        mockRequest.setToCity("Pune");
        
        List<BusInfo> buses = createMockBusData(mockRequest);
        return buses.stream()
                .filter(bus -> bus.getBusId().equals(busId))
                .findFirst()
                .orElse(buses.get(0)); // Return first bus if not found
    }
}