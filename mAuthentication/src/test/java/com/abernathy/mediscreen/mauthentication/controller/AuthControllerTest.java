package com.abernathy.mediscreen.mauthentication.controller;

import com.abernathy.mediscreen.mauthentication.dto.AuthRequestDto;
import com.abernathy.mediscreen.mauthentication.dto.AuthTokenDto;
import com.abernathy.mediscreen.mauthentication.exception.InvalidAccessException;
import com.abernathy.mediscreen.mauthentication.exception.UserAlreadyExistsException;
import com.abernathy.mediscreen.mauthentication.service.AuthService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController controllerUnderTest;

    @Mock
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void addNewUserTest() throws UserAlreadyExistsException {
        // Arrange
        String expectedResult = RandomString.make(64);
        when(authService.createUser(any())).thenReturn(expectedResult);
        // Act
        ResponseEntity<String> result = controllerUnderTest.addNewUser(new AuthRequestDto());
        // Assert
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    void getTokenTest() throws InvalidAccessException {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        // Act
        ResponseEntity<AuthTokenDto> result = controllerUnderTest.getToken(new AuthRequestDto());
        // Assert
        verify(authService, times(1)).generateToken(any());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    void getTokenWithInvalidAccessExceptionTest() {
        // Arrange
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);
        // Act + Assert
        assertThrows(InvalidAccessException.class, () -> controllerUnderTest.getToken(new AuthRequestDto()));
    }

    @Test
    void validateTokenTest() {
        // Arrange
        String testToken = RandomString.make(64);
        // Act
        controllerUnderTest.validateToken(testToken);
        // Assert
        verify(authService, times(1)).validateToken(testToken);
    }




}
