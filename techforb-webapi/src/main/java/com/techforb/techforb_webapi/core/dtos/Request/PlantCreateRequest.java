package com.techforb.techforb_webapi.core.dtos.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlantCreateRequest {
    @NotBlank(message = "El Nombre es requerido.")
    private String name;
    private String countryName;
    private String countryFlag;
}
