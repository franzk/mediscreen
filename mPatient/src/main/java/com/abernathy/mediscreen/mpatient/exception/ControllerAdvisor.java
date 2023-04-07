package com.abernathy.mediscreen.mpatient.exception;

import com.abernathy.mediscreen.mdto.exception.DateFormatException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Handle exceptions for API requests
 */
@ControllerAdvice
@PropertySource("classpath:messages.properties")
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Value("${patient.error.patientnotfound}")
    private String patientNotFoundErrorMessage;

    @Value("${patient.error.invaliddateformat}")
    private String invalidDateFormatErrorMessage;


    /**
     * @param ex      {@link PatientNotFoundException}
     * @param request {@link WebRequest}
     * @return patientNotFoundErrorMessage (see message.properties) and status code 400 (BAD REQUEST)
     */
    @ExceptionHandler(PatientNotFoundException.class)
    protected ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex,
                                                                    WebRequest request) {
        return handleExceptionInternal(ex, patientNotFoundErrorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * @param ex      {@link DateFormatException}
     * @param request {@link WebRequest}
     * @return invalidDateFormatErrorMessage (see message.properties) and status code 400 (BAD REQUEST)
     */
    @ExceptionHandler(DateFormatException.class)
    protected ResponseEntity<Object> handleDateFormatException(DateFormatException ex,
                                                               WebRequest request) {
        return handleExceptionInternal(ex, invalidDateFormatErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle constraint violations
     * @param ex Exception contains error messages
     * @param headers
     * @param status
     * @param request
     * @return  List<String> containing error messages relative with entity validity constraint violations
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("handleMethodArgumentNotValid" + ex.getBindingResult().getFieldErrors().toString());
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<String> errorMessages = errors.stream().map(err -> err.getDefaultMessage()).toList();
        return handleExceptionInternal(ex, errorMessages, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLException(SQLException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}