package com.techforb.techforb_webapi.webapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.techforb_webapi.core.dtos.Request.PlantCreateRequest;
import com.techforb.techforb_webapi.core.dtos.Response.PlantResponse;
import com.techforb.techforb_webapi.core.mappers.PlantDtoMapper;
import com.techforb.techforb_webapi.core.models.Plant;
import com.techforb.techforb_webapi.core.services.PlantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @Autowired
    PlantDtoMapper dtoMapper;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PlantCreateRequest request) {
        plantService.create(request.getName(), request.getCountryName(), request.getContryFlag());

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<PlantResponse>> getAll() {
        List<Plant> plants = plantService.getAll();
        List<PlantResponse> response = plants.stream().map(dtoMapper::apply).toList();
        return ResponseEntity.ok().body(response);
    }

}
