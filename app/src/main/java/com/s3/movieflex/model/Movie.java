package com.s3.movieflex.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    private long movieId;
    private double rating;
    private String title;
    private String description;
    private String thumbnail;
    private String cover;
    private String streamingLink;
    private ArrayList<Cast> cast;


    public Movie(Movie movie) {

        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.thumbnail = movie.getThumbnail();
        this.rating = movie.getRating();
        this.streamingLink = movie.streamingLink;
        this.cover = movie.getCover();
        this.movieId = movie.getMovieId();
    }

    public Movie(long movieId) {
        this.movieId = movieId;
    }

    // MOVIE TASK LOADER
    public Movie(long movieId, String title, String description, String thumbnail,
                 String cover, double rating, String stremingLink, ArrayList<Cast> cast) {
        this.movieId = movieId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.streamingLink = stremingLink;
        this.cover = cover;
        this.cast = cast;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}
