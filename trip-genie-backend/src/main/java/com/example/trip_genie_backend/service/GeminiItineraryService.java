package com.example.trip_genie_backend.service;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.trip_genie_backend.model.DayItinerary;
import com.example.trip_genie_backend.model.ItineraryOption;
import com.example.trip_genie_backend.model.ItineraryRequest;
import com.example.trip_genie_backend.model.ItineraryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiItineraryService {
    
    @Value("${gemini.api.key}")
    private String geminiApiKey;
    
    @Value("${gemini.api.url:https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent}")
    private String geminiApiUrl;
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    public GeminiItineraryService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }
    
    public ItineraryResponse generateItinerary(ItineraryRequest request) {
        try {
            // Calculate trip duration
            long tripDuration = ChronoUnit.DAYS.between(request.getTripStartDate(), request.getTripEndDate()) + 1;
            
            // Create the prompt for Gemini
            String prompt = createItineraryPrompt(request, tripDuration);
            
            // Call Gemini API
            String geminiResponse = callGeminiAPI(prompt);
            
            // Parse the response and create itinerary options
            List<ItineraryOption> options = parseGeminiResponse(geminiResponse, request, tripDuration);
            
            return ItineraryResponse.success(options);
            
        } catch (Exception e) {
            return ItineraryResponse.error("Failed to generate itinerary: " + e.getMessage());
        }
    }
    
    private String createItineraryPrompt(ItineraryRequest request, long tripDuration) {
        StringBuilder prompt = new StringBuilder();
        
        prompt.append("You are an expert travel planner. Create 3 different travel itinerary options for the following trip:\n\n");
        prompt.append("Destination: ").append(request.getDestination()).append("\n");
        prompt.append("Budget: $").append(request.getBudget()).append(" USD total\n");
        prompt.append("Trip Duration: ").append(tripDuration).append(" days\n");
        prompt.append("Start Date: ").append(request.getTripStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)).append("\n");
        prompt.append("End Date: ").append(request.getTripEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE)).append("\n");
        prompt.append("Number of Travelers: ").append(request.getNumberOfPersons()).append("\n");
        
        if (request.getTravelStyle() != null && !request.getTravelStyle().isEmpty()) {
            prompt.append("Travel Style: ").append(request.getTravelStyle()).append("\n");
        }
        if (request.getInterests() != null && !request.getInterests().isEmpty()) {
            prompt.append("Interests: ").append(request.getInterests()).append("\n");
        }
        if (request.getAccommodation() != null && !request.getAccommodation().isEmpty()) {
            prompt.append("Preferred Accommodation: ").append(request.getAccommodation()).append("\n");
        }
        
        prompt.append("\nPlease provide exactly 3 different itinerary options that fit within the budget. ");
        prompt.append("Each option should have a different theme/style (e.g., Budget-Friendly, Mid-Range, Luxury or Cultural, Adventure, Relaxation). ");
        prompt.append("\nFor each option, provide:\n");
        prompt.append("1. Title and brief description\n");
        prompt.append("2. Total estimated cost breakdown\n");
        prompt.append("3. Day-by-day itinerary with activities, accommodation, meals, and daily costs\n");
        prompt.append("4. Transportation recommendations\n");
        prompt.append("5. Key highlights of the trip\n");
        prompt.append("6. Important notes or tips\n\n");
        
        prompt.append("Format the response as valid JSON with this structure:\n");
        prompt.append("{\n");
        prompt.append("  \"options\": [\n");
        prompt.append("    {\n");
        prompt.append("      \"title\": \"Option Title\",\n");
        prompt.append("      \"description\": \"Brief description\",\n");
        prompt.append("      \"totalCost\": 1200.50,\n");
        prompt.append("      \"travelStyle\": \"Budget/Mid-Range/Luxury\",\n");
        prompt.append("      \"transportation\": \"Transportation details\",\n");
        prompt.append("      \"notes\": \"Important notes and tips\",\n");
        prompt.append("      \"highlights\": [\"Highlight 1\", \"Highlight 2\"],\n");
        prompt.append("      \"dailyItinerary\": [\n");
        prompt.append("        {\n");
        prompt.append("          \"day\": 1,\n");
        prompt.append("          \"date\": \"2025-01-01\",\n");
        prompt.append("          \"activities\": \"Detailed activities for the day\",\n");
        prompt.append("          \"accommodation\": \"Where to stay\",\n");
        prompt.append("          \"meals\": \"Meal recommendations\",\n");
        prompt.append("          \"estimatedCost\": 150.00\n");
        prompt.append("        }\n");
        prompt.append("      ]\n");
        prompt.append("    }\n");
        prompt.append("  ]\n");
        prompt.append("}\n\n");
        prompt.append("Ensure all costs are realistic and add up to stay within the total budget. ");
        prompt.append("Make sure each option offers different experiences and price points.");
        
        return prompt.toString();
    }
    
    private String callGeminiAPI(String prompt) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> contents = new HashMap<>();
        Map<String, Object> parts = new HashMap<>();
        parts.put("text", prompt);
        contents.put("parts", Arrays.asList(parts));
        requestBody.put("contents", Arrays.asList(contents));
        
        Map<String, Object> generationConfig = new HashMap<>();
        generationConfig.put("temperature", 0.7);
        generationConfig.put("topK", 40);
        generationConfig.put("topP", 0.95);
        generationConfig.put("maxOutputTokens", 8192);
        requestBody.put("generationConfig", generationConfig);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        String url = geminiApiUrl + "?key=" + geminiApiKey;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            return extractTextFromGeminiResponse(response.getBody());
        } else {
            throw new RuntimeException("Gemini API call failed: " + response.getStatusCode());
        }
    }
    
    private String extractTextFromGeminiResponse(String responseBody) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode candidates = root.path("candidates");
        if (candidates.isArray() && candidates.size() > 0) {
            JsonNode content = candidates.get(0).path("content");
            JsonNode parts = content.path("parts");
            if (parts.isArray() && parts.size() > 0) {
                return parts.get(0).path("text").asText();
            }
        }
        throw new RuntimeException("Invalid response format from Gemini API");
    }
    
    private List<ItineraryOption> parseGeminiResponse(String geminiResponse, ItineraryRequest request, long tripDuration) {
        try {
            // Try to extract JSON from the response
            String jsonResponse = extractJsonFromResponse(geminiResponse);
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode optionsNode = root.path("options");
            
            List<ItineraryOption> options = new ArrayList<>();
            
            if (optionsNode.isArray()) {
                for (JsonNode optionNode : optionsNode) {
                    ItineraryOption option = new ItineraryOption();
                    option.setTitle(optionNode.path("title").asText());
                    option.setDescription(optionNode.path("description").asText());
                    option.setTotalCost(optionNode.path("totalCost").asDouble());
                    option.setTravelStyle(optionNode.path("travelStyle").asText());
                    option.setTransportation(optionNode.path("transportation").asText());
                    option.setNotes(optionNode.path("notes").asText());
                    
                    // Parse highlights
                    List<String> highlights = new ArrayList<>();
                    JsonNode highlightsNode = optionNode.path("highlights");
                    if (highlightsNode.isArray()) {
                        for (JsonNode highlight : highlightsNode) {
                            highlights.add(highlight.asText());
                        }
                    }
                    option.setHighlights(highlights);
                    
                    // Parse daily itinerary
                    List<DayItinerary> dailyItinerary = new ArrayList<>();
                    JsonNode dailyNode = optionNode.path("dailyItinerary");
                    if (dailyNode.isArray()) {
                        for (JsonNode day : dailyNode) {
                            DayItinerary dayItinerary = new DayItinerary();
                            dayItinerary.setDay(day.path("day").asInt());
                            dayItinerary.setDate(day.path("date").asText());
                            dayItinerary.setActivities(day.path("activities").asText());
                            dayItinerary.setAccommodation(day.path("accommodation").asText());
                            dayItinerary.setMeals(day.path("meals").asText());
                            dayItinerary.setEstimatedCost(day.path("estimatedCost").asDouble());
                            dailyItinerary.add(dayItinerary);
                        }
                    }
                    option.setDailyItinerary(dailyItinerary);
                    
                    options.add(option);
                }
            }
            
            // If we couldn't parse or got no options, create fallback options
            if (options.isEmpty()) {
                options = createFallbackOptions(request, tripDuration);
            }
            
            return options;
            
        } catch (Exception e) {
            // Return fallback options if parsing fails
            return createFallbackOptions(request, tripDuration);
        }
    }
    
    private String extractJsonFromResponse(String response) {
        // Try to find JSON content between ```json and ``` or just look for { }
        if (response.contains("```json")) {
            int start = response.indexOf("```json") + 7;
            int end = response.indexOf("```", start);
            if (end != -1) {
                return response.substring(start, end).trim();
            }
        }
        
        // Look for JSON object starting with {
        int start = response.indexOf('{');
        int end = response.lastIndexOf('}');
        if (start != -1 && end != -1 && end > start) {
            return response.substring(start, end + 1).trim();
        }
        
        return response;
    }
    
    private List<ItineraryOption> createFallbackOptions(ItineraryRequest request, long tripDuration) {
        List<ItineraryOption> fallbackOptions = new ArrayList<>();
        
        double budgetPerOption = request.getBudget() * 0.8; // Use 80% of budget for conservative estimate
        
        // Create 3 basic options
        String[] styles = {"Budget-Friendly", "Mid-Range", "Comfort"};
        String[] descriptions = {
            "An economical trip focusing on local experiences and budget accommodations",
            "A balanced trip with moderate comfort and good value experiences", 
            "A comfortable trip with quality accommodations and premium experiences"
        };
        
        for (int i = 0; i < 3; i++) {
            ItineraryOption option = new ItineraryOption();
            option.setTitle(styles[i] + " " + request.getDestination() + " Experience");
            option.setDescription(descriptions[i]);
            option.setTotalCost(budgetPerOption * (0.7 + i * 0.15)); // Varying costs
            option.setTravelStyle(styles[i]);
            option.setTransportation("Local transportation and walking");
            option.setNotes("This is a basic itinerary. Please customize based on your preferences.");
            option.setHighlights(Arrays.asList("Explore " + request.getDestination(), "Local cuisine", "Cultural sites"));
            
            // Create basic daily itinerary
            List<DayItinerary> dailyItinerary = new ArrayList<>();
            for (int day = 1; day <= tripDuration; day++) {
                DayItinerary dayPlan = new DayItinerary();
                dayPlan.setDay(day);
                dayPlan.setDate(request.getTripStartDate().plusDays(day - 1).toString());
                dayPlan.setActivities("Explore local attractions and culture in " + request.getDestination());
                dayPlan.setAccommodation(styles[i] + " accommodation option");
                dayPlan.setMeals("Local dining options");
                dayPlan.setEstimatedCost(option.getTotalCost() / tripDuration);
                dailyItinerary.add(dayPlan);
            }
            option.setDailyItinerary(dailyItinerary);
            
            fallbackOptions.add(option);
        }
        
        return fallbackOptions;
    }
}