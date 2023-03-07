package com.abernathy.mediscreen.mpatient.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@PropertySource("classpath:messages.properties")
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Value("${patient.errormessage.patientnotfound}")
    private String patientNotFoundErrorMessage;

    @Value("${patient.errormessage.invaliddateformat}")
    private String invalidDateFormatErrorMessage;

    @ExceptionHandler(PatientNotFoundException.class)
    protected ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex,
                                                                        WebRequest request) {
        return handleExceptionInternal(ex, patientNotFoundErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DateFormatException.class)
    protected ResponseEntity<Object> handleDateFormatException(DateFormatException ex,
                                                                        WebRequest request) {
        return handleExceptionInternal(ex, invalidDateFormatErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



}
