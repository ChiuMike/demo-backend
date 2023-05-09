package com.example.demobackend.exception;

public class NotImplementedException extends UnsupportedOperationException {

    private static final long serialVersionUID = 706392001665493901L;

    public NotImplementedException() {
        super();
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
