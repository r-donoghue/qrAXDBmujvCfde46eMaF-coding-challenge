package com.weather.sensormetrics.controller;

import com.weather.sensormetrics.entities.Reading;
import com.weather.sensormetrics.repository.ReadingRepository;
import com.weather.sensormetrics.repository.SensorRepository;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReadingController {
    private final ReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    private static final long MILLISECONDS_IN_DAY = 86400000;

    @Autowired
    EntityManager entityManager;

    ReadingController(ReadingRepository readingRepository, SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/reading")
    List<Reading> all() {
        return readingRepository.findAll();
    }

    @PostMapping(value = "/reading")
    Reading newSensor(@RequestParam(name="sensorid") Long sensorId,
                     @RequestParam(name="temperature") Double temperature,
                     @RequestParam(name="humidity") Double humidity,
                     @RequestParam(name="windspeed") Double windspeed,
                     @RequestParam(name="timestamp") Long timestamp) {

        Reading reading = new Reading();
        reading.setSensor(sensorRepository.getById(sensorId));
        reading.setTemperature(temperature);
        reading.setHumidity(humidity);
        reading.setWindspeed(windspeed);
        reading.setTimestamp(timestamp);
        return readingRepository.save(reading);
    }

    @GetMapping(value = "/reading/aggregate")
    Map<String, Object> newSensor(@RequestParam(required = false, defaultValue = "") List<Long> sensors,
                          @RequestParam List<String> metrics,
                          @RequestParam String aggregationtype,
                          @RequestParam(required = false, defaultValue = "1") int days) {

        long upperDateRange = Instant.now().toEpochMilli();
        long lowerDateRange = upperDateRange - (MILLISECONDS_IN_DAY *days);

        Map<String, Object> results = new HashMap<>();
        Query query;
        for(String metric: metrics){
            query= entityManager.createQuery(generateSQLQuery(sensors, metric, aggregationtype));
            query.setParameter(1, lowerDateRange);
            query.setParameter(2, upperDateRange);
            results.put(metric, query.getSingleResult());
        }
        return results;
    }

    public static String generateSQLQuery(List<Long> sensors, String metric, String aggregationtype){

        StringBuilder sb = new StringBuilder();

        sb.append("select ").
                append(aggregationtype).
                append("(").
                append(metric).
                append(") from Reading WHERE ");

        if (sensors.size() > 0){
            sb.append("reading_sensor_id IN (");
            for(int i = 0; i < sensors.size(); i++){
                sb.append(sensors.get(i));
                if ( i != sensors.size() - 1){
                    sb.append(", ");
                }
            }
            sb.append(") AND ");
        }

        sb.append("timestamp between ?1 and ?2");

        return sb.toString();
    }

}
