package com.techforb.techforb_webapi.core.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.techforb.techforb_webapi.core.dtos.Response.CountryResponse;
import com.techforb.techforb_webapi.core.models.Country;

@Service
public class CountryDtoMapper implements Function<Country, CountryResponse> {

    @Override
    public CountryResponse apply(Country country) {

        return new CountryResponse(country.getName(), country.getFlagImage());
    }

}
