package com.masivian.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -3558924557093501969L;
	private String id;
    private String name;
    private double money;
	public User() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}
