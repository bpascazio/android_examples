package edu.pace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CacheMemoryDemoActivity extends Activity {

	static Thread thr;

	private abstract class LargeBuffer extends Object {
		abstract void allocate(Activity a, Handler h, int size, int id);
		abstract void remove(Activity a, Handler h, int id);
	}

	private class LargeBufferMemory extends LargeBuffer {
		public byte[] bigArray = null;
		Handler localh;
		int localsize;
		LargeBuffer localref = this;
		void allocate(Activity a, Handler h, int size, int id) {
			if (thr==null) {
				localh = h;
				localsize = size;
				thr = new Thread(new Runnable() {
					public void run() {
						bigArray = new byte[localsize];
						Message msg = new Message();			
						if (bigArray == null) msg.what = ALLOCFAILED;
						else msg.what = ALLOCSUCCESS;
						msg.obj=localref;
						localh.sendMessage(msg);
						thr = null;
					}
				});
				thr.run();				
			} else {
				Message msg = new Message();			
				msg.what = ACTIONPENDING;
				msg.obj=this;
				h.sendMessage(msg);
				System.gc();
			}
		}
		void remove(Activity a, Handler h, int id) {
			if (thr==null) {
				localh = h;
				thr = new Thread(new Runnable() {
					public void run() {
						bigArray = null;
						Message msg = new Message();			
						msg.what = REMOVESUCCESS;
						msg.obj=this;
						localh.sendMessage(msg);
						System.gc();
						thr = null;
					}
				});
				thr.run();				
			} else {
				Message msg = new Message();			
				msg.what = ACTIONPENDING;
				msg.obj=this;
				h.sendMessage(msg);
				System.gc();
			}
		}
	}
	
	private class LargeBufferFile extends LargeBuffer {
	
		File file;
		Activity locala;
		Handler localh;
		int localsize;
		int localid;
		LargeBuffer localref = this;
		private static final String TAG = "LargeBufferFile";
		
		void allocate(Activity a, Handler h, int size, int id) {
			if (thr==null) {
				localh = h;
				localsize = size;
				locala = a;
				localid = id;
				thr = new Thread(new Runnable() {
					public void run() {
						try {
							Log.d(TAG, "cache directory="+locala.getCacheDir());
							file = new File(locala.getCacheDir(), "temp"+localid);
							FileWriter fw = new FileWriter(file);
							BufferedWriter bw = new BufferedWriter(fw);
							char[] temp = new char[localsize];
							bw.write(temp);
							bw.close();
							Message msg = new Message();			
							msg.what = ALLOCSUCCESS;
							msg.obj = localref;
							localh.sendMessage(msg);
						} catch (IOException e) {
							Log.d(TAG, "Error writing file!");
							Message msg = new Message();			
							msg.what = ALLOCFAILED;
							localh.sendMessage(msg);
						}
						thr = null;
					}
				});
				thr.run();
			} else {
				Message msg = new Message();			
				msg.what = ACTIONPENDING;
				h.sendMessage(msg);
				System.gc();
			}
		}			
			
		void remove(Activity a, Handler h, int id) {
			
			if (thr==null) {
				localh = h;
				thr = new Thread(new Runnable() {
					public void run() {
						if (file != null)
							file.delete();
						Message msg = new Message();			
						msg.what = REMOVESUCCESS;
						localh.sendMessage(msg);
						System.gc();
						thr = null;
					}
				});
				thr.run();
			} else {
				Message msg = new Message();			
				msg.what = ACTIONPENDING;
				h.sendMessage(msg);
				System.gc();
			}
		}
    }
	
	private static final int ALLOCSUCCESS   = 0;
	private static final int ALLOCFAILED    = 1;
	private static final int REMOVESUCCESS  = 2;
	private static final int REMOVEFAILED   = 3;
	private static final int ACTIONPENDING  = 4;
	
	private Handler largebufferHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			CharSequence text;
			Toast tmsg;
			switch (msg.what) {
				case ALLOCFAILED:
					text = "Failed to allocate.";
					tmsg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
					        tmsg.getYOffset() / 2);
					tmsg.show();					
					break;
				case ALLOCSUCCESS:
					text = "Allocated 1Mb.";
					tmsg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
					        tmsg.getYOffset() / 2);
					tmsg.show();
					bufferFIFO.offer((LargeBuffer)(msg.obj)); //now we actually add to the queue
					break;
				case REMOVEFAILED:
					text = "FAILURE in Release 1Mb.";
					tmsg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
					        tmsg.getYOffset() / 2);
					tmsg.show();
					break;
				case REMOVESUCCESS:
					text = "Release 1Mb.";
					tmsg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
					        tmsg.getYOffset() / 2);
					tmsg.show();	
					bufferFIFO.remove(); //now we actually remove from the queue
					System.gc();
					break;
				case ACTIONPENDING:
					text = "Action is pending.";
					tmsg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
					        tmsg.getYOffset() / 2);
					tmsg.show();	
			}
	        TextView textinfo = (TextView) findViewById(R.id.textView1);
			textinfo.setText("Allocated Mb="+bufferFIFO.size());
			super.handleMessage(msg);
		}
	};
	
	private static final int oneMb = 1024*1024*1; //1Mb
	Button allocButton;
	Button freeButton;
	 
	Queue<LargeBuffer> bufferFIFO;
	CacheMemoryDemoActivity act;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        act = this;
        bufferFIFO=new LinkedList<LargeBuffer>();
        thr = null;
        
        TextView textinfo = (TextView) findViewById(R.id.textView1);
		textinfo.setText("Allocated Mb=0");
        
        allocButton = (Button) findViewById(R.id.button1);
        allocButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {	
//				LargeBuffer lb = new LargeBufferMemory();
				LargeBuffer lb = new LargeBufferFile();
				lb.allocate(act, largebufferHandler, oneMb, bufferFIFO.size());
			}
		});
        
        freeButton = (Button) findViewById(R.id.button2);
        freeButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {	
				if (bufferFIFO.size()==0) {
					CharSequence text = "No more large buffers.";
					Toast msg = Toast.makeText(act, text, Toast.LENGTH_SHORT);
					msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
					        msg.getYOffset() / 2);
					msg.show();
				} else {
					LargeBuffer lb = bufferFIFO.peek();
					lb.remove(act, largebufferHandler, bufferFIFO.size());
				}		
			}
		});
    }
}