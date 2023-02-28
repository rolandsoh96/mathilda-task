package com.example.restservice.exception;

public class MaxClassSizeExceededException extends RuntimeException {
    public MaxClassSizeExceededException(String message) {
        super(message);
    }
}
