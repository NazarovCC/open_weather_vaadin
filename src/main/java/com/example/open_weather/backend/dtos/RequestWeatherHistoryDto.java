package com.example.open_weather.backend.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class RequestWeatherHistoryDto {

    private LocalTime localTime;
    private float lon;
    private float lat;
    private String description;
}
