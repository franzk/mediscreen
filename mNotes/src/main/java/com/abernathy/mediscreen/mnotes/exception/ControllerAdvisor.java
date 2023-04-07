package com.abernathy.mediscreen.mnotes.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidNoteDataException.class)
    protected ResponseEntity<Object> handleInvalidNoteDataException(InvalidNoteDataException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid data format", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    protected ResponseEntity<Object> handleNoteNotFoundException(NoteNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Note not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
