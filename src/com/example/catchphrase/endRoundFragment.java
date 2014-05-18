package com.example.catchphrase;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class endRoundFragment extends Fragment {
	
	private Button redTeamButton;
	private Button blueTeamButton;
	
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
						
			}
		});
		
		blueTeamButton = (Button) v.findViewById(R.id.blueTeamButton);
		blueTeamButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
						
			}
		});
		
		return v;
	}

}
