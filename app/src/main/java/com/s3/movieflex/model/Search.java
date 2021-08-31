package com.s3.movieflex.model;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Search {

    //https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=movieName

    private static final String frontLink="https://api.themoviedb.org/3/search/movie?api_key=452671fab9e5a7eb0349b6139855d282&query=";
    private static final String queries="q";
    private static final String max_Result="max_result";
    private static final String Print_Type="print_type";


    static String getFilmInfo(String queryString){
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        return bookJSONString;

        try{
            Uri buildUri = Uri.parse(frontLink).buildUpon().appendQueryParameter(queries,queryString)
                    .appendQueryParameter(max_Result,10)
                    .appendQueryParameter(Print_Type,"world war z").build();

            URL url = new URL(buildUri.toString());

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");   // Type of connection is getting data
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer = buffer.append(line+"\n");
            }
            if(buffer.length()==0){
                // stream is empty
                return null;
            }


        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return bookJSONString;
        }

    }


}
