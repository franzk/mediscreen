package com.abernathy.mediscreen.mauthentication.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex,
                                                                    WebRequest request) {
        log.info("Bad Credentials !");
        return handleExceptionInternal(ex, "Bad Credentials", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(InvalidAccessException.class)
    protected ResponseEntity<Object> handleInvalidAccessException(InvalidAccessException ex,
                                                                   WebRequest request) {
        return handleExceptionInternal(ex, "Invalid Access", new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleUserAlreadyExistsExceptionException(UserAlreadyExistsException ex,
                                                                  WebRequest request) {
        return handleExceptionInternal(ex, "User already exists ", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
