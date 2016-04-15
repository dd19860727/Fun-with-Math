package com.example.funwithmath;

import android.app.Activity;
import android.widget.TextView;

public class DisplayPercent {
	
	private Activity activity;
	
	public DisplayPercent(Activity activity) {
		// TODO Auto-generated constructor stub
		
		this.activity = activity;
	}

	public void disPercent(int r, int w) {
		// TODO Auto-generated method stub

		if (w == 0 && r == 0) {
			
			if(activity instanceof AddSub){
				((AddSub) activity).setCorrectText("Start");
			}else if (activity instanceof MultiDiv) {
				((MultiDiv) activity).setCorrectText("Start");
			}else {
				((MixMode) activity).setCorrectText("Start");
			}


		} else if (w == 0 && r != 0) {
			
			if(activity instanceof AddSub){
				((AddSub) activity).setCorrectText("100%");
			}else if (activity instanceof MultiDiv) {
				((MultiDiv) activity).setCorrectText("100%");
			}else {
				((MixMode) activity).setCorrectText("100%");
			}

		} else {

			double dPrecent = (double) r / (w + r);

			int precent = ((int) (dPrecent * 100));

			String sprecent = new Integer(precent).toString();
			
			if(activity instanceof AddSub){
				((AddSub) activity).setCorrectText(sprecent + "%");
			}else if (activity instanceof MultiDiv) {
				((MultiDiv) activity).setCorrectText(sprecent + "%");
			}else {
				((MixMode) activity).setCorrectText(sprecent + "%");
			}

		}

	}

}
