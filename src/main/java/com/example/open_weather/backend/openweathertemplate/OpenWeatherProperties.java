package com.example.open_weather.backend.openweathertemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "open-weather")
public class OpenWeatherProperties {

    private String url;
    private String appId;
    private String lang;
    private String units;
    private Integer connectTimeout;
    private Integer readTimeout;
}
