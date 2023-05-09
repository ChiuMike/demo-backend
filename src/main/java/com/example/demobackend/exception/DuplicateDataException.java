package com.example.demobackend.exception;

public class DuplicateDataException extends RuntimeException{

    private static final long serialVersionUID = 968351595829878054L;

    public DuplicateDataException() { super(); }

    public DuplicateDataException(String message) { super(message); }
}
