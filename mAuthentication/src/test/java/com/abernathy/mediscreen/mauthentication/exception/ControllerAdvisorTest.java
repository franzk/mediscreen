package com.abernathy.mediscreen.mauthentication.exception;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    @InjectMocks
    ControllerAdvisor controllerUnderTest;

    @Mock
    WebRequest webRequest;

    @Test
    void handleBadCredentialsExceptionTest() {
        // Act
        ResponseEntity<Object> result =
                controllerUnderTest.handleBadCredentialsException(
                        new BadCredentialsException(RandomString.make(64)),
                        webRequest
                );
        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void handleInvalidAccessExceptionTest() {
        // Act
        ResponseEntity<Object> result =
                controllerUnderTest.handleInvalidAccessException(new InvalidAccessException(), webRequest);
        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void handleUserAlreadyExistsExceptionTest() {
        // Act
        ResponseEntity<Object> result =
                controllerUnderTest.handleUserAlreadyExistsException(new UserAlreadyExistsException(), webRequest);
        // Assert
        assertThat(result).isNotNull();
    }

    @Test
    void handleSQLExceptionTest() {
        // Act
        ResponseEntity<Object> result =
                controllerUnderTest.handleSQLException(new SQLException(), webRequest);
        // Assert
        assertThat(result).isNotNull();
    }

}
