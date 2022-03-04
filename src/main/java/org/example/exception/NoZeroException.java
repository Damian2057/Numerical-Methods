package org.example.exception;

public class NoZeroException extends RuntimeException{
    public NoZeroException(String message) {
        super(message);
    }
}
