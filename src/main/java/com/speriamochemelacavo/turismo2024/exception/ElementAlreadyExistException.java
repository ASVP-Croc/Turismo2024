package com.speriamochemelacavo.turismo2024.exception;

public class ElementAlreadyExistException extends Exception {
    private String message;

    public ElementAlreadyExistException() {
        super("Already Exist!!");
    }

    public ElementAlreadyExistException(String message) {
        this.message = message;
    }
}
