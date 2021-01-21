package com.masivian.dto;

public class RouletteOpeningDto {
    private boolean state;
	private String message;
	public RouletteOpeningDto() {
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
