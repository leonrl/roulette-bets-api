package com.masivian.dto;

import java.util.ArrayList;
import java.util.List;
import com.masivian.model.Bet;

public class RouletteClosingDto {
    private int totalWinners;
    private int totalLosers;
    private List<Bet> bets = new ArrayList<>();
	public RouletteClosingDto() {
	}
	public int getTotalWinners() {
		return totalWinners;
	}
	public void setTotalWinners(int totalWinners) {
		this.totalWinners = totalWinners;
	}
	public int getTotalLosers() {
		return totalLosers;
	}
	public void setTotalLosers(int totalLosers) {
		this.totalLosers = totalLosers;
	}
	public List<Bet> getBets() {
		return bets;
	}
	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}
}
