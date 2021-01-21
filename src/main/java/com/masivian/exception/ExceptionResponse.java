package com.masivian.exception;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {

	private String error;
	private String message;
	private String path;
	private int status;
	private Date timestamp;
	private List<String> ListaCamposObligatorios;

	public ExceptionResponse(String error, String message, String path, int status, Date timestamp,
			List<String> ListaCamposObligatorios) {
		this.error = error;
		this.message = message;
		this.path = path;
		this.status = status;
		this.ListaCamposObligatorios = ListaCamposObligatorios;
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getListaCamposObligatorios() {
		return ListaCamposObligatorios;
	}

	public void setListaCamposObligatorios(List<String> listaCamposObligatorios) {
		ListaCamposObligatorios = listaCamposObligatorios;
	}

}
