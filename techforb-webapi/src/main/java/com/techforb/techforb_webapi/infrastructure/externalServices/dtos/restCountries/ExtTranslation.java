package com.techforb.techforb_webapi.infrastructure.externalServices.dtos.restCountries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtTranslation {
    private String common;
    private String official;
}
