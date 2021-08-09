package com.weather.sensormetrics.repository;

import com.weather.sensormetrics.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {

}