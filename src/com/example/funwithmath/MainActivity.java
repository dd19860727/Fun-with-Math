/*
 * This code is free software; you can redistribute it and/or modify it.
 * 
 * The Fun with Math App class implements an application that
 * educates children in the area of basic mathematics. 
 * 
 * The MainActivtity Class is the Main Panel.
 * 
 * @author  Di Wang
 * 
 * */

package com.example.funwithmath;

import com.example.funwithmath.util.MusicController;
import com.example.funwithmath.util.MusicServer;
import com.example.funwithmath.widget.CustomActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private CustomActionBar cActionBar;

	private Button addSubButton;
	private Button multiDivButton;
	private Button mixMode;

	private Button sound;
	private Intent serviceIntent;
	private MusicController musiControl;
	private boolean musicPlayStatus = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Customize Action Bar
		cActionBar = new CustomActionBar(getActionBar());
		cActionBar.initActionBar();
		cActionBar.cutomLayoutInflater(this);
		cActionBar.display();

		// Control Background Music
		sound = (Button) findViewById(R.id.sound);
		sound.setBackgroundResource(R.drawable.soundopen);
		serviceIntent = new Intent(this, MusicServer.class);
		musiControl = new MusicController(this, serviceIntent);
		musiControl.startMusic();
		musicPlayStatus = true;

		addSubButton = (Button) findViewById(R.id.addSub);
		multiDivButton = (Button) findViewById(R.id.mutiDiv);
		mixMode = (Button) findViewById(R.id.mixmode);

		// Button to open or close music

		sound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (!musicPlayStatus) {
					musiControl.soundSwitch(sound, musicPlayStatus);
					musicPlayStatus = true;
				} else {
					musiControl.soundSwitch(sound, musicPlayStatus);
					musicPlayStatus = false;
				}

			}
		});

		addSubButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(), AddSub.class);
				startActivity(i);

			}
		});

		multiDivButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(), MultiDiv.class);
				startActivity(i);

			}
		});

		mixMode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), MixMode.class);
				startActivity(i);

			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		musiControl.stopMyPlaySerive();
		musicPlayStatus = false;

	}

}
