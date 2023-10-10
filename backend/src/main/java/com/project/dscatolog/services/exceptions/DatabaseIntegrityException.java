package com.project.dscatolog.services.exceptions;

public class DatabaseIntegrityException extends RuntimeException {

    public DatabaseIntegrityException(String message) {
        super(message);
    }
}
