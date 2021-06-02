package org.example.controller;

import org.example.domain.ResponseContainer;
import org.example.repository.NotFoundException;
import org.example.session.NoAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ResponseContainer> handleNotFound(NotFoundException ex) {
        return ResponseContainer.of(404, ex.getMessage());
    }

    @ExceptionHandler(NoAccessException.class)
    ResponseEntity<ResponseContainer> handleNoAccess(NoAccessException ex) {
        return ResponseContainer.of(403, ex.getMessage());
    }

}
