package com.techforb.techforb_webapi.core.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantUpdateRequest {

    private long numberOfReadings;

    private long numberOfRedAlerts;

    private long numberOfMediumAlerts;

    private long numberOfDisabledSensors;
}
