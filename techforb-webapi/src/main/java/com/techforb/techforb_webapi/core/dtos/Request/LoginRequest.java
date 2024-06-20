package com.techforb.techforb_webapi.core.dtos.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "El email es requerido.")
    @Email(message = "El email es invalido.")
    private String email;

    @NotBlank(message = "La contraseña es requerido.")
    private String password;
}
