package com.example.sejo.navdra;

/**
 * FilmData
 * Created by pr_idi on 10/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FilmData {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    // Here we only select Title and Director, must select the appropriate columns
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_TITLE,
            MySQLiteHelper.COLUMN_DIRECTOR,
            MySQLiteHelper.COLUMN_COUNTRY,
            MySQLiteHelper.COLUMN_YEAR_RELEASE,
            MySQLiteHelper.COLUMN_PROTAGONIST,
            MySQLiteHelper.COLUMN_CRITICS_RATE};

    public FilmData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Film createFilm(String title, String pais, String bany, String director, String prot, String punts) {
        ContentValues values = new ContentValues();
        Log.d("Creating", "Creating " + title + " " + director);
        System.out.println("AAAA");
        // Add data: Note that this method only provides title and director
        // Must modify the method to add the full data
        values.put(MySQLiteHelper.COLUMN_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_COUNTRY, pais);
        values.put(MySQLiteHelper.COLUMN_YEAR_RELEASE, Integer.parseInt(bany));
        values.put(MySQLiteHelper.COLUMN_DIRECTOR, director);
        // Invented dat
        values.put(MySQLiteHelper.COLUMN_PROTAGONIST, prot);
        values.put(MySQLiteHelper.COLUMN_CRITICS_RATE, Integer.parseInt(punts));

        // Actual insertion of the data using the values variable
        long insertId = database.insert(MySQLiteHelper.TABLE_FILMS, null,
                values);

        // Main activity calls this procedure to create a new film
        // and uses the result to update the listview.
        // Therefore, we need to get the data from the database
        // (you can use this as a query example)
        // to feed the view.


        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();
        Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(cursor));
        Film newFilm = cursorToFilm(cursor);

        // Do not forget to close the cursor
        cursor.close();

        // Return the book
        return newFilm;
    }

    public void deleteFilm(Film film) {
        long id = film.getId();
        System.out.println("Film deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_FILMS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Film> getAllFilms() {
        List<Film> comments = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns, null, null, null, null, MySQLiteHelper.COLUMN_TITLE+" ASC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Film comment = cursorToFilm(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return comments;
    }


    public List<Film> FindFilms(String s) {
        List<Film> comments = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns,
                MySQLiteHelper.COLUMN_TITLE + " LIKE *" + s + "* OR "
                        + MySQLiteHelper.COLUMN_PROTAGONIST + " LIKE *" + s + "*",
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Film comment = cursorToFilm(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        return comments;
    }


    public List<Film> gettAllFilmsYear()
    {
        List<Film> comments = new ArrayList<>();

        Cursor c = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns, null, null, null, null, MySQLiteHelper.COLUMN_YEAR_RELEASE+" ASC");

        c.moveToFirst();
        while (!c.isAfterLast()) {
            Film f = new Film();
            f.setId(c.getLong(0));
            f.setTitle(c.getString(1));
            f.setDirector(c.getString(2));
            f.setCountry(c.getString(3));
            f.setCritics_rate(c.getInt(6));
            f.setProtagonist(c.getString(5));
            f.setYear(c.getInt(4));
            comments.add(f);
            c.moveToNext();
        }
        // make sure to close the cursor
        c.close();

        return comments;
    }

    public Film getFilm(String peli, String dire)
    {
        //Asumimos que nunca puede haver 2 peliculas con elmismo nombre y director
        System.out.println("TEST");
        Cursor c = database.query(MySQLiteHelper.TABLE_FILMS,
                allColumns,MySQLiteHelper.COLUMN_TITLE+" = '"+peli+"' AND "+MySQLiteHelper.COLUMN_DIRECTOR+" = '"+dire+"'", null, null, null, null);
        c.moveToFirst();
        Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(c));
        Film f = new Film();
        f.setId(c.getLong(0));
        f.setTitle(c.getString(1));
        f.setDirector(c.getString(2));
        f.setCountry(c.getString(3));
        f.setCritics_rate(c.getInt(6));
        f.setProtagonist(c.getString(5));
        f.setYear(c.getInt(4));
        System.out.println(f.getTitle());
        System.out.println(f.getCountry());
        c.close();
        return f;
    }


    private Film cursorToFilm(Cursor cursor) {
        Film film = new Film();
        film.setId(cursor.getLong(0));
        film.setTitle(cursor.getString(1));
        film.setDirector(cursor.getString(2));
        return film;
    }
}