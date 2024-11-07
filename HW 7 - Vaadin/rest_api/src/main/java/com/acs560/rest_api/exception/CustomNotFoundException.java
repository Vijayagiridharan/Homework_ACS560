package com.acs560.rest_api.exception;

public class CustomNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomNotFoundException(String message) {
        super(message);
    }
}
