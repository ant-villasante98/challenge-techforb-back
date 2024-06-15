package com.techforb.techforb_webapi.core.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantCreateRequest {
    private String name;
    private String countryName;
    private String contryFlag;
}
