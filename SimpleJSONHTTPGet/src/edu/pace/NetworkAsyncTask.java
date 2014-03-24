package edu.pace;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkAsyncTask extends AsyncTask<String, Integer, Long> {
	public Activity act = null;
	public String citystr = null;
	WeatherHttpConnector wconnect = null;
	boolean res = false;
	Dialog dialog = null;
	@Override
	protected Long doInBackground(String... params) {
		 wconnect = new WeatherHttpConnector();
		 res = wconnect.executeHttpGetWeather(act,citystr);
		return null;
	}
	
	protected void onPostExecute(Long result) {
		if (res) {
			
	        TextView text = (TextView) dialog.findViewById(R.id.TextView01);
	        text.setText("The current temperature in "+citystr+" is "+wconnect.tempstr+" C.  The current weather condition is "+wconnect.infostr+"."+
	        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur nec felis magna. Vivamus volutpat dapibus porttitor. Donec elementum volutpat est, id condimentum arcu egestas a. Suspendisse sed lectus sit amet massa ultrices volutpat ac at elit. Nunc sit amet faucibus nibh. Etiam sit amet est at nisi consequat molestie et at est. In augue arcu, condimentum a blandit in, elementum vel elit. Nam erat quam, hendrerit in ultrices nec, tristique quis sapien. Pellentesque placerat sapien blandit est mollis ut sagittis felis semper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer risus eros, ullamcorper at laoreet non, vulputate non velit. Quisque ante erat, semper at iaculis in, congue quis justo. Duis a augue eu est eleifend sagittis at quis diam. Praesent fringilla odio ac urna iaculis nec faucibus nunc tincidunt. Proin congue nunc id felis rutrum hendrerit. Nunc imperdiet rhoncus elit, a laoreet dolor posuere nec. In ut erat vestibulum mi luctus porttitor. Donec ac ipsum ac turpis gravida venenatis. Nullam nibh urna, condimentum vitae mollis iaculis, suscipit ac purus. Etiam mattis faucibus diam nec faucibus. Sed molestie lorem at justo commodo quis dignissim metus iaculis. Suspendisse tincidunt rutrum congue. Proin accumsan ante in purus rhoncus faucibus quis id mauris. Integer eget ante vehicula ligula tincidunt ullamcorper ac posuere nunc. Nulla sed orci id tortor imperdiet tempor. Quisque ultricies dignissim adipiscing. In tempor interdum metus, eu molestie libero rhoncus quis.");
	        dialog.show();
	        
	        //set up image view
	        ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
	        if (wconnect.infostr.equals("rain"))
	        	img.setImageResource(R.drawable.rain);
	        else if (wconnect.infostr.equals("cloudy"))
	        	img.setImageResource(R.drawable.cloudy);
	        else img.setImageResource(R.drawable.sunny);	       
	        
		} else {
			Toast tmsg;
			if (wconnect.error_code==WeatherHttpConnector.ERROR_CODE_COMMUNICATON_PROBLEM) {
				tmsg = Toast.makeText(act, "Problem communicating to server.", Toast.LENGTH_SHORT);
			} else {
				tmsg = Toast.makeText(act, "Server reports that this is an Unknown city.", Toast.LENGTH_SHORT);
			}
			
			tmsg.setGravity(Gravity.CENTER, tmsg.getXOffset() / 2,
			        tmsg.getYOffset() / 2);
			tmsg.show();		
		}	
	}
}
