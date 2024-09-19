package com.speriamochemelacavo.turismo2024.exception;

public class ElementNotFoundException extends Exception {
    private String message;

    public ElementNotFoundException() {
        super("Not found!");
    }

    public ElementNotFoundException(String message) {
        super();
        this.message = message;
    }
}
