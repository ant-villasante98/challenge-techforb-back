package com.techforb.techforb_webapi.core.dtos.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantUpdateRequest {
    @NotBlank()
    @Min(value = 0)
    private long numberOfReadings;

    @NotBlank()
    @Min(value = 0)
    private long numberOfRedAlerts;

    @NotBlank()
    @Min(value = 0)
    private long numberOfMediumAlerts;

    @NotBlank()
    @Min(value = 0)
    private long numberOfDisabledSensors;
}
