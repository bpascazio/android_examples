package edu.pace;

import edu.pace.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
//import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/* Activities in the system are managed as an activity stack. When a new activity is started, 
   it is placed on the top of the stack and becomes the running activity -- the previous activity 
   always remains below it in the stack, and will not come to the foreground again until the 
   new activity exits.

   An activity has essentially four states:

   If an activity in the foreground of the screen (at the top of the stack), it is active or running.
   
   If an activity has lost focus but is still visible (that is, a new non-full-sized or transparent 
     activity has focus on top of your activity), it is paused. A paused activity is completely 
     alive (it maintains all state and member information and remains attached to the window manager), 
     but can be killed by the system in extreme low memory situations.
     
   If an activity is completely obscured by another activity, it is stopped. It still retains all 
     state and member information, however, it is no longer visible to the user so its window is 
     hidden and it will often be killed by the system when memory is needed elsewhere.
     
   If an activity is paused or stopped, the system can drop the activity from memory by either 
     asking it to finish, or simply killing its process. When it is displayed again to the user, 
     it must be completely restarted and restored to its previous state.
*/

public class ExampleDialogStateActivity extends Activity {
	private static final String TAG = "ExampleDialogStateActivity";
	private static final int DIALOG_YES_NO_MESSAGE = 0;
	private static final int DIALOG_PROGRESS = 1;
	private static final int DIALOG_PROGRESS_TIMER = 2;
	
	private final static int STOPPROGRESS = 1;
	private final static int STOPPROGRESS_THREAD = 2;
	private final static int MESSAGE_PROGRESS = 3;
	
	public class MyThreadClass extends Thread 
	{
		 public Handler HandlerOfCaller;
	  // ***************************
	    // Public Constants
	    // ***************************
	  public final static byte STATE_NOT_STARTED = 0;
	  public final static byte STATE_RUNNING = 1;
	  public final static byte STATE_DONE = 2;
	  // *********************************
	    // Private properties
	    // *********************************
	  // Used to stop a running threaded process
	  private boolean mblnStop;
	  // Keep track of the running status of the thread
	  private byte mbytStatus = STATE_NOT_STARTED;
	  // *********************************
	    // Public properties
	    // *********************************
	    public double Latitude;
	    public String Name;
	  public String Email;
	  public int Quantity;
	  
	  MyThreadClass(Handler oHandler) 
	    {
	      HandlerOfCaller = oHandler;
	    }
	  
	  // **********************************
	    // Public methods
	    // **********************************
	    // This method is run when the thread
	  // is started by calling start().
	  @Override
	    public void run() 
	    {
	    // Flag that process does not need stopping
	    mblnStop = false;
	    mbytStatus = STATE_RUNNING;
	    
	      // Do something time consuming
	    for (int i = 0; i < 10; i++)
	    {
	      // Check if we need to stop executing this code
	      if (mblnStop)
	        break; // exit the loop to end the process
	      
	      // Update the progress of the file upload
	      if (HandlerOfCaller != null) {
	    	  Message oMessage = HandlerOfCaller.obtainMessage();
	    	  int intPercentageComplete = i*10;
	    	  oMessage.arg1 = intPercentageComplete;
	    	  oMessage.what = MESSAGE_PROGRESS;
	    	  HandlerOfCaller.sendMessage(oMessage);
	      } else {
	    	  Log.d(TAG, "can't send message!!!");
	      }
	      try 
	      {
	    	  Log.d(TAG, "MyThreadClass running "+i);
	    	  Thread.sleep(1000);
	      } 
	      catch (InterruptedException e) 
	      {
	        e.printStackTrace();
	      }
	    }
	      Log.d(TAG, "MyThreadClass done!");
	      
	      Message oMessage = HandlerOfCaller.obtainMessage();
	        oMessage.what = STOPPROGRESS_THREAD;
	        HandlerOfCaller.sendMessage(oMessage);
			
	      mbytStatus = STATE_DONE;
	    }
	  
	  // Set internal flag to stop threaded process
	  public void StopThread()
	  {
	    mblnStop = true;
	  }
	  
	  // Get the running status of the threaded process
	  public byte GetStatus()
	  {
	    return mbytStatus;
	  }
	}
    private MyThreadClass moMyThreadClass = null;
    private ProgressDialog dialog_progress = null;
    // onCreate(Bundle) is where you initialize your activity. Most importantly, 
	// here you will usually call setContentView(int) with a layout resource defining 
	// your UI, and using findViewById(int) to retrieve the widgets in that UI that 
	// you need to interact with programmatically.
	
	/* An activity will do all setup of "global" state in onCreate(), and release 
	 * all remaining resources in onDestroy(). For example, if it has a thread running 
	 * in the background to download data from the network, it may create that thread in 
	 * onCreate() and then stop the thread in onDestroy().
	 */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Almost all activities interact with the user, so the Activity class takes care 
        // of creating a window for you in which you can place your UI with setContentView(View).
        setContentView(R.layout.main);
        Log.d(TAG, "Activity.OnCreate should only ever be called when the Activity is created and initialized.");
        
        Button button1 = (Button) findViewById(R.id.create_dialog_alert);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.d(TAG, "clicked create dialog button");
				showDialog(DIALOG_YES_NO_MESSAGE);
			}
		});

        Button button2 = (Button) findViewById(R.id.create_dialog_progress);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.d(TAG, "clicked create dialog progress button");
				showDialog(DIALOG_PROGRESS);
				// Create instance of the worker thread class
				moMyThreadClass = new MyThreadClass(dialogHandler);
		          // Start the worker thread
		          moMyThreadClass.start();
			}
		});

        Button button3 = (Button) findViewById(R.id.create_progress_timer);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Log.d(TAG, "clicked create dialog progress timer button");
				showDialog(DIALOG_PROGRESS_TIMER);
				Message msg = new Message();
				msg.what = STOPPROGRESS;
				dialogHandler.sendMessageDelayed(msg, 5000); // ms
			}
		});
		
		 // Get the last sending thread if it was still going
		  if (getLastNonConfigurationInstance() != null)
		  {
			  
		        Log.d(TAG, "found getLastNonConfigurationInstance");
//		      TextView txtOutput = 
//		        (TextView)findViewById(R.id.txtOutput);
		    // Check that it is still running
		    moMyThreadClass = 
		      (MyThreadClass)getLastNonConfigurationInstance();
		    moMyThreadClass.HandlerOfCaller = dialogHandler;

	        Log.d(TAG, "moMyThreadClass.HandlerOfCaller setup correctly");

		    // Check that it is still running
		    switch (moMyThreadClass.GetStatus())
		    {
		    case MyThreadClass.STATE_RUNNING:
		      // Show the progress dialog again
		    	Log.d(TAG, "showing the dialog since it is running");
		      //showDialog(DIALOG_PROGRESS);
		      break;
		    case MyThreadClass.STATE_NOT_STARTED:
		      // Close the progress dialog in case it is open
		    	Log.d(TAG, "dismissing the dialog not started");
		      dismissDialog(DIALOG_PROGRESS);
		      break;
		    case MyThreadClass.STATE_DONE:
		      // Check the value returned
//		      txtOutput.setText("Process complete, result = " 
//		          + String.valueOf(moMyThreadClass.Result));
		      moMyThreadClass = null;
		      // Close the progress dialog in case it is open
		    	Log.d(TAG, "done");
		      //dismissDialog(DIALOG_PROGRESS);
		      break;
		    default:
		      // This should never occur
//		      txtOutput.setText(
//		          "Unknown MyThreadClass status type = " 
//		          + String.valueOf(moMyThreadClass.GetStatus()));
		      // Close the progress dialog in case it is open
		      dismissDialog(DIALOG_PROGRESS);
		      // Get rid of the sending thread
		      moMyThreadClass.StopThread();
		      moMyThreadClass = null;
		      break;
		    }
		  }
		
		
    }
    
    private Handler dialogHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case STOPPROGRESS:
	        	Log.d(TAG, "handler called dismissing TIMER dialog");
				dismissDialog(DIALOG_PROGRESS_TIMER);					
				break;
			case STOPPROGRESS_THREAD:
	        	Log.d(TAG, "handler called dismissing THREAD dialog");
				dismissDialog(DIALOG_PROGRESS);					
				break;
			case MESSAGE_PROGRESS:
		          // Update the progress bar
	        	Log.d(TAG, "progress in handler "+msg.arg1);

	        	dialog_progress.setProgress(msg.arg1);
		          break;	
		     default:
		                 // Close the progress dialog 
		    	 Log.d(TAG, "unknown message");
		              dismissDialog(DIALOG_PROGRESS);
		              // Get rid of the sending thread
		              moMyThreadClass.StopThread();
		              moMyThreadClass = null;
		              break;	
			}
			super.handleMessage(msg);
		}
	};
	// Store the sending thread between screen rotations
	@Override
	public Object onRetainNonConfigurationInstance() 
	{
		Log.d(TAG, "onRetainNonConfigurationInstance called");
	  //removeDialog(DIALOG_PROGRESS);
		Log.d(TAG, "removing dialog");
	  // Check that there is a worker thread that
	  // needs preserving
	  if (moMyThreadClass != null)
	  {
	    // remove reference to this activity 
	    // (important to avoid memory leak)
	    moMyThreadClass.HandlerOfCaller = null;
	    // Return the instance to be retained
	    return(moMyThreadClass);
	  }
	  return super.onRetainNonConfigurationInstance();
	}
	
    @Override
    protected Dialog onCreateDialog(int id) {
    	Log.d(TAG, "onCreateDialog called with "+id);
    	switch (id) {
        case DIALOG_YES_NO_MESSAGE:
            return new AlertDialog.Builder(ExampleDialogStateActivity.this)
                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle(R.string.alert_dialog_two_buttons_title)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                    }
                })
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked Cancel so do some stuff */
                    }
                })
                .create();
        case DIALOG_PROGRESS:
        	dialog_progress = new ProgressDialog(this);
        	dialog_progress.setTitle("Indeterminate");
        	dialog_progress.setMessage("Please wait while loading...");
        	//dialog_progress.setIndeterminate(true);
        	//dialog_progress.setCancelable(true);
        	dialog_progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            return dialog_progress;
        case DIALOG_PROGRESS_TIMER:
        	ProgressDialog dialog_progress_timer = new ProgressDialog(this);
        	dialog_progress_timer.setTitle("Indeterminate - Timer");
        	dialog_progress_timer.setMessage("Please wait while loading...");
        	dialog_progress_timer.setIndeterminate(true);
        	dialog_progress_timer.setCancelable(true);
            return dialog_progress_timer;
    	}
    	return null;
    }

    @Override
    public void onStart() {
    	super.onStart();
        Log.d(TAG, "Activity.onStart should only ever be called when the Activity is started.");
    }
    @Override
    public void onDestroy() {
    	super.onDestroy();
        Log.d(TAG, "Activity.onDestroy should only ever be called when the Activity is destroyed.");
    }
    // onPause() is where you deal with the user leaving your activity. Most importantly, 
    // any changes made by the user should at this point be committed (usually to the 
    // ContentProvider holding the data).
    //
    // Note that the activity can be paused and still visible, it may be resumed or stopped.
    @Override
    public void onPause() {
    	super.onPause();
        Log.d(TAG, "Activity.onPause is called when the activity is no longer in the foreground.");
    }
    @Override
    public void onResume() {
    	super.onResume();
        Log.d(TAG, "Activity.onResume is called when the activity is to re-gain the foreground.");
    }
    @Override
    public void onStop() {
    	super.onStop();
        Log.d(TAG, "Activity.onStop is called when the activity is no longer visible.");
    }
    @Override
    protected void onSaveInstanceState (Bundle outState) {
    	super.onSaveInstanceState(outState);
        Log.d(TAG, "Opportunity to save our instance state");
    }
}