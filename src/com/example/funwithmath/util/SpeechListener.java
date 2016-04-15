package com.example.funwithmath.util;

import java.util.ArrayList;

import com.example.funwithmath.AddSub;
import com.example.funwithmath.MixMode;
import com.example.funwithmath.MultiDiv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

public class SpeechListener implements RecognitionListener {

	private SpeechRecognizer speech;
	private Context context;
	private String ans;
	private Activity activity;
	protected long mSpeechRecognizerStartListeningTime = 0;
	
	public SpeechListener(Activity activity,Context c) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
		context = c;
	}
	
	public void onCreate(){
		
		speech = SpeechRecognizer.createSpeechRecognizer(context);
		speech.setRecognitionListener(this);
		
	}
	
	
	
	public void promptSpeechInput() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
		
		 this.mSpeechRecognizerStartListeningTime = System.currentTimeMillis();

		speech.startListening(intent);
		
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
		
	}

	@Override
	public void onError(int error) {
		// TODO Auto-generated method stub
		
		 String errorMessage = getErrorText(error);
		 long duration = System.currentTimeMillis() - mSpeechRecognizerStartListeningTime;
		 
		 if (duration > 500 ) {
			 Toast.makeText(context.getApplicationContext(),
		        		errorMessage,
						Toast.LENGTH_SHORT).show();
		    }
		
	}

	@Override
	public void onResults(Bundle data) {
		// TODO Auto-generated method stub
		ArrayList<String> result = data.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
		ans = result.get(0);
		if(activity instanceof AddSub){
			((AddSub) activity).setInputText(ans);
		}else if (activity instanceof MultiDiv) {
			((MultiDiv) activity).setInputText(ans);
		}else {
			((MixMode) activity).setInputText(ans);
		}
		
		
	}

	@Override
	public void onPartialResults(Bundle partialResults) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEvent(int eventType, Bundle params) {
		// TODO Auto-generated method stub
		
	}
	
	private String getErrorText(int errorCode) {
		// TODO Auto-generated method stub
		 String message;
	        switch (errorCode) {
	        case SpeechRecognizer.ERROR_AUDIO:
	            message = "Audio recording error";
	            break;
	        case SpeechRecognizer.ERROR_CLIENT:
	            message = "Client side error";
	            break;
	        case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
	            message = "Insufficient permissions";
	            break;
	        case SpeechRecognizer.ERROR_NETWORK:
	            message = "Network error";
	            break;
	        case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
	            message = "Network timeout";
	            break;
	        case SpeechRecognizer.ERROR_NO_MATCH:
	            message = "Didn't understand, please try again.";
	            break;
	        case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
	            message = "RecognitionService busy";
	            break;
	        case SpeechRecognizer.ERROR_SERVER:
	            message = "error from server";
	            break;
	        case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
	            message = "No speech input, please try again.";
	            break;
	        default:
	            message = "Didn't understand, please try again.";
	            break;
	        }
	        return message;
	}

	public String getAns() {
		// TODO Auto-generated method stub
		return ans;
	}
	
	public void setAns(String ans) {
		this.ans = ans;
	}

}
