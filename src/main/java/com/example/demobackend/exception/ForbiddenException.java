package com.example.demobackend.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = -5202935603692317110L;

    public ForbiddenException() { super(); }

    public ForbiddenException(String message) { super(message); }
}
