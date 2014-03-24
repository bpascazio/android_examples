package net.mobilecoders.BasicDialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicDialogsActivity extends Activity {

	private static final int DIALOG_YES_NO_MESSAGE = 1;
	private static final int DIALOG_LIST = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_YES_NO_MESSAGE);
			}
		});
		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DIALOG_LIST);
			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_YES_NO_MESSAGE:
			return new AlertDialog.Builder(BasicDialogsActivity.this).setTitle(
					R.string.alert_dialog_two_buttons_title).setPositiveButton(
					R.string.alert_dialog_ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked OK so do some stuff */
						}
					}).setNegativeButton(R.string.alert_dialog_cancel,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked Cancel so do some stuff */
						}
					}).create();
		case DIALOG_LIST:
			return new AlertDialog.Builder(BasicDialogsActivity.this).setTitle(
					R.string.select_dialog).setItems(
					R.array.select_dialog_items,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							/* User clicked so do some stuff */
							String[] items = getResources().getStringArray(
									R.array.select_dialog_items);
							new AlertDialog.Builder(BasicDialogsActivity.this)
									.setMessage(
											"You selected: " + which + " , "
													+ items[which]).show();
						}
					}).create();
		}
		return null;
	}
}