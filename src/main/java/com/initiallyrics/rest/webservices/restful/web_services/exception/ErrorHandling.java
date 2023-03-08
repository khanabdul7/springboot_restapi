package com.initiallyrics.rest.webservices.restful.web_services.exception;

import java.time.LocalDateTime;


public class ErrorHandling {

	private LocalDateTime timeStamp;
	private String message;
	private String description;

	public ErrorHandling(LocalDateTime timeStamp, String message, String description) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.description = description;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	
}
