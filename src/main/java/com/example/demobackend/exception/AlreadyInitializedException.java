package com.example.demobackend.exception;

public class AlreadyInitializedException extends RuntimeException {

    private static final long serialVersionUID = -6921330662037571855L;

    public AlreadyInitializedException() {
        super();
    }

    public AlreadyInitializedException(String message) {
        super(message);
    }
}
