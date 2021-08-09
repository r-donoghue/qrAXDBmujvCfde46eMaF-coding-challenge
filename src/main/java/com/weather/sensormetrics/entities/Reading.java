package com.weather.sensormetrics.entities;


import javax.persistence.*;

@Entity
@Table(name = "Reading")
public class Reading {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="reading_sensor_id", nullable=false)
    private Sensor sensor;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "windspeed")
    private double windspeed;

    @Column(name = "timestamp")
    private long timestamp;

    public Reading() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = this.id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "id=" + id +
                ", sensor=" + sensor +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windspeed=" + windspeed +
                ", timestamp=" + timestamp +
                '}';
    }
}