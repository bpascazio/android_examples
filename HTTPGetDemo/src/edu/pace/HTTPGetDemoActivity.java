package edu.pace;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HTTPGetDemoActivity extends Activity {
	
	public class TestHttpGet {
	    public void executeHttpGet(String url) throws Exception {
	        BufferedReader in = null;
	        try {
	            HttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet();
	            request.setURI(new URI(url));
	            request.setHeader("User-Agent", "My Android Phone");
	            HttpResponse response = client.execute(request);
	            in = new BufferedReader
	            (new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	            in.close();
	            String page = sb.toString();
	            Log.d("GET RESPONSE",page);
	            } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                    } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	Button getTask;
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        getTask = (Button) findViewById(R.id.button1);
        getTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TestHttpGet t = new TestHttpGet();
				try {
					t.executeHttpGet("http://216.254.98.167/pace/pace.php");
//					t.executeHttpGet("http://198.105.44.27/");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }
}