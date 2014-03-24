//
// UiThreadDemo demonstrates what happens when you access the UI from outside the main thread and
// also what happens if you block the main thread. So...
// 
// 1. Always access the UI from the UI (also called main) thread.
// 2. Never block the UI (main) thread.
//

package net.mobilecoders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UiThreadDemoActivity extends Activity {

	private Button problemButton, normalButton;

	private int counter;
	private int autocounter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Note that we store our counter in the activity, so when the activity
		// goes away so does this value.
		counter = autocounter = 0;
		
		EditText textinfo = (EditText) findViewById(R.id.editText1);
		textinfo.setText("" + counter);

		// Normal button just updates an EditText widget with a counter
		normalButton = (Button) findViewById(R.id.button1);
		normalButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText textinfo = (EditText) findViewById(R.id.editText1);
				textinfo.setText("" + counter);

				// Note that these lines are equivalent.
				// counter++;
				// counter+=1;
				counter = counter + 1;
			}
		});

		// Problem blocks the UI thread
		problemButton = (Button) findViewById(R.id.button2);
		problemButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				// Delay UI thread for 20 seconds very bad
/*				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				// This is just as bad, we are using Post which will run
				// in the UI thread, another sleep.
	/*			v.post(new Runnable() {
			        public void run() {
						try {
							Thread.sleep(20000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    });*/
				
				// This is a good solution.  The UI thread is not blocked.

				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						problemButton = (Button) findViewById(R.id.button2);
						problemButton.post(new Runnable() {
							public void run() {
								problemButton.setText("Problem Solved");
					        }	
						});
						
					}
				}).start();
			}
		});

		//
		// This thread is very bad, it's updating the UI with setText from a different thread.
		//
	/*	new Thread(new Runnable() {
			public void run() {
				EditText textinfo = (EditText) findViewById(R.id.editText2);
				textinfo.setText("" + autocounter);
				autocounter = autocounter + 1;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();*/

		//
		// This thread is also bad, it's delaying the UI with a sleep it can easily
		// be fixed however.
		//

		new Thread(new Runnable() {
			public void run() {
				while (true) {

					runOnUiThread(new Runnable() {
						public void run() {

							EditText textinfo = (EditText) findViewById(R.id.editText2);
							textinfo.setText("" + autocounter);
							autocounter = autocounter + 1;

						}
					});


					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				
				}
			}
		}).start();


	}
}