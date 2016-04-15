package com.example.funwithmath.widget;

import com.example.funwithmath.MainActivity;
import com.example.funwithmath.R;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class CustomActionBar {
	
	private ActionBar actionBar;
	private View mCustomView;

	public CustomActionBar(ActionBar actionBar) {
		// TODO Auto-generated constructor stub
		
		this.actionBar = actionBar;
		
	}

	public void initActionBar() {
		// TODO Auto-generated method stub
		
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		
	}

	public void cutomLayoutInflater(Activity activity) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = LayoutInflater.from(activity);
		
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		
		TextView mTitleTextViewFun = (TextView) mCustomView.findViewById(R.id.title_textFun);
		mTitleTextViewFun.setText("Fun");
		
		TextView mTitleTextViewWith = (TextView) mCustomView.findViewById(R.id.title_textWith);
		mTitleTextViewWith.setText("with");

		TextView mTitleTextViewMath = (TextView) mCustomView.findViewById(R.id.title_textMath);
		mTitleTextViewMath.setText("Math");
		
		this.mCustomView = mCustomView;
		
	}

	public void display() {
		// TODO Auto-generated method stub
		
		actionBar.setCustomView(mCustomView);
		actionBar.setDisplayShowCustomEnabled(true);
		
	}
		

}
