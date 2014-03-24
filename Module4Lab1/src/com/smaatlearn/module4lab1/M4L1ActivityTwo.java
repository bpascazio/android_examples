package com.smaatlearn.module4lab1;

import com.smaatlearn.module4lab1.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.app.Activity;

public class M4L1ActivityTwo extends Activity {
	
	Thread thr;
	boolean threadrun;

	@Override
	public void onStop() {
		super.onStop();
		threadrun = false;
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintwo);
        thr=null;
    }
    
    @Override
    public void onStart() {
		super.onStart();
		threadrun = true;
		thr = new Thread(new Runnable() {
			public void run() {
				while (threadrun) {

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					runOnUiThread(new Runnable() {
						public void run() {

							M4L1Application AC = (M4L1Application)getApplicationContext();
							
							EditText textinfo = (EditText) findViewById(R.id.editText2);
							textinfo.setText("" + AC.autocounter);
							AC.autocounter = AC.autocounter + 1;
							Log.d("","SECOND "+AC.autocounter);

						}
					});
				}
			}
		});
		
		thr.start();
    }
}
