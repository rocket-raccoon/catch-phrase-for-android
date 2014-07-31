package com.example.catchphrase;

import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements InGameFragment.GetNextFragment {
	
	public static String HOME = "home";
	public ScoreKeeper sk;
	
	//This interface receives a communication from the current in game fragment
	//Based on the string value received, it returns the next fragment to be displayed
	public Fragment nextFragment(String whatsNext, HashMap<String, Object>... additionalParams) {
		if(whatsNext == HomeFragment.RULES) {
			return new RulesFragment();
		}
		else if(whatsNext == HomeFragment.START_GAME) {
			int numRounds = (Integer) additionalParams[0].get(HomeFragment.NUM_ROUNDS);
			sk = new ScoreKeeper(numRounds);
			return new GameFragment();
		}
		else if(whatsNext == GameFragment.END_ROUND) {
			return new EndRoundFragment();
		}
		else if(whatsNext == EndRoundFragment.REBUTTAL_AFTER_BLUE_VICTORY) {
			sk.updateScore(1, "Blue");
			return new RebuttalFragment();
		}
		else if(whatsNext == EndRoundFragment.REBUTTAL_AFTER_RED_VICTORY) {
			sk.updateScore(1, "Red");
			return new RebuttalFragment();
		}
		else if(whatsNext == RebuttalFragment.REBUTTAL_YES || whatsNext == RebuttalFragment.REBUTTAL_NO) {
			//We add another point if rebuttal was successful
			if(whatsNext == RebuttalFragment.REBUTTAL_YES) sk.registerRebuttal();
			//Set the fragment arguments to display correct standings and return it
			Bundle args = new Bundle();
			args.putInt(StandingsFragment.BLUE_SCORE, sk.getBlueTeamScore());
			args.putInt(StandingsFragment.RED_SCORE, sk.getRedTeamScore());
			args.putInt(StandingsFragment.CURRENT_ROUND, sk.getCurrentRound());
			args.putInt(StandingsFragment.MAX_ROUND, sk.getTotalRounds());
			Fragment frag = new StandingsFragment();
			frag.setArguments(args);
			return frag;
		}
		else if(whatsNext == StandingsFragment.NEXT_ROUND) {
			if(sk.gameEnded() == true) {
				Fragment frag = new EndGameFragment();
				Bundle args = new Bundle();
				args.putString(EndGameFragment.WINNER, sk.getWinner());
				frag.setArguments(args);
				return frag;
			}
			sk.updateRound();
			return new GameFragment();
		}
		else return new HomeFragment();
	}
	
	//We swap out fragments through this function as appropriate
	public void goNext(String whatsNext, HashMap<String, Object>... additionalParams) {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if(fragment == null) {
			if(additionalParams.length > 0) fragment = nextFragment(whatsNext, additionalParams[0]);
			else fragment = nextFragment(whatsNext);
			fm.beginTransaction()
				.add(R.id.fragmentContainer, fragment)
				.commit();
		}
		else{
			if(additionalParams.length > 0) fragment = nextFragment(whatsNext, additionalParams[0]);
			else fragment = nextFragment(whatsNext);
			fm.beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.commit();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		goNext(HOME);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	// Respond to the action bar's Up/Home button
	    	case android.R.id.home:
	    		goNext(HOME);
	    		return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
}
