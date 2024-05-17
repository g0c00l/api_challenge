package com.disney.studios.exception;

import org.springframework.http.HttpStatus;

public class DisneyException extends Exception {

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HttpStatus httpStatus;
	public String detail;
	public String msg;

	public DisneyException() {
		super();
	}

	public DisneyException(HttpStatus httpStatus, String msg) {
		super();
		this.httpStatus = httpStatus;
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public DisneyException(HttpStatus httpStatus, String msg, String detail) {
		super();
		this.httpStatus = httpStatus;
		this.detail = detail;
		this.msg = msg;
	}
}
