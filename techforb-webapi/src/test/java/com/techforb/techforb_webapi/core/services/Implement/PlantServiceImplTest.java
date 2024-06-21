package com.techforb.techforb_webapi.core.services.Implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.techforb.techforb_webapi.core.dtos.Response.GlobalReading;
import com.techforb.techforb_webapi.core.models.Country;
import com.techforb.techforb_webapi.core.models.Plant;
import com.techforb.techforb_webapi.core.reposittories.PlantRepository;

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
        // Arrange
        List<Plant> plantList = List.of(mock(Plant.class), mock(Plant.class), mock(Plant.class), mock(Plant.class));

        when(plantRepository.findAll()).thenReturn(plantList);

        // Act

        List<Plant> result = plantService.getAll();

        // Assert

        assertEquals(plantList.size(), result.size());
        assertEquals(plantList, result);

    }

    @Test
    void testGetById() {
        // Arrange

        long id = 10;

        Plant expectedPlant = new Plant(id, "Plant 10", 12, 32, 32, 15, new Country("Argentina", "url de bandera"));

        when(plantRepository.findById(id)).thenReturn(Optional.of(expectedPlant));

        // Act
        Plant result = plantService.getById(id);

        // Assert
        assertEquals(expectedPlant.getName(), result.getName());
        assertEquals(expectedPlant.getNumberOfDisabledSensors(), result.getNumberOfDisabledSensors());
        assertEquals(expectedPlant.getNumberOfMediumAlerts(), result.getNumberOfMediumAlerts());
        assertEquals(expectedPlant.getNumberOfReadings(), result.getNumberOfReadings());
        assertEquals(expectedPlant.getNumberOfRedAlerts(), result.getNumberOfRedAlerts());
        assertEquals(expectedPlant.getCountry().getName(), result.getCountry().getName());
        assertEquals(expectedPlant.getCountry().getFlagImage(), result.getCountry().getFlagImage());

    }

    @Test
    void testGetGlobalReading() {
        // Arrange
        long expectedNumberOfDisabledSensors = 4;
        long expectedNumberOfMediumAlerts = 5;
        long expectedNumberOfReadings = 6;
        long expectedNumberOfRedAlerts = 7;

        Plant plant1 = new Plant(3, "Plant 10", 3, 4, 4, 2, new Country("Argentina", "url de bandera"));
        Plant plant2 = new Plant(1, "Plant 10", 3, 3, 1, 2, new Country("Argentina", "url de bandera"));
        List<Plant> plantList = List.of(plant1, plant2);
        when(plantRepository.findAll()).thenReturn(plantList);

        // Act

        GlobalReading result = plantService.getGlobalReading();

        // Assert
        assertEquals(expectedNumberOfDisabledSensors, result.getDisableSensors());
        assertEquals(expectedNumberOfMediumAlerts, result.getMediumAlerts());
        assertEquals(expectedNumberOfReadings, result.getReadingOK());
        assertEquals(expectedNumberOfRedAlerts, result.getRedAlerts());

    }

    @Test
    void testRemove() {
        // Arrange
        long id = 6;

        Plant plant = Plant.builder().id(id).build();

        when(plantRepository.findById(id)).thenReturn(Optional.of(plant));

        // Act
        plantService.remove(id);

        // Assert
        verify(plantRepository, times(1)).findById(id);
        verify(plantRepository, times(1)).deleteById(id);

    }

    @Test
    void testUpdate() {
        // Arrange
        long id = 8;
        Plant plant = Plant.builder().id(9).build();
        when(plantRepository.findById(id)).thenReturn(Optional.of(plant));

        // Act
        plantService.remove(id);

        // Assert
        verify(plantRepository, times(1)).findById(id);
        verify(plantRepository, times(1)).deleteById(id);

    }
}
