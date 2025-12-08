package com.tmdna.exceptions;

public class IllegalCommandTypeException extends IllegalArgumentException {
    public IllegalCommandTypeException(String message) {
        super(message);
    }
}
