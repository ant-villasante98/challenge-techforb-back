package com.techforb.techforb_webapi.core.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantResponse {
    private long id;

    private String name;

    private long numberOfReadings;

    private long numberOfRedAlerts;

    private long numberOfMediumAlerts;

    private long numberOfDisabledSensors;

    private CountryResponse country;
}
