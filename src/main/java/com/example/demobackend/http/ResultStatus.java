package com.example.demobackend.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public enum ResultStatus {

    OK(0, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    ALREADY_INITIALIZED(1000, "Already Initialized"),
    ALREADY_USED(1001, "Already Used"),
    RATE_LIMIT_EXCEEDED(2000, "Rate Limit Exceeded"),
    AUTHENTICATION_CONTEXT_ERROR(2100, "Authentication Context Error"),
    NOT_IMPLEMENTED(10000, "Not Implemented"),
    DATA_CONFLICT(10001, "Data Conflict"),
    DATA_PROTECTION(10002, "Data Protection"),
    ILLEGAL_DELETE(10003, "Illegal Delete"),
    DUPLICATE_DATA(10004, "Duplicate Data"),
    VERIFY_ERROR(10005, "Verify error");

    private Integer status;

    private String message;
}