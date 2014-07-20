package com.example.catchphrase;

public class ScoreKeeper {
	
	private int blueTeamScore;
	private int redTeamScore;
	private int currentRound;
	private int totalRounds;
	private String lastWinner;
	
	public ScoreKeeper(int rounds) {
		blueTeamScore = 0;
		redTeamScore = 0;
		currentRound = 1;
		totalRounds = rounds;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	public int getTotalRounds() {
		return totalRounds;
	}
	
	public int getBlueTeamScore() {
		return blueTeamScore;
	}
	
	public int getRedTeamScore() {
		return redTeamScore;
	}
	
	public void registerRebuttal() {
		if(lastWinner == "Red") {
			redTeamScore++;
		}
		else {
			blueTeamScore++;
		}
	}
	
	public void updateScore(int amount, String team) {
		lastWinner = team;
		if(team == "Red") {
			redTeamScore++;
		}
		else {
			blueTeamScore++;
		}
	}
	
	public void updateRound() {
		currentRound++;
	}

}
