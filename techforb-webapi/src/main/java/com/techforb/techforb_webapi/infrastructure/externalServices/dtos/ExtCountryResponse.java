package com.techforb.techforb_webapi.infrastructure.externalServices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtCountryResponse {
    private ExtCountryFlagResponse flags;
    private ExtTranslationsResponse translations;
}
