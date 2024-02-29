package com.example.demo.controller;

import com.example.demo.mapper.WeatherResponseMapper;
import com.example.demo.service.WeatherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherResponseMapper weatherResponseMapper;

    public WeatherController(WeatherService weatherService, WeatherResponseMapper weatherResponseMapper) {
        this.weatherService = weatherService;
        this.weatherResponseMapper = weatherResponseMapper;
    }

    @GetMapping("/wet")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getWeather() {
        String str = weatherService.getTemp();
        return str;
    }


}
