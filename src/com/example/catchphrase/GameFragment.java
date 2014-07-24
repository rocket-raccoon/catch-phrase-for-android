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

public class GameFragment extends InGameFragment {
	
	private Button skipButton;
	private Button pauseButton;
	private Button nextButton;
	private TextView currentPhrase;
	private int skipCount = 0;
	private int maxSkipsAllowed = 2;
	private WordBank words = new WordBank();
	
	public static String END_ROUND = "End Round";
	
	private class runTimer extends AsyncTask<Void, Void, Integer> {
		@Override
		protected Integer doInBackground(Void... params) {
			RoundTimer timer = new RoundTimer(getActivity());
			timer.startTimer();
			return 0;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
			mCallback.goNext(END_ROUND);
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
		
		pauseButton = (Button) v.findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
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







