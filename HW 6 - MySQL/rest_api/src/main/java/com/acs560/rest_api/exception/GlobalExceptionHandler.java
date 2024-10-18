package com.acs560.rest_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler is a class that handles exceptions thrown by
 * the REST API controllers. It uses Spring's {@link ControllerAdvice}
 * to provide centralized exception handling across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic exceptions thrown by the application.
     * 
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing the error message and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles illegal argument exceptions thrown by the application.
     * 
     * @param ex the IllegalArgumentException that was thrown
     * @return a ResponseEntity containing the error message and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Add more specific exception handlers as needed
}
