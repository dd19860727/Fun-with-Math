package com.example.funwithmath.widget;

import com.example.funwithmath.R;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.widget.ImageView;

public class AnimationCross {
	
	private AnimationDrawable animationDrawable;

	public void animationCrossMark(ImageView animation, MediaPlayer mpWrong) {
		// TODO Auto-generated method stub
		
		animation.setImageResource(R.drawable.animationcr);
		animationDrawable = (AnimationDrawable) animation.getDrawable();
		animationDrawable.start();
		mpWrong.start();
		
	}


}
