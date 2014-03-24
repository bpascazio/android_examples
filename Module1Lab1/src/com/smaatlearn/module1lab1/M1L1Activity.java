package com.smaatlearn.module1lab1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class M1L1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_l1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.m1_l1, menu);
        return true;
    }
    
}
