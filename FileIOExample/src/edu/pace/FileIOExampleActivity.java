package edu.pace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileIOExampleActivity extends Activity {
	private static final String TAG = "FileIOExampleActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		Button loadTask;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String string1 = "Hey you";
		String string2 = "Is there anybody there";
		String string3 = "Can you hear me";

		setContentView(R.layout.main);

		try {
			
			//write to my private storage
			String FILENAME = "hello_file";
			String string = "hello world!";

			FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();
			
			String state = Environment.getExternalStorageState();

			Log.d(TAG, state);

			// This is where you save files that could be shared
			File path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			// Root of the external storage
//			File path = Environment.getExternalStorageDirectory();
			
			File file = new File(path, "file.txt");

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(string1);
			writer.newLine();
			writer.write(string2);
			writer.newLine();
			writer.write(string3);
			writer.newLine();
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		loadTask = (Button) findViewById(R.id.button1);
		loadTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					File file = new File(Environment
							.getExternalStorageDirectory(), "file.txt");
					BufferedReader reader = new BufferedReader(new FileReader(file));
					String firstline;
				    firstline = reader.readLine();
					// Get a directory listing
					//firstline=Environment.getExternalStorageDirectory().list()[0];
					
					TextView t=(TextView)findViewById(R.id.tv1); 
				    t.setText(firstline);
				    reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}