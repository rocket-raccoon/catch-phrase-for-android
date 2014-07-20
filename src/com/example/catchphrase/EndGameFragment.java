package com.example.catchphrase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndGameFragment extends InGameFragment {

	public static String WINNER = "Who won?";
	public static String RETURN_HOME = "Go Home Again";
	
	private TextView gameWinner;
	private Button returnHomeButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.end_game_fragment, parent, false);
		gameWinner = (TextView) v.findViewById(R.id.game_winner);
		if(getArguments().getString(WINNER) == "Red") {
			gameWinner.setText("Red Team Wins!");
			gameWinner.setBackgroundColor(Color.RED);
		}
		else {
			gameWinner.setText("Blue Team Wins!");
			gameWinner.setBackgroundColor(Color.BLUE);
		}
		gameWinner.setTextColor(Color.WHITE);
		
		returnHomeButton = (Button) v.findViewById(R.id.return_to_home_button);
		returnHomeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(RETURN_HOME);
			}
		});
		
		return v;
	}
	
}
