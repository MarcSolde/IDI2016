package com.example.sejo.navdra;

/**
 * MySQLiteHelper
 * Created by pr_idi on 10/11/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.StringBuilderPrinter;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_FILMS = "films";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_YEAR_RELEASE = "year_release";
    public static final String COLUMN_DIRECTOR = "director";
    public static final String COLUMN_PROTAGONIST = "protagonist";
    public static final String COLUMN_CRITICS_RATE = "critics_rate";

    private static final String DATABASE_NAME = "films.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_FILMS + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_COUNTRY + " text not null, "
            + COLUMN_YEAR_RELEASE + " integer not null, "
            + COLUMN_DIRECTOR + " text not null, "
            + COLUMN_PROTAGONIST + " text not null, "
            + COLUMN_CRITICS_RATE + " integer"
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        onCreate(db);
    }

    public void insert(String titol, String pais, String any, String dir, String prot, String punts)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, titol);
        cv.put(COLUMN_COUNTRY, pais);
        cv.put(COLUMN_YEAR_RELEASE, any);
        cv.put(COLUMN_DIRECTOR, dir);
        cv.put(COLUMN_PROTAGONIST, prot);
        cv.put(COLUMN_CRITICS_RATE, punts);
        long ret = db.insert(TABLE_FILMS, null, cv);
        db.close();
    }


}