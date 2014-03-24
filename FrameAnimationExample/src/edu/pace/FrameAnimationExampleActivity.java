package edu.pace;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.drawable.*;
import android.view.View;
import android.widget.*;

public class FrameAnimationExampleActivity extends Activity {
         AnimationDrawable animation;
        @Override
           public void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.main);
              
               Button btnStart = (Button) findViewById(R.id.btnStart);
               final ImageView imgView = (ImageView)findViewById(R.id.img);
              
               btnStart.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                     startAnimation();
                  }
               });
               imgView.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
    
           }
            });
           }
        
        class Starter implements Runnable {
              public void run() {
                   animation.start();
               }
           }
          
           private void startAnimation(){
               animation = new AnimationDrawable();
               animation.addFrame(getResources().getDrawable(R.drawable.h1), 100);
               animation.addFrame(getResources().getDrawable(R.drawable.h2), 100);
               animation.addFrame(getResources().getDrawable(R.drawable.h3), 100);
               animation.addFrame(getResources().getDrawable(R.drawable.h4), 100);
               animation.setOneShot(false);
              
               ImageView imageView = (ImageView) findViewById(R.id.img);
               RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(80, 90);
               params.alignWithParent = true;
               params.addRule(RelativeLayout.CENTER_IN_PARENT);      
               imageView.setLayoutParams(params);
               imageView.setImageDrawable(animation);
               imageView.post(new Starter());
           }
        }
