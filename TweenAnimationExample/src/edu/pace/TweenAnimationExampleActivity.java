package edu.pace;

import edu.pace.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TweenAnimationExampleActivity extends Activity {
        /** Called when the activity is first created. */
        @Override public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);
                final Animation a = AnimationUtils.loadAnimation(this, R.anim.rotater);
                a.reset();
                final TextView rText = (TextView) findViewById(R.id.animatedText);
                LinearLayout layout = (LinearLayout) findViewById(R.id.root);
                layout.setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                                rText.startAnimation(a);
                        }
                });
        }
}