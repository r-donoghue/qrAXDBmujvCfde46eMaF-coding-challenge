package com.weather.sensormetrics;

import com.weather.sensormetrics.entities.Reading;
import com.weather.sensormetrics.entities.Sensor;
import com.weather.sensormetrics.repository.ReadingRepository;
import com.weather.sensormetrics.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class SensorMetricsApplication {
	private final Logger logger = LoggerFactory.getLogger(SensorMetricsApplication.class);

	@Autowired
	private SensorRepository sensorRepository;

	@Autowired
	private ReadingRepository readingRepository;

	public static void main(String[] args) {
		SpringApplication.run(SensorMetricsApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		logger.info("Application ready!");
	}
}
