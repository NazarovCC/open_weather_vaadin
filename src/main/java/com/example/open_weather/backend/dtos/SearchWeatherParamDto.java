package com.example.open_weather.backend.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchWeatherParamDto {

    @Pattern(regexp = "-?\\d{2}+(\\.\\d{2})?", message = "Введите число, например 33.33")
    private String lat;
    @Pattern(regexp = "-?\\d{2}+(\\.\\d{2})?", message = "Введите число, например 33.33")
    private String lon;
}
