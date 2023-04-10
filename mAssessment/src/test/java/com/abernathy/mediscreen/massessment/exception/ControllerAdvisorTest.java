package com.abernathy.mediscreen.massessment.exception;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    @InjectMocks
    ControllerAdvisor controllerUnderTest;

    @Mock
    WebRequest webRequest;

    @Test
    void handlePatientNotFoundExceptionTest() {
        // Arrange
        PatientNotFoundException ex = new PatientNotFoundException();
        // Act
        ResponseEntity<Object> result = controllerUnderTest.handlePatientNotFoundException(ex, webRequest);
        // Assert
        assertThat(result.hasBody()).isTrue();
    }

}
