package edu.pace;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AsyncTaskDemoActivity extends Activity {

	private static final String TAG = "AsyncTaskDemoActivity";
	private static final int DIALOG_DOWNLOAD_COMPLETE = 0;
	private static final int DIALOG_PROGRESS = 1;

	private class Downloader {
		long dowloadFile(URL url) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // sleep or 1 second
			return 0;
		}
	}

	private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
		protected Long doInBackground(URL... urls) {
			int count = urls.length;
			long totalSize = 0;
			/*Thread thr = new Thread(new Runnable() {
				public void run() {
					publishProgress(0);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			thr.run();*/
			for (int i = 0; i < count; i++) {
				Downloader d = new Downloader();
				totalSize += d.dowloadFile(urls[i]);				
				publishProgress((int) ((i / (float) count) * 100));
				if (isCancelled()==true) {
					i=count;
				}
			}
			return totalSize;
		}

		protected void onPreExecute () {
			//in the UI thread
			//uncomment this to show the progress dialog earlier
			showDialog(DIALOG_PROGRESS);
		}
		
		protected void onProgressUpdate(Integer... progress) {
			
			//setProgressPercent(progress[0]);
			//if (progress[0]==0) {
			//	showDialog(DIALOG_PROGRESS);
			//}
		}

		protected void onPostExecute(Long result) {
			dismissDialog(DIALOG_PROGRESS);
			showDialog(DIALOG_DOWNLOAD_COMPLETE);
		}
		
		protected void onCancelled() {
			dismissDialog(DIALOG_PROGRESS);
		}
	}
	
	private ProgressDialog dialog_progress = null;

	@Override
	protected Dialog onCreateDialog(int id) {
		Log.d(TAG, "onCreateDialog called with " + id);
		switch (id) {
		case DIALOG_PROGRESS:
        	dialog_progress = new ProgressDialog(this);
        	dialog_progress.setTitle("Downloading");
        	dialog_progress.setMessage("Please wait while loading...");
        	dialog_progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        	dialog_progress.setCancelable(true);
            return dialog_progress;
		case DIALOG_DOWNLOAD_COMPLETE:
			return new AlertDialog.Builder(AsyncTaskDemoActivity.this)
					.setIcon(R.drawable.alert_dialog_icon).setTitle(
							R.string.alert_dialog_two_buttons_title)
					.setPositiveButton(R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked OK so do some stuff */
								}
							}).setNegativeButton(R.string.alert_dialog_cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked Cancel so do some stuff */
								}
							}).create();
		}
		return null;
	}

	Button asyncTask;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		asyncTask = (Button) findViewById(R.id.buttonasynctask);
		asyncTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				URL url1=null;
				URL url2=null;
				URL url3=null;
				try {
					url1 = new URL("http://www.cnn.com");
					url2 = new URL("http://www.nytimes.com");
					url3 = new URL("http://www.apple.com");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new DownloadFilesTask().execute(url1, url2, url3);
			}
		});
	}
}




