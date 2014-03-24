package edu.pace;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

//
// NOTE: This example demonstrates Anroid text to speach capabilities.
//

public class TTSExampleActivity extends Activity implements OnInitListener {
	
	static final String TAG = "TTSExampleActivity";
	private static final int MY_DATA_CHECK_CODE = 1234;
	/** Called when the activity is first created. */
	private TextToSpeech tts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//
		// This intent is used to check to see if the TTS service is available.
		//
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "In onActivityResult requestCode: " + requestCode
				+ " resultCode: " + resultCode);
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				Log.i(TAG, "CheckVoiceDataPassed. Initializing tts");
				tts = new TextToSpeech(this, this);
			} else {
				Log.i(TAG, "CheckVoiceDataFailed. Installing tts");
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent
						.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}
	}

	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
		String speech1 = "How are you?";
		String speech2 = "I hope you are fine.";
		tts.setLanguage(Locale.US);
		//
		// NOTE: Flush drops all entries in the playback
		// queue (media to be played and text to be synthesized). The new entry
		// replaces these.
		//
		tts.speak(speech1, TextToSpeech.QUEUE_FLUSH, null);
		tts.speak(speech2, TextToSpeech.QUEUE_ADD, null);

		//
		// NOTE: 1.0 is normal pitch and speech rate, here we play with then
		// restore these values.
		//
		tts.setPitch((float) 0.2);
		tts.speak(speech1, TextToSpeech.QUEUE_ADD, null);
		tts.setPitch((float) 1.0);
		tts.setSpeechRate((float) 0.2);
		tts.speak(speech1, TextToSpeech.QUEUE_ADD, null);
		tts.setSpeechRate((float) 1.0);
		//
		// NOTE: This is how you change the language.
		//
		tts.setLanguage(Locale.FRENCH);
		tts.speak(speech1, TextToSpeech.QUEUE_ADD, null);
		tts.speak(speech2, TextToSpeech.QUEUE_ADD, null);
	}

}