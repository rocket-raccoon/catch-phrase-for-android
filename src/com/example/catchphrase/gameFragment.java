package com.example.catchphrase;

import android.app.Fragment;
import android.app.FragmentManager;
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
				FragmentManager fm = getActivity().getFragmentManager();
				Fragment fragment = new endRoundFragment();
				fm.beginTransaction()
					.replace(R.id.fragmentContainer, fragment)
					.addToBackStack(null)
					.commit();
			}
		});
		
		return v;
	}

}







