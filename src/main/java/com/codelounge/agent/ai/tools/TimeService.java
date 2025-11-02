package com.codelounge.agent.ai.tools;

import java.util.function.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeService {

    // 1. Define the Input and Output Records (POJOs) for the function
    public record TimeRequest(
        @Description("The name of the city to get the current time for, e.g., 'London' or 'Tokyo'")
        String city
    ) {}

    public record TimeResponse(String currentTime, String timezone) {}

    /**
     * This bean is the "tool" the LLM can call.
     */

    @Description("Get the current local time for a specified city.")
    public TimeResponse getCurrentTime(TimeRequest request) {
        String city = request.city().toLowerCase();
        String timezoneId;

        // Map the city name from the LLM to a Java ZoneId
        if (city.contains("london")) {
            timezoneId = "Europe/London";
        } else if (city.contains("tokyo")) {
            timezoneId = "Asia/Tokyo";
        } else {
            timezoneId = ZoneId.systemDefault().getId();
        }

        LocalTime time = LocalTime.now(ZoneId.of(timezoneId));
        return new TimeResponse(
                time.toString(),
                timezoneId
        );
    }
}