package com.example.open_weather.ui;

import com.example.open_weather.backend.dtos.RequestWeatherHistoryDto;
import com.example.open_weather.backend.dtos.WeatherDto;
import com.example.open_weather.backend.services.RequestWeatherHistoryService;
import com.example.open_weather.backend.services.WeatherServiceImpl;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.util.ArrayList;
import java.util.List;

@Route("")
@CssImport("./themes/my-theme/styles.css")
public class HomeView extends VerticalLayout {

    private List<RequestWeatherHistoryDto> weatherHistory = new ArrayList<>();
    private RequestWeatherHistoryService requestWeatherHistoryService;
    private WeatherServiceImpl weatherServiceImpl;
    private WeatherForm weatherForm;
    private H1 h1 = new H1();
    private Image image = new Image();
    private Paragraph paragraph = new Paragraph();
    private Div resultContent = new Div(h1, paragraph, image);
    private WeatherGrid weatherGrid = new WeatherGrid();

    public HomeView(RequestWeatherHistoryService requestWeatherHistoryService, WeatherServiceImpl weatherServiceImpl) {

        this.requestWeatherHistoryService = requestWeatherHistoryService;
        this.weatherServiceImpl = weatherServiceImpl;

        weatherForm = new WeatherForm();
        weatherForm.addListener(WeatherForm.ShowWeatherEvent.class, this::getWeather);

        addClassName("list-view");
        setSizeFull();

        image.setMaxWidth("500px");
        resultContent.setVisible(false);

        Div mainContent = new Div(weatherForm, resultContent);
        mainContent.setClassName("main-content");

        Div divider = new Div();
        divider.setClassName("divider");

        Div content = new Div(mainContent, divider, weatherGrid);
        content.setClassName("content");
        content.setSizeFull();
        add(content);
        updateList();
    }

    private void getWeather(WeatherForm.ShowWeatherEvent event) {
        WeatherDto weatherDto = weatherServiceImpl.getWeather(event.getSearchWeatherParamDto());
        h1.setText(weatherDto.getDescription());
        paragraph.setText(String.format("%s градусов, ощущается как %s", weatherDto.getDegree(), weatherDto.getFeelingDegree()));
        resultContent.setVisible(true);
        StreamResource streamResource = new StreamResource(weatherDto.getImageUrl(), () -> getClass().getClassLoader().getResourceAsStream("images/" + weatherDto.getImageUrl()));
        image.setSrc(streamResource);
        RequestWeatherHistoryDto requestWeatherHistoryDto = requestWeatherHistoryService.getLastAddedHistory();
        if (requestWeatherHistoryDto != null) {
            weatherHistory.add(requestWeatherHistoryService.getLastAddedHistory());
            weatherGrid.updateGrid(weatherHistory);
        }
    }

    private void updateList() {
        weatherHistory.addAll(requestWeatherHistoryService.getRequestHistory());
        weatherGrid.updateGrid(weatherHistory);
    }
}
