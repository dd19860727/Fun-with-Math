package com.example.funwithmath.util;

import java.util.List;
import java.util.Set;

import com.example.funwithmath.AddSub;
import com.example.funwithmath.MixMode;
import com.example.funwithmath.MultiDiv;
import com.example.funwithmath.R;

import android.app.Activity;
import android.content.Context;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;

public class GestureRecognition {
	
	private Activity activity;
	private Context context;
	private GestureOverlayView gov;
	private GestureLibrary gestureLib;
	public static String temp;
	private String temp1;
	private Boolean checTemp = true;;

	public GestureRecognition(Activity activity, Context context, GestureOverlayView gov) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		this.context = context;
		this.gov = gov;
		
		gestureLib = GestureLibraries.fromRawResource(context, R.raw.gestures);

	}
	

	public void addMyGesture(Gesture gesture) {
		// TODO Auto-generated method stub
		try {

			findGesture(gesture);
			

			} catch (Exception e) {			
			}

	}
	
	private void findGesture(Gesture gesture) {
		// TODO Auto-generated method stub
		
	
		
		try {

			List<Prediction> predictions = gestureLib.recognize(gesture);

			if (!predictions.isEmpty()) {
				Prediction prediction = predictions.get(0);
				
				if (prediction.score >= 1) {
					
					if (prediction.name.equals("10")) {

						temp1 = removSec("1");
						
						if(activity instanceof AddSub){
							((AddSub) activity).setGesInputText(temp1);
						}else if (activity instanceof MultiDiv) {
							((MultiDiv) activity).setGesInputText(temp1);
						}else {
							((MixMode) activity).setGesInputText(temp1);
						}

					} else if (prediction.name.equals("11")) {
						temp1 = removSec("8");
						
						if(activity instanceof AddSub){
							((AddSub) activity).setGesInputText(temp1);
						}else if (activity instanceof MultiDiv) {
							((MultiDiv) activity).setGesInputText(temp1);
						}else {
							((MixMode) activity).setGesInputText(temp1);
						}

					} else if (prediction.name.equals("4")) {
						temp1 = removSec("4");
						
						if(activity instanceof AddSub){
							((AddSub) activity).setGesInputText(temp1);
						}else if (activity instanceof MultiDiv) {
							((MultiDiv) activity).setGesInputText(temp1);
						}else {
							((MixMode) activity).setGesInputText(temp1);
						}

					} else if (prediction.name.equals("5")) {
						temp1 = removSec("5");
						
						if(activity instanceof AddSub){
							((AddSub) activity).setGesInputText(temp1);
						}else if (activity instanceof MultiDiv) {
							((MultiDiv) activity).setGesInputText(temp1);
						}else {
							((MixMode) activity).setGesInputText(temp1);
						}

					} else {
						temp1 = prediction.name;
						
						if(activity instanceof AddSub){
							((AddSub) activity).setGesInputText(temp1);
							((AddSub) activity).clearGesture();
						}else if (activity instanceof MultiDiv) {
							((MultiDiv) activity).setGesInputText(temp1);
							((MultiDiv) activity).clearGesture();
						}else {
							((MixMode) activity).setGesInputText(temp1);
							((MixMode) activity).clearGesture();
						}
						
					}

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


	public void loadGestureLib() {
		// TODO Auto-generated method stub
		
		if (!gestureLib.load()) {

		} else {
			Set<String> set = gestureLib.getGestureEntries();
			Object ob[] = set.toArray();
		}
		
	}

}
