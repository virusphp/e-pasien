/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package id.nexterweb.epasien.untils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {
	private static final String TAG = SQLiteHandler.class.getSimpleName();
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "db_epasien.db";
	// Login table name
	private static final String TABLE_PASIEN = "pasien";

	// Login Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_NO_RM = "no_rm";
	public static final String KEY_NAMA = "nama_pasien";
	public static final String KEY_TGL_LAHIR = "tgl_lahir";
	public static final String KEY_JNS_KEL = "jns_kel";
	public static final String KEY_JENIS_PASIEN = "jenis_pasien";
	public static final String KEY_TEMPAT_LAHIR = "tempat_lahir";
	public static final String KEY_ALAMAT = "alamat";
	public static final String KEY_TELP = "no_telp";
	private static final String KEY_CREATED_AT = "created_at";

	public SQLiteHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " +TABLE_PASIEN +" ( " +
				" "+KEY_NO_RM+" PRIMARY KEY," +
				" "+KEY_NAMA+" TEXT, " +
				" "+KEY_TEMPAT_LAHIR+" TEXT,"+
				" "+KEY_TGL_LAHIR+" TEXT,"+
				" "+KEY_JNS_KEL+" INTEGER,"+
				" "+KEY_JENIS_PASIEN+" INTEGER,"+
				" "+KEY_ALAMAT+" TEXT,"+
				" "+KEY_TELP+" TEXT)";
		db.execSQL(sql);
		Log.d(TAG, "Database tables created");
	}
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASIEN);
		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addPasien(Integer no_rm,String nama_pasien,String tempat_lahir, String tgl_lahir,Integer jns_kel,Integer jenis_pasien,String alamat, String no_telp) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NO_RM, no_rm);
		values.put(KEY_NAMA,nama_pasien);
		values.put(KEY_TEMPAT_LAHIR,tempat_lahir);
		values.put(KEY_TGL_LAHIR,tgl_lahir);
		values.put(KEY_JNS_KEL,jns_kel);
		values.put(KEY_JENIS_PASIEN,jenis_pasien);
		values.put(KEY_ALAMAT,alamat);
		values.put(KEY_TELP, no_telp);
		// Inserting Row
		long id = db.insert(TABLE_PASIEN, null, values);
		db.close(); // Closing database connection
		Log.d(TAG, "New user inserted into sqlite: " + id);
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getPasienDetails() {
		HashMap<String, String> pasien = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM " + TABLE_PASIEN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			pasien.put("no_rm", cursor.getString(0));
			pasien.put("nama_pasien", cursor.getString(1));
            pasien.put("tempat_lahir", cursor.getString(2));
            pasien.put("tgl_lahir", cursor.getString(3));
			pasien.put("jns_kel", cursor.getString(4));
			pasien.put("jenis_pasien", cursor.getString(5));
			pasien.put("alamat", cursor.getString(6));
			pasien.put("no_telp", cursor.getString(7));
		}
		cursor.close();
		db.close();
		Log.d(TAG, "Fetching user from Sqlite: " + pasien.toString());
		return pasien;
	}

	/**
	 * Re crate database Delete all tables and create them again
	 * */
	public void deleteUsers() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_PASIEN, null, null);
		db.close();

		Log.d(TAG, "Deleted all user info from sqlite");
	}

}
