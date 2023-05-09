package com.example.demobackend.exception;

public class DataProtectionException extends RuntimeException {

    private static final long serialVersionUID = 2331190491293287257L;

    public DataProtectionException() {
        super();
    }

    public DataProtectionException(String message) {
        super(message);
    }
}
