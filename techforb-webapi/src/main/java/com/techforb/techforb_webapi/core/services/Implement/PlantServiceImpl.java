package com.techforb.techforb_webapi.core.services.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.techforb_webapi.core.models.Country;
import com.techforb.techforb_webapi.core.models.Plant;
import com.techforb.techforb_webapi.core.reposittories.PlantRepository;
import com.techforb.techforb_webapi.core.services.PlantService;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository plantRepository;

    @Override
    public Plant getById(long id) {
        // TODO: perzonalizar exception
        return plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro el id" + id));
    }

    @Override
    public List<Plant> getAll() {
        return plantRepository.findAll();
    }

    @Override
    public void update(long id, long numberOfReadings, long numberOfRedAlerts, long numberOfMediumAlerts,
            long numberOfDisabledSensors) {
        Plant foundPlant = getById(numberOfDisabledSensors);
        foundPlant.setNumberOfReadings(numberOfReadings);
        foundPlant.setNumberOfRedAlerts(numberOfRedAlerts);
        foundPlant.setNumberOfMediumAlerts(numberOfMediumAlerts);
        foundPlant.setNumberOfDisabledSensors(numberOfDisabledSensors);

        plantRepository.saveAndFlush(foundPlant);
    }

    @Override
    public void remove(long id) {
        getById(id);
        plantRepository.deleteById(id);
    }

    @Override
    public void create(String name, String countryName, String countryFlagImage) {

        Country country = new Country(countryName, countryFlagImage);
        Plant newPlant = Plant.builder()
                .name(name)
                .country(country)
                .numberOfDisabledSensors(0)
                .numberOfMediumAlerts(0)
                .numberOfReadings(0)
                .numberOfRedAlerts(0)
                .build();

        plantRepository.saveAndFlush(newPlant);
    }

}
