package com.techforb.techforb_webapi.core.services;

import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    void registre(RegisterUserRequest userRequest);

    AuthResponse refresh(String refreshToken);
}
