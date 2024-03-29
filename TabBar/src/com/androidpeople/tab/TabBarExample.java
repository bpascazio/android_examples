package com.androidpeople.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabBarExample extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        
        ImageView img = (ImageView) findViewById(R.id.ImageView01);
        img.setImageResource(R.drawable.adimage);
        
        /* TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        
        /* TabSpec setIndicator() is used to set name for the tab. */
        /* TabSpec setContent() is used to set content for a particular tab. */
        Resources res = getResources();
        firstTabSpec.setIndicator("Events",res.getDrawable(R.drawable.tab_icon_events)).setContent(new Intent(this,FirstTab.class));
        secondTabSpec.setIndicator("Home",res.getDrawable(R.drawable.tab_icon_home)).setContent(new Intent(this,SecondTab.class));
        thirdTabSpec.setIndicator("Prefs",res.getDrawable(R.drawable.tab_icon_more)).setContent(new Intent(this,Preferences.class));
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);
        
    }
}
