package com.techforb.techforb_webapi.core.services.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforb.techforb_webapi.core.dtos.Response.GlobalReading;
import com.techforb.techforb_webapi.core.exceptions.NotFoundException;
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
        return plantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    @Override
    public List<Plant> getAll() {
        return plantRepository.findAll();
    }

    @Override
    public void update(long id, long numberOfReadings, long numberOfRedAlerts, long numberOfMediumAlerts,
            long numberOfDisabledSensors) {
        Plant foundPlant = getById(id);
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

    @Override
    public GlobalReading getGlobalReading() {
        List<Plant> plantList = plantRepository.findAll();
        GlobalReading global = new GlobalReading(0, 0, 0, 0);
        plantList.stream().forEach(x -> {
            global.setDisableSensors(global.getDisableSensors() + x.getNumberOfDisabledSensors());
            global.setMediumAlerts(global.getMediumAlerts() + x.getNumberOfMediumAlerts());
            global.setReadingOK(global.getReadingOK() + x.getNumberOfReadings());
            global.setRedAlerts(global.getRedAlerts() + x.getNumberOfRedAlerts());
        });
        return global;
    }

}
