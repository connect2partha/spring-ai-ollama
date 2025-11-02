package com.codelounge.agent.ai.config;


import com.codelounge.agent.ai.tools.TimeService;
import com.codelounge.agent.ai.tools.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import java.util.function.Function;

@Configuration
public class ToolConfig {

    // Time Tool
    @Bean
    @Description("Gets the current time for a specified city.")
    public Function<TimeService.TimeRequest, TimeService.TimeResponse> getCurrentTime() {
        // Assume CurrentTimeService is a class with the getCurrentTime method
        return new TimeService()::getCurrentTime;
    }

    // Weather Tool
    @Bean
    @Description("Gets the current weather information for a specified city name.")
    public Function<WeatherService.WeatherRequest, WeatherService.WeatherResponse> getWeatherInfo() {
        return new WeatherService()::getWeatherInfo;
    }
}