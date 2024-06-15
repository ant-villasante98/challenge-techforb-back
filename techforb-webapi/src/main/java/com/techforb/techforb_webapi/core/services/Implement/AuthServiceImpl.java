package com.techforb.techforb_webapi.core.services.Implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;
import com.techforb.techforb_webapi.core.models.User;
import com.techforb.techforb_webapi.core.reposittories.UserRepository;
import com.techforb.techforb_webapi.core.services.AuthService;
import com.techforb.techforb_webapi.core.services.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        User foundUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        String accessToken = jwtService.generateToken(foundUser);

        // TODO: Implementar refresh token
        String refreshToken = "refresh-token";

        return new AuthResponse(accessToken, jwtService.getExpirationTime(), refreshToken, "Bearer");
    }

    @Override
    public void registre(RegisterUserRequest userRequest) {
        Optional<User> foundUser = userRepository.findByEmail(userRequest.getEmail());
        if (foundUser.isPresent()) {
            // TODO: Personalizar exception
            throw new RuntimeException("El email ya existe");
        }

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword())).build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refresh'");
    }

}
