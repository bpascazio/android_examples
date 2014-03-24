package net.mobilecoders.BasicLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LinearLayoutActivity extends Activity {
	
	private int buttonCounter = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);
        final ImageButton ibutton1 = (ImageButton) findViewById(R.id.imageButton1);
        ibutton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Context context = getApplicationContext();
            	CharSequence text = "Image Button Toast!";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            }
        });
        final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView textview1 = (TextView) findViewById(R.id.textview1);
                textview1.setText(""+buttonCounter+" Hits");
                buttonCounter = buttonCounter + 1;
                final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                }
            }
        });
        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Context context = getApplicationContext();
            	CharSequence text = "";
            	int duration = Toast.LENGTH_SHORT;
            	final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
            	if (checkBox.isChecked()) {
                	text = "checked";
            	} else {
                	text = "not checked";
            	}
            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            }
        });
    }
}
