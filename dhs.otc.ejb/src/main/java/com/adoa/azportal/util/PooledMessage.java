package com.adoa.azportal.util;

public class PooledMessage {
	
	private Severity severity;

	private String message;
	
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public PooledMessage(Severity severity, String message) {
		super();
		this.severity = severity;
		this.message = message;
	}

	@Override
	public String toString() {
		return "PooledMessage [severity=" + severity + ", message=" + message + "]";
	}

	
	

}
