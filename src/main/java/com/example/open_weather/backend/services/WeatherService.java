package com.example.open_weather.backend.services;

import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.example.open_weather.backend.dtos.WeatherDto;

public interface WeatherService {

    WeatherDto getWeather(SearchWeatherParamDto searchWeatherParamDto);
}
