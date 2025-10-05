package com.example.trip_genie_backend.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.trip_genie_backend.model.HotelInfo;
import com.example.trip_genie_backend.model.HotelSearchRequest;
import com.example.trip_genie_backend.model.RoomType;

@Service
public class HotelService {
    
    public List<HotelInfo> searchHotels(HotelSearchRequest request) {
        // Since free hotel APIs are limited, providing mock data
        // You can integrate with Booking.com API, Hotels.com API, or Amadeus API
        // by adding proper API keys and authentication
        
        return createMockHotelData(request);
    }
    
    private List<HotelInfo> createMockHotelData(HotelSearchRequest request) {
        List<HotelInfo> hotels = new ArrayList<>();
        
        long nights = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        
        // Sample hotel data based on destination
        String[] hotelNames = {
            "Grand Palace Hotel", 
            "Comfort Inn & Suites", 
            "Budget Stay Lodge",
            "Luxury Resort & Spa",
            "City Center Hotel"
        };
        
        String[] descriptions = {
            "Luxurious hotel in the heart of the city with world-class amenities",
            "Comfortable accommodation with modern facilities and great service", 
            "Budget-friendly hotel offering clean rooms and basic amenities",
            "Premium resort with spa, pool, and premium dining options",
            "Conveniently located hotel perfect for business and leisure travelers"
        };
        
        Double[] starRatings = {5.0, 4.0, 3.0, 5.0, 4.0};
        Double[] baseRates = {200.0, 120.0, 60.0, 350.0, 150.0};
        
        for (int i = 0; i < hotelNames.length; i++) {
            HotelInfo hotel = new HotelInfo();
            hotel.setHotelId("HTL00" + (i + 1));
            hotel.setHotelName(hotelNames[i]);
            hotel.setDescription(descriptions[i]);
            hotel.setCity(request.getDestination());
            hotel.setCountry("India"); // Default to India for now
            hotel.setAddress("123 Main Street, " + request.getDestination());
            hotel.setStarRating(starRatings[i]);
            hotel.setPricePerNight(baseRates[i]);
            hotel.setTotalPrice(baseRates[i] * nights * request.getRooms());
            hotel.setCurrency(request.getCurrency());
            hotel.setRating(4.0 + (i * 0.2));
            hotel.setReviewCount(150 + (i * 50));
            hotel.setPhoneNumber("+91-" + (9000000000L + i));
            hotel.setEmail("info@" + hotelNames[i].toLowerCase().replace(" ", "") + ".com");
            hotel.setWebsite("www." + hotelNames[i].toLowerCase().replace(" ", "") + ".com");
            
            // Mock coordinates (around center of major Indian cities)
            hotel.setLatitude(19.0760 + (i * 0.01));
            hotel.setLongitude(72.8777 + (i * 0.01));
            
            // Set amenities
            List<String> amenities = createHotelAmenities(starRatings[i]);
            hotel.setAmenities(amenities);
            
            // Set images
            List<String> images = Arrays.asList(
                "https://example.com/hotel" + (i+1) + "_1.jpg",
                "https://example.com/hotel" + (i+1) + "_2.jpg",
                "https://example.com/hotel" + (i+1) + "_3.jpg"
            );
            hotel.setImages(images);
            
            // Set available rooms
            List<RoomType> roomTypes = createRoomTypes(baseRates[i]);
            hotel.setAvailableRooms(roomTypes);
            
            hotels.add(hotel);
        }
        
        // Filter by price range if specified
        if (request.getMinPrice() != null || request.getMaxPrice() != null) {
            hotels = hotels.stream()
                    .filter(hotel -> {
                        double price = hotel.getPricePerNight();
                        boolean withinMin = request.getMinPrice() == null || price >= request.getMinPrice();
                        boolean withinMax = request.getMaxPrice() == null || price <= request.getMaxPrice();
                        return withinMin && withinMax;
                    })
                    .collect(ArrayList::new, (list, hotel) -> list.add(hotel), (list1, list2) -> list1.addAll(list2));
        }
        
        return hotels;
    }
    
    private List<String> createHotelAmenities(Double starRating) {
        List<String> amenities = new ArrayList<>();
        
        // Basic amenities for all hotels
        amenities.addAll(Arrays.asList("Free WiFi", "24/7 Front Desk", "Room Service"));
        
        if (starRating >= 3.0) {
            amenities.addAll(Arrays.asList("Air Conditioning", "TV", "Private Bathroom"));
        }
        
        if (starRating >= 4.0) {
            amenities.addAll(Arrays.asList("Swimming Pool", "Fitness Center", "Restaurant", "Laundry Service"));
        }
        
        if (starRating >= 5.0) {
            amenities.addAll(Arrays.asList("Spa", "Concierge", "Valet Parking", "Business Center", "Conference Rooms"));
        }
        
        return amenities;
    }
    
    private List<RoomType> createRoomTypes(Double baseRate) {
        List<RoomType> roomTypes = new ArrayList<>();
        
        // Standard Room
        RoomType standard = new RoomType();
        standard.setRoomType("Standard Room");
        standard.setRoomDescription("Comfortable room with basic amenities");
        standard.setPricePerNight(baseRate);
        standard.setAvailableRooms(5);
        standard.setAmenities(Arrays.asList("Free WiFi", "AC", "TV", "Private Bathroom"));
        roomTypes.add(standard);
        
        // Deluxe Room
        RoomType deluxe = new RoomType();
        deluxe.setRoomType("Deluxe Room");
        deluxe.setRoomDescription("Spacious room with premium amenities");
        deluxe.setPricePerNight(baseRate * 1.3);
        deluxe.setAvailableRooms(3);
        deluxe.setAmenities(Arrays.asList("Free WiFi", "AC", "Smart TV", "Mini Bar", "City View"));
        roomTypes.add(deluxe);
        
        // Suite
        if (baseRate > 150.0) {
            RoomType suite = new RoomType();
            suite.setRoomType("Executive Suite");
            suite.setRoomDescription("Luxury suite with separate living area");
            suite.setPricePerNight(baseRate * 2.0);
            suite.setAvailableRooms(1);
            suite.setAmenities(Arrays.asList("Free WiFi", "AC", "Smart TV", "Mini Bar", "Living Room", "Balcony"));
            roomTypes.add(suite);
        }
        
        return roomTypes;
    }
    
    public HotelInfo getHotelDetails(String hotelId) {
        // Mock implementation for getting specific hotel details
        HotelSearchRequest mockRequest = new HotelSearchRequest();
        mockRequest.setDestination("Mumbai");
        mockRequest.setAdults(2);
        mockRequest.setRooms(1);
        
        List<HotelInfo> hotels = createMockHotelData(mockRequest);
        return hotels.stream()
                .filter(hotel -> hotel.getHotelId().equals(hotelId))
                .findFirst()
                .orElse(hotels.get(0)); // Return first hotel if not found
    }
}