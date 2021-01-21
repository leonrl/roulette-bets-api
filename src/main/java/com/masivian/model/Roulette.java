package com.masivian.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Roulette implements Serializable {
	private static final long serialVersionUID = -7737529838459666982L;
	private String id;
    private boolean state;
    private List<Bet> bets = new ArrayList<>();
	public Roulette() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public List<Bet> getBets() {
		return bets;
	}
	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}
}

