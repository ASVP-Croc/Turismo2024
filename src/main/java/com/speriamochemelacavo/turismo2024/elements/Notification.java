package com.speriamochemelacavo.turismo2024.elements;

public class Notification {
	private final String message;
	
	
	public Notification(String text) {
		this.message = text;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	public String addMotivation(String text) {
		return message+text;
	}
}
