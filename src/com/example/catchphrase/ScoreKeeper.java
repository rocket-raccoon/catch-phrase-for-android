package com.example.catchphrase;

public class ScoreKeeper {
	
	private int blueTeamScore;
	private int redTeamScore;
	private int currentRound;
	private int totalRounds;
	
	public ScoreKeeper(int rounds) {
		blueTeamScore = 0;
		redTeamScore = 0;
		currentRound = 1;
		totalRounds = rounds;
	}
	
	private int getBlueTeamScore() {
		return blueTeamScore;
	}
	
	private int getRedTeamScore() {
		return redTeamScore;
	}
	
	private void updateScore(int amount, String team) {
		if(team == "Red") {
			redTeamScore++;
		}
		else {
			blueTeamScore++;
		}
	}
	
	private void updateRound() {
		currentRound++;
	}

}
