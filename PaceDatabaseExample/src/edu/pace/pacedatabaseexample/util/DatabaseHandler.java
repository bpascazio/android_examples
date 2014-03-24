package edu.pace.pacedatabaseexample.util;

import edu.pace.pacedatabaseexample.model.Department;
import edu.pace.pacedatabaseexample.model.Employee;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 2;

	public static final String DATABASE_NAME = "ExampleDatabase";

	public static final String TABLE_EMPLOYEES = "employees";
	public static final String TABLE_DEPARTMENT = "departments";

	public static final String EMPLOYEE_KEY_ID = "id";
	public static final String EMPLOYEE_KEY_NAME = "name";
	public static final String EMPLOYEE_KEY_PHONE = "phone";
	public static final String EMPLOYEE_KEY_DEPT_ID = "dept_id";

	public static final String DEPARTMENT_KEY_ID = "id";
	public static final String DEPARTMENT_KEY_NAME = "dept_name";

	private static final String TAG = "DatabaseHandler";

	private static final String TABLE_DEPARTMENTS = null;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
		String CREATE_EMPLOYEES_TABLE = "CREATE TABLE " + TABLE_EMPLOYEES
				+ "(" + EMPLOYEE_KEY_ID + " INTEGER PRIMARY KEY, "
				+ EMPLOYEE_KEY_NAME + " TEXT, " + EMPLOYEE_KEY_PHONE
				+ " TEXT, " + EMPLOYEE_KEY_DEPT_ID + " INTEGER);";
		Log.d(TAG, CREATE_EMPLOYEES_TABLE);
		db.execSQL(CREATE_EMPLOYEES_TABLE);

		String CREATE_DEPARTMENT_TABLE = "CREATE TABLE " + TABLE_DEPARTMENT
				+ "(" + DEPARTMENT_KEY_ID + " INTEGER PRIMARY KEY, "
				+ DEPARTMENT_KEY_NAME + " TEXT);";
		Log.d(TAG, CREATE_DEPARTMENT_TABLE);
		db.execSQL(CREATE_DEPARTMENT_TABLE);
		} catch (SQLException se) {
			Log.d(TAG, se.getMessage());
		}

	}

	public void addEmployee(Employee e, Department d) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DEPARTMENT_KEY_NAME, d._dept_name);

		db.insert(TABLE_EMPLOYEES, null, values);
		db.close();

	}

	public void addDepartment(Department d) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DEPARTMENT_KEY_NAME, d._dept_name);

		db.insert(TABLE_DEPARTMENT, null, values);
		db.close();

	}

	public Department getDepartment(String name) {

		SQLiteDatabase db = this.getReadableDatabase();
		Department d = null;
		Cursor cursor = db.query(false, TABLE_DEPARTMENT, new String[] {
				DEPARTMENT_KEY_ID, DEPARTMENT_KEY_NAME }, 
				DEPARTMENT_KEY_NAME + " = " + name, null, null, null, null, null, null);
		
		if (cursor != null) {
			d = new Department();
			d._dept_name=cursor.getString(2);
			d._id=cursor.getInt(1);
		}
		return d;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade "+oldVersion+" "+newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
		
	}

}
