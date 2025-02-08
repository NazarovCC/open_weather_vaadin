package com.example.open_weather.backend.repositories;

import com.example.open_weather.backend.entities.RequestWeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RequestWeatherHistoryRepository extends JpaRepository<RequestWeatherHistory, Long> {

    @Transactional(readOnly = true)
    RequestWeatherHistory findFirstByOrderByIdDesc();
}
