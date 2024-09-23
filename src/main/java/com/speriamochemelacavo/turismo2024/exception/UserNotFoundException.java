package com.speriamochemelacavo.turismo2024.exception;

public class UserNotFoundException extends Exception{
    private String message;

    public UserNotFoundException() {
        super("Not found!");
    }

    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }
}
