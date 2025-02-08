package com.example.open_weather.backend.openweathertemplate;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableConfigurationProperties({OpenWeatherProperties.class})
public class OpenWeatherTemplateConfig {

    @Bean("restTemplateOpenWeather")
    public RestTemplate restTemplateOpenWeather(RestTemplateBuilder restTemplateBuilder, OpenWeatherProperties openWeatherProperties) {
        return restTemplateBuilder
                .connectTimeout(Duration.of(openWeatherProperties.getConnectTimeout(), ChronoUnit.MILLIS))
                .readTimeout(Duration.of(openWeatherProperties.getReadTimeout(), ChronoUnit.MILLIS))
                .rootUri(openWeatherProperties.getUrl())
                .build();
    }
}
