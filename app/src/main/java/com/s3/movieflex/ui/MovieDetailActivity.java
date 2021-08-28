package com.s3.movieflex.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.CastAdapter;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView movieImg, movieCover, favState;
    TextView movieTitle, movieDesc;
    RecyclerView rvCast;
    ArrayList<Cast> cast = new ArrayList<>();
    CastAdapter castAdapter;
    Movie movieDetail;
    MaterialFavoriteButton favorite;
    String trail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniView();
        iniData();
    }

    private void iniView() {
        rvCast = findViewById(R.id.rv_cast);

        movieImg = findViewById(R.id.detail_movie_img);
        movieCover = findViewById(R.id.detail_movie_cover);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieDesc = findViewById(R.id.detail_movie_desc);
        favorite = findViewById(R.id.fav_btn);

    }


private void iniData(){
    if (getIntent() != null) {
        movieDetail=(Movie) getIntent().getExtras().getSerializable("movie");

        movieImg.setImageResource(movieDetail.getThumbnai());
        movieCover.setImageResource(movieDetail.getCover());
        trail=movieDetail.getStremingLink();
        movieTitle.setText(movieDetail.getTitle());
        movieDesc.setText(movieDetail.getDescription());
        cast=movieDetail.getCast();

        movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
        getSupportActionBar().setTitle(movieDetail.getTitle());




        castAdapter = new CastAdapter(cast);
        rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCast.setAdapter(castAdapter);


        favorite.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                if (favorite) {
                    Toast.makeText(getApplicationContext(), "favorite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();
                }

            }
        });
        if (isFav())
            favorite.setFavorite(true);
        else
            favorite.setFavorite(false);
    }
}

    private boolean isFav() {

        //check the data is exist on database


        return false;
    }


}