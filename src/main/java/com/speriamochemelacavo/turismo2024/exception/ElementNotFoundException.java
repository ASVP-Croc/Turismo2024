package com.speriamochemelacavo.turismo2024.exception;

public class ElementNotFoundException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8566338657082162730L;

	public ElementNotFoundException() {
        super("Not found!");
    }

    public ElementNotFoundException(String message) {
        super();
    }
}
