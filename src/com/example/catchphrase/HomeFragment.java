package com.example.catchphrase;

import java.util.HashMap;

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

public class HomeFragment extends InGameFragment {
	
	private Button mStartGameButton;
	private Button mRulesButton;
	
	public static String RULES = "Rules";
	public static String START_GAME = "Start Game";
	public static String NUM_ROUNDS = "How many rounds?";
	private static int REQUEST_NO_ROUNDS = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK) return;
		if(requestCode == REQUEST_NO_ROUNDS) {
			int numRounds = (Integer) data.getSerializableExtra(RoundPickerFragment.NUM_ROUNDS);
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put(NUM_ROUNDS, numRounds);
			mCallback.goNext(START_GAME, params);
		}
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home_fragment, parent, false);
		
		mStartGameButton = (Button) v.findViewById(R.id.startGameButton);
		mStartGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getFragmentManager();
				RoundPickerFragment rp = new RoundPickerFragment();
				rp.setTargetFragment(HomeFragment.this, REQUEST_NO_ROUNDS);
				rp.show(fm, "BLAH");
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


