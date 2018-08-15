package com.amir.odometer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OdometerDatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "odometer";
    private final static int DB_VERSION = 3;
    private static final String TABLE_NAME = "WALK";
    private static final String TAG = "Eminem";
    private String col1 = "Date";
    private String col2 = "TOTAL";
    private String col3 = "DONE";
    //
    public OdometerDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                +col1 + " TEXT, "+col2 + " TEXT, " + col3 + " INTEGER)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String date, String item, int done) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col1, date);
        cv.put(col2, item);
        cv.put(col3, done);
        Log.d(TAG, "addData: Adding" + item + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase dbb = this.getReadableDatabase();
        Cursor cr = dbb.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cr;
    }
}
