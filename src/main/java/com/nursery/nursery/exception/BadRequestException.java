package com.nursery.nursery.exception;

public class BadRequestException
        extends RuntimeException {

    public BadRequestException(
            String message
    ) {
        super(message);
    }
}