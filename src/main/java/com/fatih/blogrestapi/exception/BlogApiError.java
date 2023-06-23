package com.fatih.blogrestapi.exception;

import org.springframework.http.HttpStatus;

public class BlogApiError extends RuntimeException {

    HttpStatus status;
    String message;

    public BlogApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogApiError(HttpStatus status, String message1, String message2) {
        super(message1);
        this.status = status;
        this.message = message2;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
