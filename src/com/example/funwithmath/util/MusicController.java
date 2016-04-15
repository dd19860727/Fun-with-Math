package com.example.funwithmath.util;

import com.example.funwithmath.AddSub;
import com.example.funwithmath.MainActivity;
import com.example.funwithmath.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;

public class MusicController {

	private boolean musicPlayStatus = true;
	private Intent serviceIntent;
	private Context context;


	public MusicController(Context c, Intent serviceIntent) {
		// TODO Auto-generated constructor stub
		context = c;
		this.serviceIntent = serviceIntent;
		
	}


	public void startMusic() {
		// TODO Auto-generated method stub
		// Start Music
		context.startService(serviceIntent);
		musicPlayStatus = true;

	}
	
	public void soundSwitch(Button sound, boolean musicPlayStatus) {
		// TODO Auto-generated method stub
		
		this.musicPlayStatus = musicPlayStatus;
		
		if (!musicPlayStatus) {
			sound.setBackgroundResource(R.drawable.soundopen);
			soundSwitcher();
			musicPlayStatus = true;
		} else {
			sound.setBackgroundResource(R.drawable.soundclose);
			soundSwitcher();
			musicPlayStatus = false;
		}
		
		
	}

	public void soundSwitcher() {
		// TODO Auto-generated method stub
		if (!musicPlayStatus) {
			playAudio();
			musicPlayStatus = true;
		} else {
			stopMyPlaySerive();
			musicPlayStatus = false;
		}

	}

	public void stopMyPlaySerive() {
		// TODO Auto-generated method stub
		context.stopService(serviceIntent);
		musicPlayStatus = false;
	}

	public void playAudio() {
		// TODO Auto-generated method stub
		context.startService(serviceIntent);
		musicPlayStatus = true;
	}

}
