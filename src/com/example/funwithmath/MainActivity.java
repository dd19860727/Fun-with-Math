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

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button addSubButton;
	private Button multiDivButton;
	private Button mixMode;
	private Button sound;
	private Intent serviceIntent;
	private boolean musicPlayStatus = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addSubButton = (Button) findViewById(R.id.addSub);
		multiDivButton = (Button) findViewById(R.id.mutiDiv);
		mixMode = (Button) findViewById(R.id.mixmode);
		sound = (Button) findViewById(R.id.sound);
		sound.setBackgroundResource(R.drawable.soundopen);
		serviceIntent = new Intent(this, MusicServer.class);

		// Customize Action Bar
		initActionBar();
		
		//Start Music
		startService(serviceIntent);
		musicPlayStatus = true;

		// Button to open or close music

		sound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				soundStopClick();
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
		
		stopService(serviceIntent);
		musicPlayStatus = false;
		
	}

	private void soundStopClick() {
		// TODO Auto-generated method stub
		if (!musicPlayStatus) {
			sound.setBackgroundResource(R.drawable.soundopen);
			playAudio();
			musicPlayStatus = true;
		} else {
			sound.setBackgroundResource(R.drawable.soundclose);
			stopMyPlaySerive();
			musicPlayStatus = false;
		}
	}

	private void stopMyPlaySerive() {
		// TODO Auto-generated method stub
		stopService(serviceIntent);
		musicPlayStatus = false;
	}

	private void playAudio() {
		// TODO Auto-generated method stub
		startService(serviceIntent);
		musicPlayStatus = true;
	}

	private void initActionBar() {
		// TODO Auto-generated method stub

		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mTitleTextViewFun = (TextView) mCustomView.findViewById(R.id.title_textFun);
		mTitleTextViewFun.setText("Fun");

		TextView mTitleTextViewWith = (TextView) mCustomView.findViewById(R.id.title_textWith);
		mTitleTextViewWith.setText("with");

		TextView mTitleTextViewMath = (TextView) mCustomView.findViewById(R.id.title_textMath);
		mTitleTextViewMath.setText("Math");

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);

	}

}
