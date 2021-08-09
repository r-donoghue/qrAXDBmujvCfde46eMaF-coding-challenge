package com.weather.sensormetrics.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonType;


@Entity
@Table(name = "Sensor")
@TypeDef(
        name = "json",
        typeClass = JsonType.class
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sensor {

    @Id
    private long sensorId;

    @Type(type = "json")
    @Column(name = "metadata", columnDefinition = "jsonb")
    private String metadata;

    public Sensor() {
        super();
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}