package com.example.catchphrase;

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
	
	public Fragment nextFragment(String whatsNext) {
		if(whatsNext == homeFragment.RULES) {
			return new rulesFragment();
		}
		else if(whatsNext == homeFragment.START_GAME) {
			return new gameFragment();
		}
		else if(whatsNext == gameFragment.END_ROUND) {
			return new endRoundFragment();
		}
		else if(whatsNext == endRoundFragment.REBUTTAL_AFTER_BLUE_VICTORY) {
			sk.updateScore(1, "Blue");
			return new rebuttalFragment();
		}
		else if(whatsNext == endRoundFragment.REBUTTAL_AFTER_RED_VICTORY) {
			sk.updateScore(1, "Red");
			return new rebuttalFragment();
		}
		else if(whatsNext == rebuttalFragment.REBUTTAL_YES || whatsNext == rebuttalFragment.REBUTTAL_NO) {
			//We add another point if rebuttal was successful
			if(whatsNext == rebuttalFragment.REBUTTAL_YES) {
				sk.registerRebuttal();
			}
			//Set the fragment arguments to display correct standings and return it
			Bundle args = new Bundle();
			args.putInt(standingsFragment.BLUE_SCORE, sk.getBlueTeamScore());
			args.putInt(standingsFragment.RED_SCORE, sk.getRedTeamScore());
			args.putInt(standingsFragment.CURRENT_ROUND, sk.getCurrentRound());
			args.putInt(standingsFragment.MAX_ROUND, sk.getTotalRounds());
			Fragment frag = new standingsFragment();
			frag.setArguments(args);
			return frag;
		}
		else if(whatsNext == standingsFragment.NEXT_ROUND) {
			if(sk.gameEnded() == true) {
				Fragment frag = new EndGameFragment();
				Bundle args = new Bundle();
				args.putString(EndGameFragment.WINNER, sk.getWinner());
				frag.setArguments(args);
				return frag;
			}
			sk.updateRound();
			return new gameFragment();
		}
		else {
			sk = new ScoreKeeper(1);
			return new homeFragment();
		}
	}
	
	public void goNext(String whatsNext) {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if(fragment == null) {
			fragment = nextFragment(whatsNext);
			fm.beginTransaction()
				.add(R.id.fragmentContainer, fragment)
				.commit();
		}
		else{
			fragment = nextFragment(whatsNext);
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
