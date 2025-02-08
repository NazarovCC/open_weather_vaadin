package com.example.open_weather.backend.mappers;

import com.example.open_weather.backend.dtos.RequestWeatherHistoryDto;
import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.example.open_weather.backend.dtos.WeatherDto;
import com.example.open_weather.backend.entities.RequestWeatherHistory;
import org.springframework.stereotype.Component;

@Component
public class RequestWeatherHistoryMapper {

    public RequestWeatherHistory toRequestWeatherHistory(WeatherDto weatherDto, SearchWeatherParamDto searchWeatherParamDto) {
        if (weatherDto == null || searchWeatherParamDto == null) {
            return null;
        }

        return RequestWeatherHistory.builder()
                .description(weatherDto.getDescription())
                .lat(Float.parseFloat(searchWeatherParamDto.getLat()))
                .lon(Float.parseFloat(searchWeatherParamDto.getLon()))
                .build();
    }

    public RequestWeatherHistoryDto toDto(RequestWeatherHistory requestWeatherHistory) {
        if (requestWeatherHistory == null) {
            return null;
        }

        return RequestWeatherHistoryDto.builder()
                .localTime(requestWeatherHistory.getTimeRequest())
                .description(requestWeatherHistory.getDescription())
                .lat(requestWeatherHistory.getLat())
                .lon(requestWeatherHistory.getLon())
                .build();
    }
}
