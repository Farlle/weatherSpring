package com.example.demo.mapper;

import com.example.demo.dto.Location;
import com.example.demo.dto.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class WeatherResponseMapper {
    private final ObjectMapper jsonParser = new ObjectMapper();


    @SneakyThrows
    public WeatherResponse mapWeatherResponse(String str) {
        JsonNode jsonNode = jsonParser.readTree(str);
        var minutely = getAvgTemper(jsonNode, "minutely", "temperature");
        var hourly = getAvgTemper(jsonNode, "hourly", "temperature");
        var daily = getAvgTemper(jsonNode, "daily", "temperatureAvg");
        var location = getLocation(jsonNode);
        return new WeatherResponse(location, minutely, hourly, daily);
    }

    private Location getLocation(JsonNode jsonNode) {
        String lat = jsonNode.get("location").get("lat").toString();
        String lon = jsonNode.get("location").get("lon").toString();


        return new Location(lat, lon);

    }

    private double getAvgTemper(JsonNode jsonNode, String range, String nodeName) {
        return jsonNode
                .get("timelines")
                .get(range)
                .findValues(nodeName)
                .stream()
                .mapToDouble(JsonNode::doubleValue)
                .average()
                .getAsDouble();
    }


}
