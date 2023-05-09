package com.example.demobackend.exception;

public class AlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1873898798440464405L;

    public AlreadyUsedException() {
        super();
    }

    public AlreadyUsedException(String message) {
        super(message);
    }
}
