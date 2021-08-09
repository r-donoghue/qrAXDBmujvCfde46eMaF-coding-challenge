package com.weather.sensormetrics.controller;

import com.weather.sensormetrics.entities.Sensor;
import com.weather.sensormetrics.repository.SensorRepository;
import javafx.beans.binding.MapExpression;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorController {
    private final SensorRepository repository;

    SensorController(SensorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/sensor")
    List<Sensor> all() {
        return repository.findAll();
    }

    @PostMapping(value = "/sensor", consumes = "application/json")
    Sensor newSensor(@RequestBody Sensor sensor) {
        return repository.save(sensor);
    }
}
