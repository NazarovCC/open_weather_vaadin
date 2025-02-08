package com.example.open_weather.backend.services;

import com.example.open_weather.backend.dtos.RequestWeatherHistoryDto;
import com.example.open_weather.backend.repositories.RequestWeatherHistoryRepository;
import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.example.open_weather.backend.dtos.WeatherDto;
import com.example.open_weather.backend.entities.RequestWeatherHistory;
import com.example.open_weather.backend.mappers.RequestWeatherHistoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestWeatherHistoryServiceImpl implements RequestWeatherHistoryService {

    private final RequestWeatherHistoryRepository requestWeatherHistoryRepository;
    private final RequestWeatherHistoryMapper requestWeatherHistoryMapper;

    public void saveRequestHistory(WeatherDto weatherDto, SearchWeatherParamDto searchWeatherParamDto) {
        RequestWeatherHistory requestWeatherHistory = requestWeatherHistoryMapper.toRequestWeatherHistory(weatherDto, searchWeatherParamDto);
        requestWeatherHistoryRepository.save(requestWeatherHistory);
    }

    public List<RequestWeatherHistoryDto> getRequestHistory() {
        List<RequestWeatherHistory> requestWeatherHistories = requestWeatherHistoryRepository.findAll();
        return requestWeatherHistories.stream().map(requestWeatherHistoryMapper::toDto).collect(Collectors.toList());
    }

    public RequestWeatherHistoryDto getLastAddedHistory() {
        RequestWeatherHistory requestWeatherHistory = requestWeatherHistoryRepository.findFirstByOrderByIdDesc();
        return requestWeatherHistoryMapper.toDto(requestWeatherHistory);
    }
}
