package com.example.demobackend.exception;

public class ServiceUnAvailableException extends RuntimeException{

    private static final long serialVersionUID = 6062297673649864907L;

    public ServiceUnAvailableException() { super(); }

    public ServiceUnAvailableException(String message) { super(message); }
}
