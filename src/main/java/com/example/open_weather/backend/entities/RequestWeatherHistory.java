package com.example.open_weather.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalTime;

@Entity
@Table(name = "request_weather_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestWeatherHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lon")
    private float lon;
    @Column(name = "lat")
    private float lat;
    @Column(name = "description")
    private String description;
    @Column(name = "time_request")
    @CreationTimestamp
    private LocalTime timeRequest;
}
