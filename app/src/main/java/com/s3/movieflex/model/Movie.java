package com.s3.movieflex.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable {
    private long _id;


    private String title;
    private String description;
    private int thumbnail;
    private int cover;
    private String studio;
    private float rating;
    private String streamingLink;
    private ArrayList<Cast> cast;


    public Movie(Movie movie) {

        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.thumbnail = movie.getThumbnail();
        this.studio = movie.getStudio();
        this.rating = movie.getRating();
        this.streamingLink = movie.streamingLink;
        this.cover = movie.getCover();
        this._id = movie.get_id();
    }

    public Movie(long _id) {
        this._id = _id;
    }

    public Movie(long _id, String title, String description, int thumbnail, int cover, String studio, float rating, String stremingLink, ArrayList<Cast> cast) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = stremingLink;
        this.cover = cover;
        this.cast = cast;
        this._id = _id;
    }

    public Movie(String title, String description, int thumbnail, int cover, String studio, float rating, String stremingLink, ArrayList<Cast> cast) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.streamingLink = stremingLink;
        this.cover = cover;
        this.cast = cast;
        this._id = -1;

    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
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

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
