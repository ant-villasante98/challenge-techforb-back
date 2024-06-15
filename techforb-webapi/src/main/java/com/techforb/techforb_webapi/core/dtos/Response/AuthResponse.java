package com.techforb.techforb_webapi.core.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private long expirationTime;
    private String refreshToken;
    private String type;
}
