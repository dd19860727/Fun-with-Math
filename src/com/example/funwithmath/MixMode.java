/*
 * 
 * The MixModeActivtity Class is working for Mixed four operators.
 * 
 * */

package com.example.funwithmath;

import java.util.ArrayList;
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

public class MixMode extends Activity implements RecognitionListener {

	private TextView textFactor1;
	private TextView textFactor2;
	private TextView textFactor3;

	private TextView operator;
	private TextView operator1;
	private int operatorGen;

	private ClearableEditText input;
	private String sinput;
	private int inputNum;

	private int factor1;
	private int factor2;
	private int factor3;

	private String sfactor1;
	private String sfactor2;
	private String sfactor3;

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
	public static String temp;
	private String temp1;
	private Boolean checTemp;

	private MediaPlayer mpRight;
	private MediaPlayer mpWrong;

	private Button mic;
	private SpeechRecognizer speech;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mix_mode);

		// Initialize
		textFactor1 = (TextView) findViewById(R.id.factor1M);
		textFactor2 = (TextView) findViewById(R.id.factor2M);
		textFactor3 = (TextView) findViewById(R.id.factor3M);

		operator = (TextView) findViewById(R.id.operatorM);
		operator1 = (TextView) findViewById(R.id.operator1M);

		input = (ClearableEditText) findViewById(R.id.inputM);

		animationCH = (ImageView) findViewById(R.id.animationCHM);
		animationCR = (ImageView) findViewById(R.id.animationCRM);

		correctness = (TextView) findViewById(R.id.CorrectPercentM);

		right = 0;
		wrong = 0;

		// Customize Action Bar
		initActionBar();

		// Generate Operator
		operatorGenerator();

		// Generate Number
		generateNum();

		// Convert Number to String
		factNumToStr();

		sound = (Button) findViewById(R.id.soundM);
		serviceIntent = new Intent(this, MusicServer.class);

		// Correct Wrong Sound Effect
		mpRight = MediaPlayer.create(getApplicationContext(), R.raw.correct);
		mpWrong = MediaPlayer.create(getApplicationContext(), R.raw.wrong);

		// Speaker Icon
		mic = (Button) findViewById(R.id.voiceRecogM);
		speech = SpeechRecognizer.createSpeechRecognizer(this);
		speech.setRecognitionListener(this);

		gov = (GestureOverlayView) findViewById(R.id.himi_gestureM);
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
		
		//Voice Recognition
		mic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (musicPlayStatus) {
					sound.setBackgroundResource(R.drawable.soundclose);
					stopMyPlaySerive();
					musicPlayStatus = false;
				} 
				
				promptSpeechInput();
				
			}
		});

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

		submit = (Button) findViewById(R.id.ButtonSubmitM);

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				sinput = input.getText().toString();

				if (operatorGen == 0) {

					// Check the answer of addition
					check1();

				} else if (operatorGen == 1) {

					// Check the answer of substraction
					check2();

				} else if (operatorGen == 2) {

					// Check the answer of substraction
					check3();

				} else {
					check4();
				}

				temp = "";

			}

			private void check4() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 - factor2 / factor3)) {

						// animation of check mark
						animationCheck();

						input.setText("");

						operatorGenerator();
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

			private void check3() {
				// TODO Auto-generated method stub
				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 - factor2 * factor3)) {

						// animation of check mark
						animationCheck();

						input.setText("");

						operatorGenerator();
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

			private void check2() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 + factor2 / factor3)) {

						// animation of check mark
						animationCheck();

						input.setText("");

						operatorGenerator();
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

			private void check1() {
				// TODO Auto-generated method stub

				if (!(sinput.isEmpty())) {

					inputNum = Integer.parseInt(sinput);

					if (inputNum == (factor1 + factor2 * factor3)) {

						animationCheck();

						input.setText("");

						operatorGenerator();
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
				mpWrong.start();

			}

			private void animationCheck() {
				// TODO Auto-generated method stub

				animationCH.setImageResource(R.drawable.animationch);
				animationDrawable = (AnimationDrawable) animationCH.getDrawable();
				animationDrawable.start();
				mpRight.start();

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

		Reset = (Button) findViewById(R.id.ButtonResetM);

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

	protected void promptSpeechInput() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());

		speech.startListening(intent);
		
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

		if (factor1 < 10 && factor2 < 10 && factor3 < 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 < 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();

		} else if (factor1 < 10 && factor2 >= 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 >= 10 && factor3 < 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 < 10 && factor2 >= 10 && factor3 >= 10) {
			sfactor1 = "0" + new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 < 10 && factor3 < 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 >= 10 && factor3 < 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = "0" + new Integer(factor3).toString();
		} else if (factor1 >= 10 && factor2 < 10 && factor3 >= 10) {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = "0" + new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		} else {
			sfactor1 = new Integer(factor1).toString();
			sfactor2 = new Integer(factor2).toString();
			sfactor3 = new Integer(factor3).toString();
		}

		textFactor1.setText(sfactor1);
		textFactor2.setText(sfactor2);
		textFactor3.setText(sfactor3);
	}

	private void operatorGenerator() {
		// TODO Auto-generated method stub

		operatorGen = Math.abs((int) (Math.random() * 4));

		if (operatorGen == 0) {

			operator.setText("+");
			operator1.setText("*");

		} else if (operatorGen == 1) {

			operator.setText("+");
			operator1.setText("/");

		} else if (operatorGen == 2) {

			operator.setText("-");
			operator1.setText("*");

		} else {

			operator.setText("-");
			operator1.setText("/");
		}

	}

	private void generateNum() {
		// TODO Auto-generated method stub

		factor1 = Math.abs((int) (Math.random() * 100));
		factor2 = Math.abs((int) (Math.random() * 100));
		factor3 = Math.abs((int) (Math.random() * 100));

		if (operatorGen == 1 || operatorGen == 3) {

			generateDivNum();

		}

		if (operatorGen == 2) {

			Boolean i = true;

			while (i == true) {

				factor2 = Math.abs((int) (Math.random() * 100));
				factor3 = Math.abs((int) (Math.random() * 100));

				int factorX = factor2 * factor3;

				if (factorX < 100) {

					int m = 100 - factorX;

					factor1 = factorX + Math.abs((int) (Math.random() * m));
					;

					i = false;

					break;
				}

			}

		}

		if (operatorGen == 3) {

			Boolean k = true;

			while (k == true) {

				factor1 = Math.abs((int) (Math.random() * 100));

				int factorX1 = factor2 / factor3;

				if (factor1 > factorX1) {

					k = false;

					break;
				}

			}

		}

	}

	private void generateDivNum() {
		// TODO Auto-generated method stub

		Boolean i = true;

		while (i == true) {

			int multiNum = Math.abs((int) (Math.random() * 100));

			factor2 = factor3 * multiNum;

			if (factor2 < 100 && factor2 != 0 && factor3 != 0) {

				i = false;

				break;
			}

		}

	}

	@Override
	public void onReadyForSpeech(Bundle params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBeginningOfSpeech() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRmsChanged(float rmsdB) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBufferReceived(byte[] buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndOfSpeech() {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),
				getString(R.string.end_of_speech),
				Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onError(int error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResults(Bundle data) {
		// TODO Auto-generated method stub
		ArrayList<String> result = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
		input.setText(result.get(0));
		
	}

	@Override
	public void onPartialResults(Bundle partialResults) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(int eventType, Bundle params) {
		// TODO Auto-generated method stub
		
	}

}
