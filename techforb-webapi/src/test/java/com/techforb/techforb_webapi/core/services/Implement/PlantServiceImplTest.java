package com.techforb.techforb_webapi.core.services.Implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techforb.techforb_webapi.core.models.Country;
import com.techforb.techforb_webapi.core.models.Plant;
import com.techforb.techforb_webapi.core.reposittories.PlantRepository;

import io.jsonwebtoken.lang.Assert;

@ExtendWith(MockitoExtension.class)
public class PlantServiceImplTest {
    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantServiceImpl plantService;

    @Test
    void testCreate() {
        // Arrage
        String name = "planta 1";
        String countryName = "Argentina";
        String countryFlag = "url de bandera";
        Plant expectedPlant = new Plant(0, name, 0, 0, 0, 0, new Country(countryName, countryFlag));

        when(plantRepository.saveAndFlush(any(Plant.class))).thenReturn(any(Plant.class));

        // Act
        plantService.create(name, countryName, countryFlag);

        ArgumentCaptor<Plant> plantCaptor = ArgumentCaptor.forClass(Plant.class);
        verify(plantRepository, times(1)).saveAndFlush(plantCaptor.capture());

        // Assert
        Plant capturePlant = plantCaptor.getValue();
        assertEquals(expectedPlant.getName(), capturePlant.getName());
        assertEquals(expectedPlant.getNumberOfDisabledSensors(), capturePlant.getNumberOfDisabledSensors());
        assertEquals(expectedPlant.getNumberOfMediumAlerts(), capturePlant.getNumberOfMediumAlerts());
        assertEquals(expectedPlant.getNumberOfReadings(), capturePlant.getNumberOfReadings());
        assertEquals(expectedPlant.getNumberOfRedAlerts(), capturePlant.getNumberOfRedAlerts());
        assertEquals(expectedPlant.getCountry().getName(), capturePlant.getCountry().getName());
        assertEquals(expectedPlant.getCountry().getFlagImage(), capturePlant.getCountry().getFlagImage());

    }

    @Test
    void testGetAll() {

    }

    @Test
    void testGetById() {

    }

    @Test
    void testGetGlobalReading() {

    }

    @Test
    void testRemove() {

    }

    @Test
    void testUpdate() {

    }
}
