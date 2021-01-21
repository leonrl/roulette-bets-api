package com.masivian.dto;

import java.util.Date;
import java.util.List;

public class GenericAnswer {
	private Date timestamp;
	private String message;
	private List<String> errors;
	
	public GenericAnswer() {
	}

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

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
