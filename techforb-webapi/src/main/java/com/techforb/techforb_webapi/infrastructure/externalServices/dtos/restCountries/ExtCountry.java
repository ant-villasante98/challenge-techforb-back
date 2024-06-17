package com.techforb.techforb_webapi.infrastructure.externalServices.dtos.restCountries;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtCountry {
    private ExtFlags flags;
    private ExtName name;
    private Map<String, ExtTranslation> translations;
}
