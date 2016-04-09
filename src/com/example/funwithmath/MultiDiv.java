/*
 * 
 * The MultiDivActivtity Class is working for Mutiplication and Division.
 * 
 * */

package com.example.funwithmath;

import java.util.List;
import java.util.Set;

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
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MultiDiv extends Activity {

	private TextView textFactor1;
	private TextView textFactor2;

	private OperatorGenerator op;
	private TextView operator;
	private int operatorGen;

	private EditText input;
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

	private ImageView animationCH;
	private ImageView animationCR;
	private AnimationDrawable animationDrawable;

	private Button sound;
	private Intent serviceIntent;
	private boolean musicPlayStatus = true;

	private GestureOverlayView gov;
	private Gesture gesture;
	private GestureLibrary gestureLib;
	private String temp;
	private String temp1;
	private Boolean checTemp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sub_main);

		// Initialize
		textFactor1 = (TextView) findViewById(R.id.factor1);
		textFactor2 = (TextView) findViewById(R.id.factor2);
		operator = (TextView) findViewById(R.id.operator);

		input = (EditText) findViewById(R.id.input);

		animationCH = (ImageView) findViewById(R.id.animationCH);
		animationCR = (ImageView) findViewById(R.id.animationCR);

		correctness = (TextView) findViewById(R.id.CorrectPercent);

		right = 0;
		wrong = 0;

		// Customize Action Bar
		initActionBar();

		// Generate Operator
		op = new OperatorGenerator();
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

		sound = (Button) findViewById(R.id.sound);
		serviceIntent = new Intent(this, MusicServer.class);

		gov = (GestureOverlayView) findViewById(R.id.himi_gesture);
		gov.setGestureStrokeType(GestureOverlayView.GESTURE_STROKE_TYPE_MULTIPLE);
		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		checTemp = true;

		// Gesture Recognition
		gov.addOnGestureListener(new OnGestureListener() {
			@Override

			public void onGestureStarted(GestureOverlayView overlay, MotionEvent event) {

			}

			@Override

			public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
				gesture = overlay.getGesture();

				if (event.getAction() == MotionEvent.ACTION_UP) {

					addMyGesture(gesture);

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

		if (!gestureLib.load()) {

		} else {
			Set<String> set = gestureLib.getGestureEntries();
			Object ob[] = set.toArray();
		}

		// Button to open or close music

		sound.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				soundStopClick();
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

			}

			private void checkDiv() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 / factor2)) {

						// animation of check mark
						animationCheck();

						input.setText("");

						operatorGen = op.operatorGenerator();
						operatorGenerator(operatorGen);
						generateNum();
						factNumToStr();

						right++;
						// calculate correctness
						displayPercent();

					} else {

						// animation of cross mark
						animationCross();

						input.setText("");

						wrong++;

						displayPercent();

					}

				}

			}

			private void checkMulti() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 * factor2)) {

						animationCheck();

						input.setText("");

						operatorGen = op.operatorGenerator();
						operatorGenerator(operatorGen);
						generateNum();
						factNumToStr();

						right++;

						displayPercent();

					} else {

						animationCross();

						wrong++;

						displayPercent();

					}

				}

			}

			private void animationCross() {
				// TODO Auto-generated method stub

				animationCR.setImageResource(R.drawable.animationcr);
				animationDrawable = (AnimationDrawable) animationCR.getDrawable();
				animationDrawable.start();

			}

			private void animationCheck() {
				// TODO Auto-generated method stub

				animationCH.setImageResource(R.drawable.animationch);
				animationDrawable = (AnimationDrawable) animationCH.getDrawable();
				animationDrawable.start();

			}

			private void displayPercent() {
				// TODO Auto-generated method stub

				if (wrong == 0 && right == 0) {

					correctness.setText("Start");

				} else if (wrong == 0 && right != 0) {

					correctness.setText("100%");

				} else {

					double dPrecent = (double) right / (wrong + right);

					int precent = ((int) (dPrecent * 100));

					String sprecent = new Integer(precent).toString();

					correctness.setText(sprecent + "%");

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

	protected void addMyGesture(Gesture gesture2) {
		// TODO Auto-generated method stub
		try {

			findGesture(gesture2);

		} catch (Exception e) {
		}

	}

	private void findGesture(Gesture gesture2) {
		// TODO Auto-generated method stub
		try {

			List<Prediction> predictions = gestureLib.recognize(gesture);

			if (!predictions.isEmpty()) {
				Prediction prediction = predictions.get(0);

				if (prediction.score >= 1) {

					if (prediction.name.equals("10")) {

						temp1 = removSec("1");

					} else if (prediction.name.equals("11")) {
						temp1 = removSec("8");

					} else if (prediction.name.equals("4")) {
						temp1 = removSec("4");

					} else if (prediction.name.equals("5")) {
						temp1 = removSec("5");

					} else {
						temp1 = prediction.name;
						clearGesture();
					}

					temp = temp + temp1;
					temp = temp.replace("null", "");

					input.setText(temp);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void clearGesture() {
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

	private String removSec(String temp) {
		// TODO Auto-generated method stub
		if (checTemp == true) {
			temp = temp;
			checTemp = false;
		} else {
			temp = "";
			checTemp = true;
		}

		return temp;
	}

	protected void soundStopClick() {
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
