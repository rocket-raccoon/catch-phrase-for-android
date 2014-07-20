package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class standingsFragment extends InGameFragment {
	
	private Button nextRoundButton;
	private TextView blueTeamScore;
	private TextView redTeamScore;
	private TextView currentRound;
	
	public static String RED_SCORE = "Red Team's Score";
	public static String BLUE_SCORE = "Blue Team's Score";
	public static String CURRENT_ROUND = "Current Round";
	public static String MAX_ROUND = "Maximum Rounds";
	public static String NEXT_ROUND = "Next Round";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.standings_fragment, parent, false);
		
		redTeamScore = (TextView) v.findViewById(R.id.red_team_score);
		redTeamScore.setText(Integer.toString(getArguments().getInt(RED_SCORE)));
			
		blueTeamScore = (TextView) v.findViewById(R.id.blue_team_score);
		blueTeamScore.setText(Integer.toString(getArguments().getInt(BLUE_SCORE)));
		
		currentRound = (TextView) v.findViewById(R.id.current_round);
		String roundText = Integer.toString(getArguments().getInt(CURRENT_ROUND)) + " / " + Integer.toString(getArguments().getInt(MAX_ROUND));
		currentRound.setText(roundText);
		
		nextRoundButton = (Button) v.findViewById(R.id.start_next_round_button);
		nextRoundButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mCallback.goNext(NEXT_ROUND);
			}
		});
		
		return v;
	}
	
}
