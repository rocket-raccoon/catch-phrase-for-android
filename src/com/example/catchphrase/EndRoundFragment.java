package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EndRoundFragment extends InGameFragment {
	
	private Button redTeamButton;
	private Button blueTeamButton;
	private static int RED = 0;
	private static int BLUE = 1;
	
	public static String REBUTTAL_AFTER_BLUE_VICTORY = "Rebuttal, Blue Won Last Round";
	public static String REBUTTAL_AFTER_RED_VICTORY = "Rebuttal, Red Won Last Round";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.end_round_fragment, parent, false);
		
		redTeamButton = (Button) v.findViewById(R.id.redTeamButton);
		redTeamButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(REBUTTAL_AFTER_RED_VICTORY);
			}
		});
		
		blueTeamButton = (Button) v.findViewById(R.id.blueTeamButton);
		blueTeamButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(REBUTTAL_AFTER_BLUE_VICTORY);
			}
		});
		
		return v;
	}

}
