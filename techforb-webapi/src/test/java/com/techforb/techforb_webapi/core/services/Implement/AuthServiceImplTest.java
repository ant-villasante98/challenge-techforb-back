package com.techforb.techforb_webapi.core.services.Implement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.hash.Hashing;
import com.techforb.techforb_webapi.core.dtos.Request.LoginRequest;
import com.techforb.techforb_webapi.core.dtos.Request.RegisterUserRequest;
import com.techforb.techforb_webapi.core.dtos.Response.AuthResponse;
import com.techforb.techforb_webapi.core.models.RefreshToken;
import com.techforb.techforb_webapi.core.models.User;
import com.techforb.techforb_webapi.core.reposittories.UserRepository;
import com.techforb.techforb_webapi.core.services.JwtService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    JwtService jwtService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl authService;

    @Test
    void testLogin() {
        // Arrange
        String email = "user@email.com";
        String password = "password";
        LoginRequest login = new LoginRequest(email, password);
        User foundUser = new User(3, email, Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString(), "antonio", "apellido", null);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(foundUser));
        when(jwtService.generateToken(foundUser)).thenReturn("asdfasdfasdjfhsduwejkwerkj");
        when(jwtService.generateRefreshToken()).thenReturn("kcxm,czxkjcxzm,zxcvlkjsad");

        // Act
        AuthResponse result = authService.login(login);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).saveAndFlush(any(User.class));

    }

    @Test
    void testRefresh() {
        // Arrange
        String refreshToken = "sjksjsdjklsds";
        Date expiration = new Date(System.currentTimeMillis() + 1000);
        User user = new User(2, "user@email.com", "password", "nombre", "apellido",
                new RefreshToken(refreshToken, expiration));

        when(userRepository.findByRefreshToken(refreshToken)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(any(User.class))).thenReturn("asdfasdfasdjfhsduwejkwerkj");
        when(jwtService.generateRefreshToken()).thenReturn("kcxm,czxkjcxzm,zxcvlkjsad");

        // Act
        AuthResponse result = authService.refresh(refreshToken);

        // Assert
        verify(userRepository, times(1)).findByRefreshToken(refreshToken);
        verify(userRepository, times(1)).saveAndFlush(user);
        assertNotNull(result);
    }

    @Test
    void testRegistre() {
        String email = "user@email.com";
        String password = "password";
        String codenPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        // Arrange
        RegisterUserRequest userRegister = new RegisterUserRequest(email, password, "antonio",
                "villasante");

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        authService.registre(userRegister);

        // Assert
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertEquals(email, capturedUser.getEmail());
        assertEquals(codenPassword, capturedUser.getPassword());
        assertEquals(userRegister.getFirstName(), capturedUser.getFirstName());
        assertEquals(userRegister.getLastName(), capturedUser.getLastName());

    }
}
