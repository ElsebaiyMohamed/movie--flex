package com.s3.movieflex.adapters.retrofit;

import com.s3.movieflex.model.TvModel;

import java.util.ArrayList;

public class JsonTvRespons {
    ArrayList<TvModel> results;

    public ArrayList<TvModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<TvModel> results) {
        this.results = results;
    }
}
