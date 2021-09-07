package com.s3.movieflex.adapters;

import android.widget.ImageView;

import com.s3.movieflex.model.TvModel;

public interface TvItemClickListener {
     void onTvClick(TvModel movie, ImageView movieImageView);
}
