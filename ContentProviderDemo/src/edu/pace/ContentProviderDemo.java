package edu.pace;

import edu.pace.R;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.Toast;

public class ContentProviderDemo extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        displayRecords();
    }

    private void displayRecords() {
        // An array specifying which columns to return.

        String columns[] = new String[] { People.NAME, People.NUMBER };
        Uri mContacts = People.CONTENT_URI;
        Cursor cur = managedQuery(mContacts, columns, // Which columns to return
                null, // WHERE clause; which rows to return(all rows)
                null, // WHERE clause selection arguments (none)
                null // Order-by clause (ascending by name)

        );
        if (cur.moveToFirst()) {
            String name = null;
            String phoneNo = null;
            do {
                // Get the field values
                name = cur.getString(cur.getColumnIndex(People.NAME));
                phoneNo = cur.getString(cur.getColumnIndex(People.NUMBER));
                Toast.makeText(this, name + " " + phoneNo, Toast.LENGTH_LONG).show();
            } while (cur.moveToNext());
        }
    }
}