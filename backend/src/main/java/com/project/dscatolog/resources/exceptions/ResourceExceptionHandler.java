package com.project.dscatolog.resources.exceptions;

import com.project.dscatolog.services.exceptions.DatabaseIntegrityException;
import com.project.dscatolog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

// Essa anotação permite que esta classe(ResourceExceptionHandler) intercepte erros na camada de recursos(controller)
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handlerEntityNotFound(ResourceNotFoundException e, WebRequest request) {
        ResponseError error = new ResponseError();
            error.setMessage(e.getMessage());
            error.setDetails(request.getDescription(false));
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseIntegrityException.class)
    public ResponseEntity<ResponseError> handlerDatabaseIntegrity(DatabaseIntegrityException e, WebRequest request) {
        ResponseError error = new ResponseError();
        error.setMessage(e.getMessage());
        error.setDetails(request.getDescription(false));
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
