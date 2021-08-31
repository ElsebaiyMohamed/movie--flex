package com.s3.movieflex.adapters.asyncLoader;

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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MovieTaskLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    ArrayList<Movie> movies = new ArrayList<>();
    String url = null;
    final String frontLink = "https://api.themoviedb.org/3/movie/";
    final String backLink = "?api_key=452671fab9e5a7eb0349b6139855d282";
    final String trial = "/videos";
    final String actors = "/credits";
    final String UTubeLink = "https://www.youtube.com/watch?v=";

    public MovieTaskLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Nullable
    @Override
    public ArrayList<Movie> loadInBackground() {
        // Parsing JSON Object
        try {
            String jsonString = getHttpRequest(new URL(frontLink + url + backLink));
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = new JSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject movies = jsonArray.getJSONObject(i);
                int id = movies.getInt("id");
                double rate = movies.getDouble("vote_average");
                String title = movies.getString("title");
                String coverLink = movies.getString("backdrop_path");
                String poster = movies.getString("poster_path");
                String description = movies.getString("overview");

                String jsonTrial = getHttpRequest(new URL(frontLink + id + trial + backLink));
                JSONObject trialObject = new JSONObject(jsonTrial);
                JSONArray trialArray = new JSONArray("results");
                String trial1 = null;
                for (int j = 0; j < trialArray.length(); j++) {
                    JSONObject trial2 = jsonArray.getJSONObject(j);
                    trial1=UTubeLink+trial2.getString("key");
                }

                String jsonCast = getHttpRequest(new URL(frontLink + id + actors + backLink));
                JSONObject castObject = new JSONObject(jsonCast);
                JSONArray castArray = new JSONArray("cast");
                ArrayList<Cast> actorsList= new ArrayList<>();
                for (int j = 0; j < castArray.length(); j++) {
                    JSONObject cast2 = jsonArray.getJSONObject(j);
                    actorsList.add(new Cast(cast2.getString("name"), cast2.getString("profile_path")));
                }
                this.movies.add(new Movie(-1 ,id, title, description, poster, coverLink, rate,trial1,actorsList));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
            urlConnection.setReadTimeout(10000);                        // Get Data Time = 10 seconds
            urlConnection.setConnectTimeout(20000);                     // Bad Connection Time = 20 seconds
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
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
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