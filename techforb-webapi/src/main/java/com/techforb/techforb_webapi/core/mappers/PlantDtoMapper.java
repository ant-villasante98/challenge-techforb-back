package com.techforb.techforb_webapi.core.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.techforb_webapi.core.dtos.Response.PlantResponse;
import com.techforb.techforb_webapi.core.models.Plant;

@Service
public class PlantDtoMapper implements Function<Plant, PlantResponse> {

    @Autowired
    CountryDtoMapper countryDtoMapper;

    @Override
    public PlantResponse apply(Plant plant) {

        return new PlantResponse(plant.getId(), plant.getName(), plant.getNumberOfReadings(),
                plant.getNumberOfRedAlerts(), plant.getNumberOfMediumAlerts(), plant.getNumberOfDisabledSensors(),
                countryDtoMapper.apply(plant.getCountry()));
    }

}
