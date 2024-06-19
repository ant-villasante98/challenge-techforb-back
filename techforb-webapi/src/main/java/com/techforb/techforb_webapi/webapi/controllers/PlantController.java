package com.techforb.techforb_webapi.webapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.techforb_webapi.core.dtos.SuccessResponse;
import com.techforb.techforb_webapi.core.dtos.Request.PlantCreateRequest;
import com.techforb.techforb_webapi.core.dtos.Request.PlantUpdateRequest;
import com.techforb.techforb_webapi.core.dtos.Response.GlobalReading;
import com.techforb.techforb_webapi.core.dtos.Response.PlantResponse;
import com.techforb.techforb_webapi.core.mappers.PlantDtoMapper;
import com.techforb.techforb_webapi.core.models.Plant;
import com.techforb.techforb_webapi.core.services.PlantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @Autowired
    PlantDtoMapper dtoMapper;

    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> create(@RequestBody PlantCreateRequest request) {
        plantService.create(request.getName(), request.getCountryName(), request.getCountryFlag());

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SuccessResponse<List<PlantResponse>>> getAll() {
        List<Plant> plants = plantService.getAll();
        List<PlantResponse> plantResponse = plants.stream().map(dtoMapper::apply).toList();
        SuccessResponse<List<PlantResponse>> response = new SuccessResponse<List<PlantResponse>>(plantResponse,
                HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SuccessResponse<PlantResponse>> getMethodName(@PathVariable long id) {
        Plant plant = plantService.getById(id);
        SuccessResponse<PlantResponse> response = SuccessResponse.create(dtoMapper.apply(plant), HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        plantService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updateOne(@PathVariable long id, @RequestBody PlantUpdateRequest request) {
        plantService.update(id, request.getNumberOfReadings(), request.getNumberOfRedAlerts(),
                request.getNumberOfMediumAlerts(),
                request.getNumberOfDisabledSensors());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("global-reading")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SuccessResponse<GlobalReading>> getMethodName() {
        GlobalReading global = plantService.getGlobalReading();
        SuccessResponse<GlobalReading> response = SuccessResponse.create(global, HttpStatus.OK.value());
        return ResponseEntity.ok().body(response);
    }

}
