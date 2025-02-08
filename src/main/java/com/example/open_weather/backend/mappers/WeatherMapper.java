package com.example.open_weather.backend.mappers;

import com.example.open_weather.backend.dtos.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherMapper {

    private Map<String, String> imageMap = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        imageMap.put("пасмурно", "img1.jpg");
        imageMap.put("ясно", "img2.jpg");
        imageMap.put("переменная облачность", "img3.jpg");
        imageMap.put("облачно с прояснениями", "img4.jpg");
    }

    public WeatherDto fromRestResponse(String resultRestTemplate) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(resultRestTemplate);

        WeatherDto weatherDto = new WeatherDto();

        if (jsonNode.has("main")) {

            JsonNode mainNode = jsonNode.get("main");
            if (mainNode.has("temp")) {
                weatherDto.setDegree(mainNode.get("temp").floatValue());
            }
            if (mainNode.has("feels_like")) {
                weatherDto.setFeelingDegree(mainNode.get("feels_like").floatValue());
            }
        }

        if (jsonNode.has("weather")) {
            JsonNode weatherNode = jsonNode.get("weather");

            if (weatherNode.isArray() && weatherNode.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();

                for (JsonNode weather: weatherNode) {
                    if (weather.has("description")) {
                        weatherDto.setImageUrl(imageMap.getOrDefault(weather.get("description").asText(), "img5.jpg"));
                        stringBuilder.append(weather.get("description").asText()).append(", ");
                    }
                }

                if (stringBuilder.length() > 0) {
                    stringBuilder.setLength(stringBuilder.length() - 2);
                    weatherDto.setDescription(stringBuilder.toString());
                }
            }
        }

        return weatherDto;
    }
}
