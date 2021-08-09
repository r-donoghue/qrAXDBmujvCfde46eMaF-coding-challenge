package com.weather.sensormetrics.repository;

import com.weather.sensormetrics.entities.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
}