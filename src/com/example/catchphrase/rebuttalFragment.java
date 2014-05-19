package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class rebuttalFragment extends Fragment {
	
	private Button yesButton;
	private Button noButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void startNextRound() {
		FragmentManager fm = getActivity().getFragmentManager();
		Fragment fragment = new standingsFragment();
		fm.beginTransaction()
			.replace(R.id.fragmentContainer, fragment)
			.commit();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.rebuttal_fragment, parent, false);
		
		yesButton = (Button) v.findViewById(R.id.yesRebuttalButton);
		yesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startNextRound();
			}
		});
		
		noButton = (Button) v.findViewById(R.id.noRebuttalButton);
		noButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startNextRound();
			}
		});
		
		return v;
	}
	
}




