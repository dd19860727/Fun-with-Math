/*
 * 
 * The MixModeActivtity Class is working for Mixed four operators.
 * 
 * */

package com.example.funwithmath;

import android.R.layout;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MixMode extends Activity {

	private TextView textFactor1;
	private TextView textFactor2;
	private TextView textFactor3;

	private TextView operator;
	private TextView operator1;
	private int operatorGen;

	private EditText input;
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

		input = (EditText) findViewById(R.id.inputM);

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

}
