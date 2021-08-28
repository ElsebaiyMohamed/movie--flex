package com.s3.movieflex.model;

import java.util.ArrayList;

public class Movie {
   private String title;
   private String description;
   private int thumbnai;
   private int cover;
   private String studio;
   private String rating;
   private String stremingLink;
   private ArrayList<Cast> cast;
    public Movie(String title, String description, int thumbnai,int cover, String studio, String rating, String stremingLink,ArrayList<Cast> cast) {
        this.title = title;
        this.description = description;
        this.thumbnai = thumbnai;
        this.studio = studio;
        this.rating = rating;
        this.stremingLink = stremingLink;
        this.cover=cover;
        this.cast=cast;
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

    public int getThumbnai() {
        return thumbnai;
    }

    public void setThumbnai(int thumbnai) {
        this.thumbnai = thumbnai;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStremingLink() {
        return stremingLink;
    }

    public void setStremingLink(String stremingLink) {
        this.stremingLink = stremingLink;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Cast> cast) {
        this.cast = cast;
    }
}
