package com.techforb.techforb_webapi.core.services.Implement;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;
import com.techforb.techforb_webapi.core.models.RefreshToken;
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

    private final String typeAuth = "Bearer";

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        User foundUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();

        String accessToken = jwtService.generateToken(foundUser);

        String refreshToken = jwtService.generateRefreshToken();

        Date expirationDateRT = new Date(System.currentTimeMillis() + (2 * jwtService.getExpirationTime()));

        foundUser.setRefreshToken(new RefreshToken(refreshToken, expirationDateRT));

        userRepository.saveAndFlush(foundUser);

        return new AuthResponse(accessToken, jwtService.getExpirationTime(), refreshToken, typeAuth);
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
                .password(passwordEncoder.encode(userRequest.getPassword())).refreshToken(null).build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        User foundUser = userRepository.findByRefreshToken(refreshToken).orElseThrow();
        if (new Date().after(foundUser.getRefreshToken().getExpirationTime())) {
            throw new RuntimeException("Unauthorization");
        }

        String accessToken = jwtService.generateToken(foundUser);

        String newRefreshToken = jwtService.generateRefreshToken();

        Date expirationDateRT = new Date(System.currentTimeMillis() + (2 * jwtService.getExpirationTime()));

        foundUser.setRefreshToken(new RefreshToken(newRefreshToken, expirationDateRT));

        userRepository.saveAndFlush(foundUser);
        return new AuthResponse(accessToken, jwtService.getExpirationTime(), newRefreshToken, typeAuth);

    }

}
