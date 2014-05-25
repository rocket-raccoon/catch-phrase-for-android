package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class gameFragment extends Fragment {
	
	private Button skipButton;
	private Button pauseButton;
	private Button nextButton;
	private TextView currentPhrase;
	private int skipCount = 0;
	private int maxSkipsAllowed = 2;
	private wordBank words = new wordBank();
	
	public void endRound() {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if(fragment == null) {
			fragment = new endRoundFragment();
			fm.beginTransaction()
				.add(R.id.fragmentContainer, fragment)
				.commit();
		}
		else{
			fragment = new endRoundFragment();
			fm.beginTransaction()
				.replace(R.id.fragmentContainer, fragment)
				.commit();
		}
	}
	
	private class runTimer extends AsyncTask<Void, Void, Integer> {
		@Override
		protected Integer doInBackground(Void... params) {
			roundTimer timer = new roundTimer(getActivity());
			timer.startTimer();
			return 0;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			Toast.makeText(getActivity(), "Round is over!", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new runTimer().execute();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.game_fragment, parent, false);
		
		currentPhrase = (TextView) v.findViewById(R.id.currentPhrase);
		currentPhrase.setText(words.getPhrase());
		
		skipButton = (Button) v.findViewById(R.id.skipButton);
		skipButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(skipCount < maxSkipsAllowed) {
					skipCount++;
					currentPhrase.setText(words.getPhrase());
				}
				else {
					Toast.makeText(getActivity(), "You've exceeded the max number of skips this turn", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		nextButton = (Button) v.findViewById(R.id.nextButton);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentPhrase.setText(words.getPhrase());
			}
		});
		
		return v;
	}
}







