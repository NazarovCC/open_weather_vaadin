package com.example.open_weather.backend.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class WeatherDto {

    private String description;
    private float degree;
    private float feelingDegree;
    private String imageUrl;
}
