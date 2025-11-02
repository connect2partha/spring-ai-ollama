package com.codelounge.agent.ai.tools;


public class WeatherService {

    // --- Input Record (What the LLM provides) ---
    /**
     * Request for weather information.
     * @param city The city name (e.g., "London", "Tokyo"). Required.
     */
    public record WeatherRequest(String city) {}

    // --- Output Record (What the function returns) ---
    /**
     * Response containing the current weather information.
     * @param city The city name.
     * @param description A summary of the weather.
     * @param temperature The current temperature in Celsius.
     */
    public record WeatherResponse(String city, String description, double temperature) {}

    // --- The actual Function Bean ---
    /**
     * A function that returns the current weather information for a specified city.
     * The LLM will use this tool when the user asks about weather.
     */
    public WeatherResponse getWeatherInfo(WeatherRequest request) {
        // --- NOTE: In a real app, you would call an external Weather API here ---
        
        // Mock logic to return data based on the city name
        String city = request.city().toLowerCase();
        
        return switch (city) {
            case "tokyo" -> new WeatherResponse("Tokyo", "Clear skies and sunny.", 28.5);
            case "london" -> new WeatherResponse("London", "Light drizzle with overcast clouds.", 14.2);
            default -> new WeatherResponse(request.city(), "Weather data not found.", 0.0);
        };
    }
}