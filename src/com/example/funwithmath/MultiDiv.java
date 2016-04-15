/*
 * 
 * The MultiDivActivtity Class is working for Mutiplication and Division.
 * 
 * */

package com.example.funwithmath;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.funwithmath.util.FactToString;
import com.example.funwithmath.util.GestureRecognition;
import com.example.funwithmath.util.MusicController;
import com.example.funwithmath.util.MusicServer;
import com.example.funwithmath.util.SpeechListener;
import com.example.funwithmath.widget.AnimationCheck;
import com.example.funwithmath.widget.AnimationCross;
import com.example.funwithmath.widget.ClearableEditText;
import com.example.funwithmath.widget.CustomActionBar;

import android.R.layout;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiDiv extends Activity  {

	private CustomActionBar cActionBar;
	
	private Button sound;
	private Intent serviceIntent;
	private MusicController musiControl;
	private boolean musicPlayStatus;
	
	private Button mic;
	private SpeechListener speechListener;;

	private TextView textFactor1;
	private TextView textFactor2;

	private OperatorGenerator op;
	private TextView operator;
	private int operatorGen;

	private ClearableEditText input;
	private String sinput;
	private int inputNum;

	private GenerateNum gM;
	private int numOfArray;
	private int[] factArray;
	private int factor1;
	private int factor2;

	private FactToString fts;
	private String[] sfactArray;
	private String sfactor1;
	private String sfactor2;

	private TextView correctness;
	private int right;
	private int wrong;
	private DisplayPercent dPercent;

	private ImageView animationCH;
	private AnimationCheck animationCheck;
	private ImageView animationCR;
	private AnimationCross animationCross;

	private GestureOverlayView gov;
	private Gesture gesture;
	private GestureRecognition gRecognition;
	public static String temp;

	private MediaPlayer mpRight;
	private MediaPlayer mpWrong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_main);

		// Customize Action Bar
		cActionBar = new CustomActionBar(getActionBar());
		cActionBar.initActionBar();
		cActionBar.cutomLayoutInflater(this);
		cActionBar.display();
		
		//Control Background Music
		sound = (Button) findViewById(R.id.sound);
		serviceIntent = new Intent(this, MusicServer.class);
		musiControl = new MusicController (this, serviceIntent);
		musicPlayStatus = true;
		
		// Speaker Icon
		mic = (Button) findViewById(R.id.voiceRecog);
		speechListener = new SpeechListener(this, this);

		// Generate Number and Operator
		textFactor1 = (TextView) findViewById(R.id.factor1);
		textFactor2 = (TextView) findViewById(R.id.factor2);
		operator = (TextView) findViewById(R.id.operator);

		input = (ClearableEditText) findViewById(R.id.input);

		animationCH = (ImageView) findViewById(R.id.animationCH);
		animationCheck = new AnimationCheck();
		animationCR = (ImageView) findViewById(R.id.animationCR);
		animationCross = new AnimationCross();

		correctness = (TextView) findViewById(R.id.CorrectPercent);
		dPercent = new DisplayPercent(this);
		right = 0;
		wrong = 0;

		// Generate Operator
		op = new OperatorGenerator(1);
		operatorGen = op.operatorGenerator();
		operatorGenerator(operatorGen);

		// Generate Number
		gM = new GenerateNum();
		factArray = new int[numOfArray];
		generateNum();

		// Convert Number to String
		fts = new FactToString();
		sfactArray = new String[numOfArray];
		factNumToStr();

		// Correct Wrong Sound Effect
		mpRight = MediaPlayer.create(getApplicationContext(), R.raw.correct);
		mpWrong = MediaPlayer.create(getApplicationContext(), R.raw.wrong);

		gRecognition = new GestureRecognition(this,this,gov);
		gov = (GestureOverlayView) findViewById(R.id.himi_gesture);
		gov.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);

		// Gesture Recognition
		gov.addOnGestureListener(new OnGestureListener() {
			@Override

			public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

			}

			@Override

			public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
				gesture = overlay.getGesture();

				if (event.getAction() == MotionEvent.ACTION_UP) {

					gRecognition.addMyGesture(gesture);
					
					if (gesture.getStrokesCount() == 2) {

						clearGesture();

					}

				}

			}

			@Override
			public void onGestureCancelled(GestureOverlayView overlay, MotionEvent event) {
			}

			@Override
			public void onGesture(GestureOverlayView overlay, MotionEvent event) {
			}
		});
		
		gRecognition.loadGestureLib();

		// Voice Recognition
		mic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (musicPlayStatus) {
					sound.setBackgroundResource(R.drawable.soundclose);
					musiControl.stopMyPlaySerive();
					musicPlayStatus = false;
				}
				
				speechListener.onCreate();
				speechListener.promptSpeechInput();

			}
		});

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

		// Submit Button
		Button submit;

		submit = (Button) findViewById(R.id.ButtonSubmit);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				sinput = input.getText().toString();

				if (operatorGen == 0) {

					// Check the answer of Multiplication
					checkMulti();

				} else {

					// Check the answer of Division
					checkDiv();

				}

				temp = "";
				input.setText(temp);

			}

			private void checkDiv() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 / factor2)) {

						// animation of check mark
						animationCheck.animationCheckmark(animationCH, mpRight);

						input.setText("");

						operatorGen = op.operatorGenerator();
						operatorGenerator(operatorGen);
						generateNum();
						factNumToStr();

						right++;
						// calculate correctness
						dPercent.disPercent(right, wrong);

					} else {

						// animation of cross mark
						animationCross.animationCrossMark(animationCR, mpWrong);

						input.setText("");

						wrong++;

						dPercent.disPercent(right, wrong);

					}

				}

			}

			private void checkMulti() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 * factor2)) {

						animationCheck.animationCheckmark(animationCH, mpRight);

						input.setText("");

						operatorGen = op.operatorGenerator();
						operatorGenerator(operatorGen);
						generateNum();
						factNumToStr();

						right++;

						dPercent.disPercent(right, wrong);

					} else {

						animationCross.animationCrossMark(animationCR, mpWrong);

						wrong++;

						dPercent.disPercent(right, wrong);

					}

				}

			}
			
		});
		Button Reset;

		Reset = (Button) findViewById(R.id.ButtonReset);

		Reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				wrong = 0;
				right = 0;
				correctness.setText("Start");

			}
		});

	}

	public void setInputText(String s) {
		// TODO Auto-generated method stub
		
		input.setText(s);
		
	}
	
	public void setGesInputText(String s) {
		// TODO Auto-generated method stub
		temp = temp + s;
		temp = temp.replace("null", "");
		input.setText(temp);
	}
	
	public void setCorrectText(String s) {
		// TODO Auto-generated method stub
		correctness.setText(s);
	}

	public void clearGesture() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gov.cancelClearAnimation();
		gov.clear(true);
	}

	private void factNumToStr() {
		// TODO Auto-generated method stub

		sfactArray = fts.factNumToStr(factor1, factor2);
		sfactor1 = sfactArray[0];
		sfactor2 = sfactArray[1];

		textFactor1.setText(sfactor1);
		textFactor2.setText(sfactor2);

	}

	private void operatorGenerator(int operatorG) {
		// TODO Auto-generated method stub

		if (operatorG == 0) {

			operator.setText("*");

		} else {

			operator.setText("/");

		}

	}

	private void generateNum() {
		// TODO Auto-generated method stub

		factArray = gM.generateNum(factor1, factor2, operatorGen);
		factor1 = factArray[0];
		factor2 = factArray[1];

		if (operatorGen == 1) {

			generateDivNum();

		}

	}

	private void generateDivNum() {
		// TODO Auto-generated method stub

		Boolean i = true;

		while (i == true) {

			int multiNum = Math.abs((int) (Math.random() * 100));

			factor1 = factor2 * multiNum;

			if (factor1 < 100) {

				i = false;

				break;
			}

		}

	}
	

}
