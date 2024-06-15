package com.techforb.techforb_webapi.core.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plants")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plant {

    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "number_of_readings", nullable = false)
    private long numberOfReadings;

    @Column(name = "number_of_red_alerts", nullable = false)
    private long numberOfRedAlerts;

    @Column(name = "number_of_medium_alerts", nullable = false)
    private long numberOfMediumAlerts;

    @Column(name = "number_of_desbled_sensors")
    private long numberOfDisabledSensors;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "flagImage", column = @Column(name = "country_flag_image")),
            @AttributeOverride(name = "name", column = @Column(name = "country_name"))
    })
    private PlantCountry country;
}
