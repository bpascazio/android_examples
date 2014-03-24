package edu.pace;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//
// Note that the server logs for this demo can be viewed at:
// http://216.254.98.167/pace/hits.php?verbose=1
//

public class SimpleJSONHTTPGetActivity extends Activity {

	Activity act = this;

	Dialog dialog;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		String[] items = new String[] {"Belize City", "Punta Gorda", "Dangriga", "San Pedro"};
		Spinner spinner = (Spinner) findViewById(R.id.city);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
		spinner.setAdapter(adapter);
		
		
		//set up dialog
        dialog = new Dialog(act);
        dialog.setContentView(R.layout.maindialog);
        dialog.setTitle("Weather Report");
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
		
		Button updateTask;
		updateTask = (Button) findViewById(R.id.updatebutton);
		updateTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//moved WeatherHttpConnector wconnect = new WeatherHttpConnector();
				try {
					Spinner cityedit = (Spinner) findViewById(R.id.city);
					String citystr = (String)cityedit.getItemAtPosition(cityedit.getLastVisiblePosition());

					NetworkAsyncTask nat = new NetworkAsyncTask();
					nat.citystr = citystr;
					nat.act = act;
					nat.dialog = dialog;
					nat.execute("");
					
					/*
					if (wconnect.executeHttpGetWeather(act,citystr)) {
						
				        TextView text = (TextView) dialog.findViewById(R.id.TextView01);
				        text.setText("The current temperature in "+citystr+" is "+wconnect.tempstr+" C.  The current weather condition is "+wconnect.infostr+".");
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
					*/
				} catch (Exception e) {

				}
			}
		});

	}
}