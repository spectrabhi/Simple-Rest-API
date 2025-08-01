package com.spectrabhi.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private Object details;

    public ErrorDetails(LocalDateTime timestamp, String message, Object details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Object getDetails() {
        return details;
    }
}
