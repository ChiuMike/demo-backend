package com.example.demobackend.exception;

public class DataConflictException extends RuntimeException {

    private static final long serialVersionUID = -3713469032045095869L;

    public DataConflictException() {
        super();
    }

    public DataConflictException(String message) {
        super(message);
    }
}
