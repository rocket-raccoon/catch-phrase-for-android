package com.example.catchphrase;

import java.util.HashMap;

import android.app.Activity;
import android.app.Fragment;

public class InGameFragment extends Fragment {
	
	GetNextFragment mCallback;
	
    public interface GetNextFragment {
        public void goNext(String whatsNext, HashMap<String, Object>... additionalParams);
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (GetNextFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement GetNextFragment");
        }
    }
    
}
