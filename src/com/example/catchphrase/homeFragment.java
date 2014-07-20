package com.example.catchphrase;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class homeFragment extends InGameFragment {
	
	private Button mStartGameButton;
	private Button mRulesButton;
	
	public static String RULES = "Rules";
	public static String START_GAME = "Start Game";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}	
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home_fragment, parent, false);
		
		mStartGameButton = (Button) v.findViewById(R.id.startGameButton);
		mStartGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(START_GAME);	
			}
		});
		
		mRulesButton = (Button) v.findViewById(R.id.rulesButton);
		mRulesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(RULES);
			}
		});
		return v;
	}
}


