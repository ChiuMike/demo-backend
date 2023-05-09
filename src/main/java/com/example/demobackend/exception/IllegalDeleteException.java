package com.example.demobackend.exception;

public class IllegalDeleteException extends RuntimeException {

    private static final long serialVersionUID = 7229969104976447118L;

    public IllegalDeleteException() {
        super();
    }

    public IllegalDeleteException(String message) {
        super(message);
    }
}
