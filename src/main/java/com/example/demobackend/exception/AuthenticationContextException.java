package com.example.demobackend.exception;

public class AuthenticationContextException extends RuntimeException {

    private static final long serialVersionUID = -3591541282420360093L;

    public AuthenticationContextException() {
        super();
    }

    public AuthenticationContextException(String message) {
        super(message);
    }
}
