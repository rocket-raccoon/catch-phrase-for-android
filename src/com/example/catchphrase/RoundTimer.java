package com.example.catchphrase;

import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

/*
This keeps the track of the time for each round played
By default each round lasts 60 seconds
The timer will beep faster and faster as the end of the round approaches
*/

public class RoundTimer {
	
	private double initialBeepInterval = 5.0;
	private int roundDuration = 60;
	private MediaPlayer mPlayer;
	private Context currentContext;
	
	public RoundTimer(Context c) {
		currentContext = c;
	}
	
	//Destroys the media player
	public void stop() {
		if(mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	//Plays a beeping sound
	public void beep(Context c) {
		mPlayer = MediaPlayer.create(c, R.raw.beep);
		mPlayer.start();
	}
	
	//Play a beeping sound faster and faster as the round goes on
	public void startTimer() {
		
		double timeBetweenBeeps = initialBeepInterval;
		long startTime = Calendar.getInstance().getTime().getTime();
		long endTime = startTime;
		
		while((endTime - startTime)/1000 < roundDuration) {
			try {
				Thread.sleep((long) (timeBetweenBeeps*1000));
				stop();
				mPlayer = new MediaPlayer();
				beep(currentContext);
			}
			catch (InterruptedException e) {
		        e.printStackTrace();
		    }
			endTime = Calendar.getInstance().getTime().getTime();
			timeBetweenBeeps = initialBeepInterval * (1 - ((endTime - startTime)/1000.0) / roundDuration);
		}
	}
}
