package com.example.demobackend.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1776388244114777193L;

    public NotFoundException() { super(); }

    public NotFoundException(String message) { super(message); }
}
