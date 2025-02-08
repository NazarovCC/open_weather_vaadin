package com.example.open_weather.ui;

import com.example.open_weather.backend.dtos.RequestWeatherHistoryDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherGrid extends VerticalLayout {

    private Grid<RequestWeatherHistoryDto> weatherHistoryDtoGrid = new Grid<>(RequestWeatherHistoryDto.class);

    public WeatherGrid() {
        addClassName("weather-history");
        configureGrid();
        add(new H2("История"),weatherHistoryDtoGrid);
    }

    private void configureGrid() {
        weatherHistoryDtoGrid.addClassName("weather-grid");
        weatherHistoryDtoGrid.setSizeFull();
        weatherHistoryDtoGrid.removeAllColumns();
        weatherHistoryDtoGrid.removeAllHeaderRows();
        weatherHistoryDtoGrid.addColumn((requestWeatherHistoryDto -> {
            LocalTime localTime = requestWeatherHistoryDto.getLocalTime();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            return localTime.format(dateTimeFormatter);
        }));

        weatherHistoryDtoGrid.addColumn((requestWeatherHistoryDto -> {
            float lon = requestWeatherHistoryDto.getLon();
            return "долгота: " + lon;
        }));

        weatherHistoryDtoGrid.addColumn((requestWeatherHistoryDto -> {
            float lat = requestWeatherHistoryDto.getLat();
            return "широта: " + lat;
        }));

        weatherHistoryDtoGrid.addColumn((requestWeatherHistoryDto -> {
            String description = requestWeatherHistoryDto.getDescription();
            return "ответ: " + description;
        }));
    }

    public void updateGrid(List<RequestWeatherHistoryDto> items) {
        weatherHistoryDtoGrid.setItems(items);
    }
}
