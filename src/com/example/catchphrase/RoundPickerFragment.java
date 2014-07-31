package com.example.catchphrase;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

public class RoundPickerFragment extends DialogFragment {
	
	public static String NUM_ROUNDS = "How many rounds we got??";
	
	private void sendResult(int resultCode, int numRounds) {
		if(getTargetFragment() == null) {
			return;
		}
		Intent i = new Intent();
		i.putExtra(NUM_ROUNDS, numRounds);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle onSavedInstanceState) {
		View v = getActivity().getLayoutInflater().inflate(R.layout.round_picker_dialog, null);
		//Configure our number picker widget
		final NumberPicker np = (NumberPicker) v.findViewById(R.id.round_picker);
        np.setMaxValue(100);
        np.setMinValue(1);
        np.setWrapSelectorWheel(false);
		//Display the number picker widget
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.round_picker_text)
			.setPositiveButton(
					android.R.string.ok,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendResult(Activity.RESULT_OK, np.getValue());
						}
					})
			.create();
	}
	
}
