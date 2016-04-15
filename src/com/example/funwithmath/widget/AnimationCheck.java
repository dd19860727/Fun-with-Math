package com.example.funwithmath.widget;

import com.example.funwithmath.R;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.widget.ImageView;

public class AnimationCheck {
	
	private AnimationDrawable animationDrawable;

	public void animationCheckmark(ImageView animation, MediaPlayer mpRight) {
		// TODO Auto-generated method stub
		
		animation.setImageResource(R.drawable.animationch);
		animationDrawable = (AnimationDrawable) animation.getDrawable();
		animationDrawable.start();
		mpRight.start();
		
	}

}
