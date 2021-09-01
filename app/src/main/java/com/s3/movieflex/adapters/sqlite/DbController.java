package com.s3.movieflex.adapters.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

import java.util.ArrayList;
public class DbController {
    private final DbHelper databaseHelper;
    private SQLiteDatabase database;
    public DbController(Context context) {
        databaseHelper = new DbHelper(context);
    }
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }
    public long addMovie(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.TAB1_COLO2, movie.getTitle());
        values.put(DbHelper.TAB1_COLO3, movie.getDescription());
        values.put(DbHelper.TAB1_COLO4, movie.getThumbnail());
        values.put(DbHelper.TAB1_COLO5, movie.getCover());
        values.put(DbHelper.TAB1_COLO6, movie.getMovieId());
        values.put(DbHelper.TAB1_COLO7, movie.getRating());
        values.put(DbHelper.TAB1_COLO8, movie.getStreamingLink() + "m");
        long id = database.insert(DbHelper.TABLE1, DbHelper.TAB1_COLO8, values);
        addMovieCast(id, movie.getCast());
        return id;
    }
    private void addMovieCast(long id, ArrayList<Cast> casts) {
        ContentValues contentValues = new ContentValues();
        for (Cast cast : casts) {
            contentValues.put(DbHelper.TAB2_COLO1, id);
            contentValues.put(DbHelper.TAB2_COLO2, cast.getName());
            contentValues.put(DbHelper.TAB2_COLO3, cast.getImgLink());
        }
        database.insert(DbHelper.TABLE2, null, contentValues);
    }
    public void delete(long id) {
        deleteCast(id);
        database.delete(DbHelper.TABLE1, DbHelper.TAB1_COLO1 + "=" + id, null);
    }
    private void deleteCast(long id) {
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
        ArrayList<Movie> movies = new ArrayList<>();
        ArrayList<Cast> c1 = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from " + DbHelper.TABLE1, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            c1 = selectCast(cursor.getInt(0));
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

        return cast;
    }
    public void close() {
        databaseHelper.close();
    }
}