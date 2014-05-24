package com.example.catchphrase;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.media.MediaPlayer;

public class roundTimer {
	
	private int duration = 60;
	private int timeBetweenBeeps = 2;
	private MediaPlayer mPlayer;
	private Context currentContext;
	
	public roundTimer(Context c) {
		currentContext = c;
	}
	
	public void stop() {
		if(mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void beep(Context c) {
		stop();
		mPlayer = MediaPlayer.create(c, R.raw.beep);
		mPlayer.start();
	}
	
	public void startTimer() {
		int counter = 0;
		while(counter < 30) {
			counter++;
			try {
				Thread.sleep(timeBetweenBeeps*1000);
				mPlayer = new MediaPlayer();
				beep(currentContext);
			}
			catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
	}
	
}
