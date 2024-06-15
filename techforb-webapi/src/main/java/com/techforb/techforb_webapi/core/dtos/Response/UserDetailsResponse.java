package com.techforb.techforb_webapi.core.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {

    private long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
