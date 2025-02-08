package com.example.open_weather.backend.services;

import com.example.open_weather.backend.dtos.RequestWeatherHistoryDto;
import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.example.open_weather.backend.dtos.WeatherDto;

import java.util.List;

public interface RequestWeatherHistoryService {

    void saveRequestHistory(WeatherDto weatherDto, SearchWeatherParamDto searchWeatherParamDto);
    List<RequestWeatherHistoryDto> getRequestHistory();
    RequestWeatherHistoryDto getLastAddedHistory();
}
