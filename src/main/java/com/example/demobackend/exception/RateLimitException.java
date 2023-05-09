package com.example.demobackend.exception;

public class RateLimitException extends RuntimeException {

    private static final long serialVersionUID = -4581062297520188957L;

    public RateLimitException() {
        super();
    }

    public RateLimitException(String message) {
        super(message);
    }
}
