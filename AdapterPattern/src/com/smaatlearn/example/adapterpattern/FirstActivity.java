package com.smaatlearn.example.adapterpattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {

	//declare the button control
	Button click;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        click=(Button)findViewById(R.id.button1);
        //add a listener to the button which invokes the onClick function
        click.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{

				//create the explicit intent object and call the required activity
				Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
				startActivity(intent);

			/*
				//Implicit will be called by the android os				
				Intent implicit=new Intent(Intent.ACTION_CALL);
				startActivity(implicit);

				*/
			}
		});
    }

}
