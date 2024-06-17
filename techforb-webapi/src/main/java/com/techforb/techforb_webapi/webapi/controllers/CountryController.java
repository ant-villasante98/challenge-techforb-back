package com.techforb.techforb_webapi.webapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techforb.techforb_webapi.core.dtos.Response.CountryResponse;
import com.techforb.techforb_webapi.core.mappers.CountryDtoMapper;
import com.techforb.techforb_webapi.core.models.Country;
import com.techforb.techforb_webapi.core.services.CountryService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1/countries")
public class CountryController {

    @Autowired
    CountryService countryService;

    @Autowired
    CountryDtoMapper dtoMapper;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getMethodName() {
        List<Country> countries = countryService.getAll();
        List<CountryResponse> res = countries.stream().map(dtoMapper::apply).toList();
        return ResponseEntity.ok().body(res);
    }

}
