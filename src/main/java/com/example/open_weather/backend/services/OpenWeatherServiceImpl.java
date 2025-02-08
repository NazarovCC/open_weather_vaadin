package com.example.open_weather.backend.services;

import com.example.open_weather.backend.openweathertemplate.OpenWeatherProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OpenWeatherServiceImpl implements OpenWeatherService {

    private final RestTemplate restTemplate;
    private final OpenWeatherProperties openWeatherProperties;

    public OpenWeatherServiceImpl(@Qualifier("restTemplateOpenWeather") RestTemplate restTemplate, OpenWeatherProperties openWeatherProperties) {
        this.restTemplate = restTemplate;
        this.openWeatherProperties = openWeatherProperties;
    }

    @Override
    public String getWeatherByLonAndLan(String lon, String lat) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(openWeatherProperties.getUrl())
                .queryParam("lon", lon)
                .queryParam("lat", lat);

        String result = restTemplate.getForObject(uriComponentsBuilder.toUriString(), String.class);
        return result;
    }
}
