package com.s3.movieflex.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.CastAdapter;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView movieImg, movieCover;
    TextView movieTitle, movieDesc;
    RecyclerView rvCast;
    ArrayList<Cast> cast = new ArrayList<>();
    CastAdapter castAdapter;
    Movie movieDetail;
    ImageButton favorite;
    String trail;
    DbController controller;

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


    private void iniData() {
        controller = new DbController(getApplicationContext());
        controller.open();
        if (getIntent() != null) {
            movieDetail = (Movie) getIntent().getExtras().getSerializable("movie");
            Movie m = new Movie(controller.selectMovie(movieDetail.get_id()));
            if (m.get_id() == -1) {
                favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                movieDetail.set_id(-1);
            } else {
                favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);

            }
            movieImg.setImageResource(movieDetail.getThumbnail());
            movieCover.setImageResource(movieDetail.getCover());
            trail = movieDetail.getStreamingLink();
            movieTitle.setText(movieDetail.getTitle());
            movieDesc.setText(movieDetail.getDescription());
            cast = movieDetail.getCast();

            movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
            getSupportActionBar().setTitle(movieDetail.getTitle());


            castAdapter = new CastAdapter(cast);
            rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvCast.setAdapter(castAdapter);

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (movieDetail.get_id() != -1) {
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                        controller.delete(movieDetail.get_id());
                        movieDetail.set_id(-1);
                        Toast.makeText(getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();

                    } else {
                        favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                        movieDetail.set_id(controller.addMovie(movieDetail));
                        Toast.makeText(getApplicationContext(), "favorite", Toast.LENGTH_SHORT).show();


                    }
                }
            });


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.close();
    }
}