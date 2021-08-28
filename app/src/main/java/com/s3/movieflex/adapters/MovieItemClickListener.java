package com.s3.movieflex.adapters;

import android.widget.ImageView;

import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

public interface MovieItemClickListener {
    void onMovieClick(Movie movie, ImageView movieImageView);
}
