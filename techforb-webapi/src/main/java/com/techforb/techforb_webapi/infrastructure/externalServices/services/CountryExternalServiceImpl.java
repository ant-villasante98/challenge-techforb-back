package com.techforb.techforb_webapi.infrastructure.externalServices.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techforb.techforb_webapi.core.models.Country;
import com.techforb.techforb_webapi.core.services.CountryService;
import com.techforb.techforb_webapi.infrastructure.externalServices.dtos.restCountries.ExtCountry;

@Service
public class CountryExternalServiceImpl implements CountryService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Country> getAll() {
        ResponseEntity<ExtCountry[]> response = restTemplate
                .getForEntity("https://restcountries.com/v3.1/all?fields=name,flags,translations", ExtCountry[].class);

        ExtCountry[] countries = response.getBody();
        List<ExtCountry> listC = Arrays.asList(countries);
        ExtCountry item = listC.get(0);
        Country test = new Country(item.getTranslations().get("spa").getCommon(), item.getFlags().getPng());
        return listC.stream().map(x -> new Country(x.getTranslations().get("spa").getCommon(),
                x.getFlags().getPng())).toList();
    }

}
