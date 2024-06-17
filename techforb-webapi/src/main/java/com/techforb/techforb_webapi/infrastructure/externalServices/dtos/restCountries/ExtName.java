package com.techforb.techforb_webapi.infrastructure.externalServices.dtos.restCountries;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtName {
    private String common;
    private Map<String, ExtTranslation> nativeName;
    private String official;
}
