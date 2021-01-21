package com.masivian.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bet implements Serializable {
	private static final long serialVersionUID = 7821110451301315929L;
    private String id;
    private int number;
    private String color;
    private double money;
    @JsonIgnore
	private User user;
	public Bet() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
