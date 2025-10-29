package com.java.labs.exceptions;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
