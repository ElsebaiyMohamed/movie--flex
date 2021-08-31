package com.s3.movieflex.adapters.asynclodar;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MovieTaskLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    ArrayList<Movie> movies = new ArrayList<>();
    String url = null;
    final String frontLink = "https://api.themoviedb.org/3/";

    final String backLink = "?api_key=452671fab9e5a7eb0349b6139855d282";
    final String trial = "/videos";
    final String actors = "/credits";
    final String UTubeLink = "https://www.youtube.com/watch?v=";
    final String tag;

    public MovieTaskLoader(@NonNull Context context, String url, String tag) {
        super(context);
        this.url = url;
        this.tag = tag;
    }

    @Nullable
    @Override
    public ArrayList<Movie> loadInBackground() {
        // Parsing JSON Object
        try {
            String jsonString = getHttpRequest(new URL(frontLink + tag + url + backLink));
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            final int resultLength = jsonArray.length();
            for (int i = 0; i < resultLength; i++) {
                JSONObject movies = jsonArray.getJSONObject(i);
                int id = movies.getInt("id");
                double rate = movies.getDouble("vote_average");
                String title = null;
                title = movies.getString("title");
                String coverLink = movies.getString("backdrop_path");
                String poster = movies.getString("poster_path");
                String description = movies.getString("overview");

                String jsonTrial = getHttpRequest(new URL(frontLink + tag + id + trial + backLink));
                JSONObject trialObject = new JSONObject(jsonTrial);
                String trial1 = null;
                if (trialObject.has("key"))
                    trial1 = UTubeLink + trialObject.getString("key");


                String jsonCast = getHttpRequest(new URL(frontLink + tag + id + actors + backLink));
                JSONObject castObject = new JSONObject(jsonCast);
                JSONArray castArray = castObject.getJSONArray("cast");
                ArrayList<Cast> actorsList = new ArrayList<>();
                final int castLength = castArray.length();
                for (int j = 0; j < castLength; j++) {
                    JSONObject cast2 = castArray.getJSONObject(j);
                    if (cast2.has("name"))
                        actorsList.add(new Cast(cast2.getString("name"), cast2.getString("profile_path")));
                }
                this.movies.add(new Movie(-1, id, title, description, poster, coverLink, rate, trial1, actorsList));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private String getHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        InputStream inputStream = null;
        HttpsURLConnection urlConnection = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();  // open connection + casting to HTTPS
            urlConnection.setRequestMethod("GET");                      // Type of connection is getting data
            urlConnection.setReadTimeout(5000);                        // Get Data Time = 10 seconds
            urlConnection.setConnectTimeout(10000);                     // Bad Connection Time = 20 seconds
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // maybe there is an error here in return type of function
    private String readInputStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader inputReader = new BufferedReader(inputStreamReader);
            String line = inputReader.readLine();
            while (line != null) {
                result.append(line);
                line = inputReader.readLine();
            }
        }
        return result.toString();
    }
}