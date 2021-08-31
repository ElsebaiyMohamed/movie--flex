package com.s3.movieflex.adapters.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

import java.util.ArrayList;

public class DbController {
    private final DbHelper databaseHelper;
    String tag = "A7A";
    private SQLiteDatabase database;

    public DbController(Context context) {
        databaseHelper = new DbHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
        Log.i("TAG", "open: ");
    }

    public long addMovie(Movie movie) {
        Log.i(tag, " add movie Db controller:  ");

        ContentValues values = new ContentValues();
        values.put(DbHelper.TAB1_COLO2, movie.getTitle());
        values.put(DbHelper.TAB1_COLO3, movie.getDescription());
        values.put(DbHelper.TAB1_COLO4, movie.getThumbnail());
        values.put(DbHelper.TAB1_COLO5, movie.getCover());
        values.put(DbHelper.TAB1_COLO7, movie.getRating());
        values.put(DbHelper.TAB1_COLO8, movie.getStreamingLink());

        long id = database.insert(DbHelper.TABLE1, null, values);
        addMovieCast(id, movie.getCast());
        Log.i(tag, "id: " + id);
        return id;
    }

    private void addMovieCast(long id, ArrayList<Cast> casts) {
        Log.i(tag, "id in cast: " + id);

        ContentValues contentValues = new ContentValues();
        for (Cast cast : casts) {
            contentValues.put(DbHelper.TAB2_COLO1, id);
            Log.i(tag, "id in for loop: " + id);
            contentValues.put(DbHelper.TAB2_COLO2, cast.getName());
            contentValues.put(DbHelper.TAB2_COLO3, cast.getImgLink());
        }
        database.insert(DbHelper.TABLE2, null, contentValues);
    }

    public void delete(long id) {
        Log.i(tag, "id on delete: " + id);
        deleteCast(id);
        database.delete(DbHelper.TABLE1, DbHelper.TAB1_COLO1 + "=" + id, null);
    }

    private void deleteCast(long id) {
        Log.i(tag, "id on delete: " + id);
        database.delete(DbHelper.TABLE2, DbHelper.TAB2_COLO1 + "=" + id, null);
    }

    public Movie selectMovie(long id) {
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE1 + " where " + DbHelper.TAB1_COLO1 + " = " + id, null);
        cursor.moveToFirst();
        if (cursor.isBeforeFirst()) {
            return new Movie(-1);
        } else {
            return new Movie(cursor.getLong(0));
        }
    }

    public ArrayList<Movie> selectAllMovie() {
        Log.i(tag, "on select method: ");
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<Cast> c1 = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE1, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            c1 = selectCast(cursor.getInt(0));
            Log.i(tag, "movie in select: " + cursor.getInt(0));
            movies.add(new Movie(cursor.getInt(5) ,cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    cursor.getFloat(6), cursor.getString(7), c1));
            cursor.moveToNext();
        }
        return movies;
    }

    private ArrayList<Cast> selectCast(long id) {
        ArrayList<Cast> cast = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE2, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            cast.add(new Cast(cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        Log.i(tag, "cast in select: " + cast.get(0).getName());

        return cast;
    }
    public void close() {
        databaseHelper.close();
    }
}