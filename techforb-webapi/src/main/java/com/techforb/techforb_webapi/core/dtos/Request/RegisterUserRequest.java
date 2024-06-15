package com.techforb.techforb_webapi.core.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
