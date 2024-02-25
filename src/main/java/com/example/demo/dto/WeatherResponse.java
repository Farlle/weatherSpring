package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class WeatherResponse {
    private Location location;
    private double avgTempMin;
    private double avgTempHour;
    private double avgTempDay;
}
