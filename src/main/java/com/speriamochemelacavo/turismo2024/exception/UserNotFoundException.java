package com.speriamochemelacavo.turismo2024.exception;

public class UserNotFoundException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2430817064709335531L;

	public UserNotFoundException() {
        super("Not found!");
    }

    public UserNotFoundException(String message) {
        super();
    }
}
