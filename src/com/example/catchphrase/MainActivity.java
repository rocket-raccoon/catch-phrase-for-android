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
	
	public static String BLUE_TEAM_SCORE = "Blue Team Score";
	public static String RED_TEAM_SCORE = "Red Team Score";
	
	public static String HOME = "home";
	
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
		else if(whatsNext == endRoundFragment.REBUTTAL) {
			return new rebuttalFragment();
		}
		else if(whatsNext == rebuttalFragment.STANDINGS) {
			return new standingsFragment();
		}
		else if(whatsNext == standingsFragment.NEXT_ROUND) {
			return new gameFragment();
		}
		else {
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
