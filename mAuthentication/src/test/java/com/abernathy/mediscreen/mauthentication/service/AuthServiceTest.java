package com.abernathy.mediscreen.mauthentication.service;

import com.abernathy.mediscreen.mauthentication.dto.AuthRequestDto;
import com.abernathy.mediscreen.mauthentication.exception.UserAlreadyExistsException;
import com.abernathy.mediscreen.mauthentication.model.User;
import com.abernathy.mediscreen.mauthentication.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService serviceUnderTest;

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Test
    void generateTokenTest() {
        // Arrange
        String testUsername = RandomString.make(64);
        // Act
        serviceUnderTest.generateToken(testUsername);
        // Assert
        verify(jwtService, times(1)).generateToken(testUsername);
    }

    @Test
    void validateTokenTest() {
        // Arrange
        String testToken = RandomString.make(64);
        // Act
        serviceUnderTest.validateToken(testToken);
        // Assert
        verify(jwtService, times(1)).validateToken(testToken);
    }

    @Test
    void createUserTest() throws UserAlreadyExistsException {
        // Arrange
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        // Act
        serviceUnderTest.createUser(new AuthRequestDto());
        // Assert
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void createUserWithUserAlreadyExistsExceptionTest() {
        // Arrange
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));
        // Act + Assert
        assertThrows(UserAlreadyExistsException.class, () -> serviceUnderTest.createUser(new AuthRequestDto()));
    }


}
