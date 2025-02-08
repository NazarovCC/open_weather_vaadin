package com.example.open_weather.backend.services;

public interface OpenWeatherService {
    String getWeatherByLonAndLan(String lon, String lat);
}
