package edu.pace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.Activity;
import android.provider.Settings.Secure;
import android.util.Log;

// 
// This class provides a simple stable HTTP communication to get weather information.
//

public class WeatherHttpConnector {

	private static final String SERVER_URL = "http://www.bytefly.com/apps/pace/simpledb.php?city=";

	final static String TAG = "executeHttpGetWeather";

	public final static int ERROR_CODE_COMMUNICATON_PROBLEM = -1;
	public final static int ERROR_CODE_SUCCESS 			  	= 0;
	public final static int ERROR_CODE_CITY_NOT_FOUND	    = 1;
	
	public String tempstr = "";
	public String infostr = "";
	public int error_code = ERROR_CODE_COMMUNICATON_PROBLEM; // assume communicaton problem

	// Call this function with a city name.
	
	public boolean executeHttpGetWeather(Activity act, String city) {

		boolean updated = false; // assume failure
		error_code = ERROR_CODE_COMMUNICATON_PROBLEM; // assume communicaton problem

		BufferedReader in = null;

		try {

			// for the url here, be sure to encode the parameter
			String citystr = java.net.URLEncoder.encode(city, "ISO-8859-1");
			String url = SERVER_URL + citystr;

			// we also add a unique id for tracking
			String android_id = Secure.getString(act.getContentResolver(),
					Secure.ANDROID_ID);
			url += "&id=" + android_id;

			Log.d(TAG, "url=" + url);

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();

			request.setURI(new URI(url));

			// set the user agent to from the phone os information
			String useragent = "android-"
					+ android.os.Build.VERSION.RELEASE;
			request.setHeader("User-Agent", useragent);
			Log.d(TAG, "useragent=" + useragent);

			// execute the http GET
			HttpResponse response = client.execute(request);

			in = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String page = sb.toString();
			Log.d(TAG, "response=" + page);
			
			//
			// Note that the reponse is JSON.
			//
			
			JSONTokener tokener = new JSONTokener(page);
			JSONObject res = (JSONObject) tokener.nextValue();
			int resultjson = (int) res.getLong("result");
			error_code = resultjson;
			if (resultjson == 0) {
				tempstr = res.getString("temp");
				infostr = res.getString("info");
				updated = true;
				Log.d(TAG, "operation success");
			} else {
				// error response code
				Log.d(TAG, "error response code=" + resultjson);
			}
		} catch (Exception e) {
			
			//
			// Note that this exception may be json parsing related or HTTP related.
			// That is why we assume a server communication error.
			//
			e.printStackTrace();
			
		} finally {
			
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return updated;
	}
}
