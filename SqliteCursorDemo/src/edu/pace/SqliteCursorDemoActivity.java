package edu.pace;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

// sqlite> drop table tbl_countries;
// sqlite> drop table tbl_states;

public class SqliteCursorDemoActivity extends Activity {

	long countryId = 0;
	SQLiteDatabase db;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		db = openOrCreateDatabase("TestingData.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		db.setVersion(1);
		db.setLocale(Locale.getDefault());
		db.setLockingEnabled(true);

		final String CREATE_TABLE_COUNTRIES = "CREATE TABLE tbl_countries ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "country_name TEXT);";
		final String CREATE_TABLE_STATES = "CREATE TABLE tbl_states ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "state_name TEXT,"
				+ "country_id INTEGER NOT NULL CONSTRAINT "
				+ "contry_id REFERENCES tbl_countries(id) "
				+ "ON DELETE CASCADE);";
		try {
			db.execSQL(CREATE_TABLE_COUNTRIES);
		} catch (Exception e) {
			// catch code
		}
		try {
			db.execSQL(CREATE_TABLE_STATES);
		} catch (Exception e) {
			// catch code
		}

		ContentValues values = new ContentValues();
		values.put("country_name", "Canada");
		countryId = db.insert("tbl_countries", null, values);
		values.put("country_name", "UK");
		countryId = db.insert("tbl_countries", null, values);
		values.put("country_name", "Mexico");
		countryId = db.insert("tbl_countries", null, values);
		values.put("country_name", "US");
		countryId = db.insert("tbl_countries", null, values);
		ContentValues stateValues = new ContentValues();
		stateValues.put("state_name", "Texas");
		stateValues.put("country_id", Long.toString(countryId));
		try {
			db.insertOrThrow("tbl_states", null, stateValues);
		} catch (Exception e) {
			// catch code
		}

		// Update our name
		Button updateTask = (Button) findViewById(R.id.button1);
		updateTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ContentValues updateCountry = new ContentValues();
				updateCountry.put("country_name", "United States");
				db.update("tbl_countries", updateCountry, "id=?",
						new String[] { Long.toString(countryId) });
			}
		});
		
		// Delete a state
		Button deleteTask = (Button) findViewById(R.id.button2);
		deleteTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				db.delete("tbl_states", "id=?", new String[] {Long.toString(countryId)});
			}
		});

		// Get list of countries
		Button getListTask = (Button) findViewById(R.id.button3);
		getListTask.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TextView view = (TextView) findViewById(R.id.textView1);
				view.setText("");
				Cursor cur = db.query("tbl_countries", null, null, null, null, null, null);
				cur.moveToFirst();
				while (cur.isAfterLast() == false) {
					view.append("\n" + cur.getString(1));
					cur.moveToNext();
				}
				cur.close();
			}
		});
	}
}