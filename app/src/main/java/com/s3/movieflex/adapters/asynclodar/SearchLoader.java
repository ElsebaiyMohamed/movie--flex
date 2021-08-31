package com.s3.movieflex.adapters.asynclodar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.s3.movieflex.model.Movie;

import java.util.ArrayList;

public class SearchLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    //https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=movieName

    final String frontLink="https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=";
    private String filmName;

    public SearchLoader(Context context, String filmName) {
        super(context);
        this.filmName=filmName;
    }

    public SearchLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Movie> loadInBackground() {
        return null;
    }
}