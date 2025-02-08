package com.example.open_weather.backend.services;

import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.example.open_weather.backend.dtos.WeatherDto;
import com.example.open_weather.backend.mappers.WeatherMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final OpenWeatherService openWeatherService;
    private final WeatherMapper weatherMapper;
    private final RequestWeatherHistoryService requestWeatherHistoryService;

    public WeatherDto getWeather(SearchWeatherParamDto searchWeatherParamDto) {
        String weather = openWeatherService.getWeatherByLonAndLan(searchWeatherParamDto.getLon(), searchWeatherParamDto.getLat());
        try {
            WeatherDto weatherDto = weatherMapper.fromRestResponse(weather);
            requestWeatherHistoryService.saveRequestHistory(weatherDto, searchWeatherParamDto);
            return weatherMapper.fromRestResponse(weather);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
