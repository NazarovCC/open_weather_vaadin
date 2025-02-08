package com.example.open_weather.ui;

import com.example.open_weather.backend.dtos.SearchWeatherParamDto;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;

public class WeatherForm extends FormLayout {

    private TextField textFieldLat = new TextField();
    private TextField textFieldLon = new TextField();
    private Button button = new Button();
    private Binder<SearchWeatherParamDto> binder = new BeanValidationBinder<>(SearchWeatherParamDto.class);

    public WeatherForm() {
        configureTextFieldLat();
        configureTextFieldLon();
        configureButton();
        setClassName("weather-form");
        HorizontalLayout horizontalLayoutText = new HorizontalLayout(textFieldLon, textFieldLat);
        VerticalLayout verticalLayout = new VerticalLayout(horizontalLayoutText, button);

        binder.forField(textFieldLat).bind("lat");
        binder.forField(textFieldLon).bind("lon");
        binder.addStatusChangeListener(event -> button.setEnabled(binder.isValid()));
        binder.setBean(new SearchWeatherParamDto());
        add(verticalLayout);
    }
    private void configureTextFieldLon() {
        textFieldLat.setLabel("Широта");
        textFieldLat.setValueChangeMode(ValueChangeMode.LAZY);
    }
    private void configureTextFieldLat() {
        textFieldLon.setLabel("Долгота");
        textFieldLon.setValueChangeMode(ValueChangeMode.LAZY);
    }
    private void configureButton() {
        button.setText("Показать погоду!");
        button.addClickListener(click -> validateAndSend());
    }

    private void validateAndSend() {
        if (binder.isValid()) {
            fireEvent(new ShowWeatherEvent(this, binder.getBean()));
        }
    }

    public static abstract class WeatherFormEvent extends ComponentEvent<WeatherForm> {
        private SearchWeatherParamDto searchWeatherParamDto;
        protected WeatherFormEvent(WeatherForm source, SearchWeatherParamDto searchWeatherParamDto) {
            super(source, false);
            this.searchWeatherParamDto = searchWeatherParamDto;
        }

        public SearchWeatherParamDto getSearchWeatherParamDto() {
            return searchWeatherParamDto;
        }
    }

    public static class ShowWeatherEvent extends WeatherFormEvent {
        public ShowWeatherEvent(WeatherForm source, SearchWeatherParamDto searchWeatherParamDto) {
            super(source, searchWeatherParamDto);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> event, ComponentEventListener<T> componentEvent) {
        return getEventBus().addListener(event, componentEvent);
    }
}
