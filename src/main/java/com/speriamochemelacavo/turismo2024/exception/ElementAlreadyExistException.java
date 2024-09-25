package com.speriamochemelacavo.turismo2024.exception;

public class ElementAlreadyExistException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6810085633311308853L;
	public ElementAlreadyExistException() {
        super("Already Exist!!");
    }

    public ElementAlreadyExistException(String message) {
    }
}
