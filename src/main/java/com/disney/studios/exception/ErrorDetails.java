package com.disney.studios.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
public class ErrorDetails {
public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
public ErrorDetails(Date timestamp, String message) {
	super();
	this.timestamp = timestamp;
	this.message = message;
}
private Date timestamp;
private String message;
private String details;
public Date getTimestamp() {
	return timestamp;
}
public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
}
