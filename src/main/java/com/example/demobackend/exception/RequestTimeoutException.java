package com.example.demobackend.exception;

public class RequestTimeoutException extends RuntimeException{

    private static final long serialVersionUID = 4284621228317321577L;

    public RequestTimeoutException() { super(); }

    public RequestTimeoutException(String message) { super(message); }
}
