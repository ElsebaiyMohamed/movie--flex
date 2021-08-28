package com.s3.movieflex.ui;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.CastAdapter;
import com.s3.movieflex.model.Cast;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView movieImg, movieCover;
    TextView movieTitle, movieDesc;
    RecyclerView rvCast;
    ArrayList<Cast> cast=new ArrayList<>();
    CastAdapter castAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniView();
        cast();

    }

    private void iniView() {
        rvCast=findViewById(R.id.rv_cast);

        movieImg = findViewById(R.id.detail_movie_img);
        movieCover = findViewById(R.id.detail_movie_cover);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieDesc = findViewById(R.id.detail_movie_desc);
        if (getIntent() != null) {
            movieImg.setImageResource(getIntent().getExtras().getInt("imgUrl"));
            movieCover.setImageResource(getIntent().getExtras().getInt("imgCover"));
            movieTitle.setText(getIntent().getExtras().getString("title"));
            movieDesc.setText(getIntent().getExtras().getString("desc"));
            movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
            getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));
        }
    }

    private void cast(){
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));
        cast.add(new Cast("tom",R.drawable.eight));

        castAdapter=new CastAdapter(cast);
        rvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rvCast.setAdapter(castAdapter);

    }
}