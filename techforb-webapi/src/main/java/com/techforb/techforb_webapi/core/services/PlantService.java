package com.techforb.techforb_webapi.core.services;

import java.util.List;

import com.techforb.techforb_webapi.core.models.Plant;

public interface PlantService {

    Plant getById(long id);

    List<Plant> getAll();

    void update(long id, long numberOfReadings, long numberOfRedAlerts, long numberOfMediumAlerts,
            long numberOfDisabledSensors);

    void remove(long id);

    void create(String name, String countryName, String countryFlagImage);

}
