package com.abernathy.mediscreen.mpatient.exception;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@PropertySource("classpath:messages.properties")
@ExtendWith(MockitoExtension.class)
class ControllerAdvisorTest {

    @Value("${patient.error.patientnotfound}")
    private String patientNotFoundErrorMessage;

    @Value("${patient.error.invaliddateformat}")
    private String invalidDateFormatErrorMessage;

    @InjectMocks
    public ControllerAdvisor controllerAdvisor;

    @Mock
    WebRequest webRequest;

    @Mock
    BindingResult bindingResult;

    @Mock
    MethodParameter methodParameter;

    @Test
    void handlePatientNotFoundExceptionTest() {
        // Arrange
        PatientNotFoundException ex = new PatientNotFoundException();
        // Act
        ResponseEntity<Object> result = controllerAdvisor.handlePatientNotFoundException(ex, webRequest);
        // Assert
        assertThat(result.getBody()).isEqualTo(patientNotFoundErrorMessage);
    }

    @Test
    void handleDateFormatExceptionTest() {
        // Arrange
        DateFormatException ex = new DateFormatException();
        // Act
        ResponseEntity<Object> result = controllerAdvisor.handleDateFormatException(ex, webRequest);
        // Assert
        assertThat(result.getBody()).isEqualTo(invalidDateFormatErrorMessage);
    }

    @Test
    void handleMethodArgumentNotValidTest() {
        // Arrange
        String testMessage1 = RandomString.make(64);
        String testMessage2 = RandomString.make(64);
        FieldError fieldError1 = new FieldError("", "", testMessage1);
        FieldError fieldError2 = new FieldError("", "", testMessage2);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);
        // Act
        ResponseEntity<Object> result = controllerAdvisor.handleMethodArgumentNotValid(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST,  webRequest);
        // Assert
        List<String> results = (List<String>) result.getBody();
        assertThat(results).hasSize(2);
        assertThat(results.get(0)).isEqualTo(testMessage1);
        assertThat(results.get(1)).isEqualTo(testMessage2);
    }

}
