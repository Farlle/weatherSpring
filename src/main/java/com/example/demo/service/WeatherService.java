package com.example.demo.service;

import com.example.demo.mapper.WeatherResponseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WeatherService {
    public static final String LOCATION = "location=42.3478,-71.0466";
    public static final String APIKEY = "HxEO2g926XLo5znaKhmBn8O2Ae3TSHUI";
    public static final String HTTPS_API_TOMORROW = "https://api.tomorrow.io/v4/weather/forecast";

    WeatherResponseMapper mapper;

    public String getTemp() {
        RestTemplate restTemplate = new RestTemplate();
        var body = restTemplate
                .getForEntity(HTTPS_API_TOMORROW + "?" + LOCATION
                        + "&apikey=" + APIKEY, String.class).getBody();

        return mapper.mapWeatherResponse(body).toString();
    }


}
