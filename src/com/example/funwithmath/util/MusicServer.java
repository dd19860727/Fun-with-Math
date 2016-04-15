package com.example.funwithmath.util;

import com.example.funwithmath.R;
import com.example.funwithmath.R.raw;

import android.app.Service;
import android.content.Intent;
import android.drm.DrmErrorEvent;
import android.drm.DrmInfoEvent;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.drm.DrmManagerClient.OnInfoListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.IBinder;

public class MusicServer extends Service {

	private MediaPlayer mediaPlayer = new MediaPlayer();
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		mediaPlayer = MediaPlayer.create(this, R.raw.bikerides); 

	}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		if(!mediaPlayer.isPlaying()){  
            mediaPlayer.start(); 
            mediaPlayer.setLooping(true);
        }  
        return START_STICKY;  
	}
	
		

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(mediaPlayer.isPlaying()){  
			mediaPlayer.stop();  
        }  
		mediaPlayer.release();  

	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}}