package com.techforb.techforb_webapi.webapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RefreshTokenRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;
import com.techforb.techforb_webapi.core.services.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserRequest request) {
        authService.registre(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
        AuthResponse response = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok().body(response);
    }

}
