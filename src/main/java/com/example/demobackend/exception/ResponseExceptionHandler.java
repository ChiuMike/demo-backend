package com.example.demobackend.exception;

import com.example.demobackend.http.Result;
import com.example.demobackend.http.ResultStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyInitializedException.class)
    protected ResponseEntity<Object> handleAlreadyInitializedException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.ALREADY_INITIALIZED), HttpStatus.OK);
    }

    @ExceptionHandler(AlreadyUsedException.class)
    protected ResponseEntity<Object> handleAlreadyUsedException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.ALREADY_USED), HttpStatus.OK);
    }

    @ExceptionHandler(AuthenticationContextException.class)
    protected ResponseEntity<Object> handleAuthenticationContextException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.AUTHENTICATION_CONTEXT_ERROR),
                HttpStatus.OK);
    }

    @ExceptionHandler(DataConflictException.class)
    protected ResponseEntity<Object> handleDataConflictException(
            DataConflictException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.DATA_CONFLICT), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDataException.class)
    protected ResponseEntity<Object> handleDuplicateDataException(DuplicateDataException ex) {
        return new ResponseEntity(Result.of(ResultStatus.DUPLICATE_DATA), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataProtectionException.class)
    protected ResponseEntity<Object> handleDataProtectionException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.DATA_PROTECTION), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalDeleteException.class)
    protected ResponseEntity<Object> handleIllegalDeleteException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.ILLEGAL_DELETE), HttpStatus.OK);
    }

    @ExceptionHandler(NotImplementedException.class)
    protected ResponseEntity<Object> handleNotImplementedException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.NOT_IMPLEMENTED), HttpStatus.OK);
    }

    @ExceptionHandler(RateLimitException.class)
    protected ResponseEntity<Object> handleRateLimitException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(Result.of(ResultStatus.RATE_LIMIT_EXCEEDED), HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(
            NoSuchElementException ex,
            WebRequest request
    ) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbiddenException(
            ForbiddenException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ServiceUnAvailableException.class)
    protected ResponseEntity<Object> handleServiceUnAvailableException(
            ServiceUnAvailableException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RequestTimeoutException.class)
    protected ResponseEntity<Object> handleRequestTimeoutException(
            RequestTimeoutException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(
            NotFoundException ex,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex,
            WebRequest request
    ) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception ex, WebRequest request) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(Exception ex, WebRequest request) {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleSignatureException(Exception ex, WebRequest request) {
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        return new ResponseEntity(
                "Exception has occurred during processing request (" + ex.toString() + ")",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
