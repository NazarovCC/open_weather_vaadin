package com.example.open_weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@Theme("my-theme")
public class OpenWeatherApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(OpenWeatherApplication.class, args);
    }
}
