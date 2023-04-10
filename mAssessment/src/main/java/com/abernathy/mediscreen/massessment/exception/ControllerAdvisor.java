package com.abernathy.mediscreen.massessment.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    protected ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex,
                                                                    WebRequest request) {
        return handleExceptionInternal(ex, "Patient not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
