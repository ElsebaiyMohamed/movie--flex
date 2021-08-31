package com.s3.movieflex.model;

import android.net.Uri;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class Search {

    //https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=movieName

    private static final String frontLink="https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=";

    private final String queries="q";

    static String getFilmInfo(String queryString){
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        String bookJSONString = null;
        return bookJSONString;

        try{
            Uri buildUri = Uri.parse(frontLink).buildUpon().appendQueryParameter(queries, queryString)
        } catch (Exception e){

        } finally {
            return bookJSONString;
        }


    }


}
