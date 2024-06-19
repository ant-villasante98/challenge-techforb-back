package com.techforb.techforb_webapi.core.services.Implement;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Optional;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.google.common.hash.Hashing;
import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;
import com.techforb.techforb_webapi.core.exceptions.UnauthorizedException;
import com.techforb.techforb_webapi.core.models.RefreshToken;
import com.techforb.techforb_webapi.core.models.User;
import com.techforb.techforb_webapi.core.reposittories.UserRepository;
import com.techforb.techforb_webapi.core.services.AuthService;
import com.techforb.techforb_webapi.core.services.JwtService;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

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

        // authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(
        // loginRequest.getEmail(),
        // loginRequest.getPassword()));

        User foundUser = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UnauthorizedException());

        if (!foundUser.getPassword().equals(encodePassword(loginRequest.getPassword()))) {
            throw new UnauthorizedException();
        }

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
                .password(encodePassword(userRequest.getPassword())).refreshToken(null).build();

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

    private String encodePassword(String value) {

        String result = Hashing.sha256()
                .hashString(value, StandardCharsets.UTF_8)
                .toString();
        return result;
    }

}
