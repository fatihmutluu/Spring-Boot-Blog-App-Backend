package com.fatih.blogrestapi.dto;

import java.util.Date;

public class ErrorDetails {

    Date timestamp;
    String message;
    String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
