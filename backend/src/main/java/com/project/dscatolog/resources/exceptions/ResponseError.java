package com.project.dscatolog.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ResponseError implements Serializable {

    private String message;
    private String details;
    private Integer status;
    private Instant timestamp;

    public ResponseError() {
    }

    public ResponseError(String message, String details, Integer status, Instant timestamp) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
