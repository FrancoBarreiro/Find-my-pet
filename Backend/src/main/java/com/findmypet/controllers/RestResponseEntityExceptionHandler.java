package com.findmypet.controllers;

import com.findmypet.exceptions.BadRequestException;
import com.findmypet.exceptions.ErrorResponse;
import com.findmypet.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handlerBadRequest(BadRequestException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Bad Request", ex.getMessage(), LocalDateTime.now()),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Resource Not Found", ex.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handlerResourceNotFoundException(java.lang.NullPointerException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Resource Not Found", ex.getMessage(), LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handlerDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorResponse("Check the fields", ex.getMessage(),
                LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
}
