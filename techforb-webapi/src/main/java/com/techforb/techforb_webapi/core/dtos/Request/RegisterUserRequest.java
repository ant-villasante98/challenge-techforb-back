package com.techforb.techforb_webapi.core.dtos.Request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @Email(message = "El email es invalido.")
    @NotBlank(message = "El email es requerido.")
    private String email;

    @Length(min = 8, message = "Debe tener 8 caracteres o mas.")
    @NotBlank(message = "La contraseña es requerida.")
    private String password;

    @NotBlank(message = "El nombre es requerido.")
    private String firstName;

    @NotBlank(message = "El apellido es requerido.")
    private String lastName;
}
