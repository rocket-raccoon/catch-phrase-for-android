package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RebuttalFragment extends InGameFragment {
	
	private Button yesButton;
	private Button noButton;
	
	public static String REBUTTAL_YES = "Yes to rebuttal";
	public static String REBUTTAL_NO = "No to rebuttal";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.rebuttal_fragment, parent, false);
		
		yesButton = (Button) v.findViewById(R.id.yesRebuttalButton);
		yesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(REBUTTAL_YES);
			}
		});
		
		noButton = (Button) v.findViewById(R.id.noRebuttalButton);
		noButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallback.goNext(REBUTTAL_NO);
			}
		});
		
		return v;
	}
	
}




