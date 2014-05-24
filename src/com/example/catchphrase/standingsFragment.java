package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class standingsFragment extends Fragment {
	
	private Button nextRoundButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void startNextRound() {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if(fragment == null) {
			fragment = new gameFragment();
			fm.beginTransaction()
				.add(R.id.fragmentContainer, fragment)
				.commit();
		}
		else {
			fragment = new gameFragment();
			fm.beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.commit();
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.standings_fragment, parent, false);
		
		nextRoundButton = (Button) v.findViewById(R.id.start_next_round_button);
		nextRoundButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startNextRound();
			}
		});
		
		return v;
	}
	
}
