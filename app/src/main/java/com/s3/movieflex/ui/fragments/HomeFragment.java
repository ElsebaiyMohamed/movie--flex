package com.s3.movieflex.ui.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.SliderPagerAdapter;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;
import com.s3.movieflex.model.Slide;
import com.s3.movieflex.ui.MainActivity;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements MovieItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    ArrayList<Movie> lstSlide = new ArrayList<>();
    ArrayList<Movie> lstMovie = new ArrayList<>();
    ArrayList<Movie> lstMovieTop = new ArrayList<>();
    ArrayList<Movie> lstMoviePopular = new ArrayList<>();
    ArrayList<Movie> lstMoviePlaying = new ArrayList<>();
    ArrayList<Movie> lstTvTop = new ArrayList<>();
    ArrayList<Movie> lstTvPopular = new ArrayList<>();
    ArrayList<Movie> lstTvOnAir = new ArrayList<>();
    ArrayList<Cast> casts=new ArrayList<>();
    TabLayout indicators;
    RecyclerView moviesRV, moviesTop, moviesPlaying, tvTop, tvPopular, tvOnAir;
    ViewPager sliderPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        indicators = view.findViewById(R.id.indicators);
        sliderPager = view.findViewById(R.id.slider_pager);
        moviesRV = view.findViewById(R.id.Rv_movies);
        moviesTop = view.findViewById(R.id.Rv_movies_top);
        moviesPlaying = view.findViewById(R.id.Rv_movies_playing);
        tvTop = view.findViewById(R.id.Rv_tv_top);
        tvPopular = view.findViewById(R.id.Rv_tv_popular);
        tvOnAir = view.findViewById(R.id.Rv_tv_on_air);
        //////////////////////////////////////////
        lstSlide.add(new Movie( "Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstSlide.add(new Movie( "Alpha","sauhfwehuhufhof", R.drawable.fourth, R.drawable.fourth,"rdgrr","wewgtwe","ewwewfef",casts));
        lstSlide.add(new Movie( "Alpha","sauhfwehuhufhof", R.drawable.fifth, R.drawable.fifth,"rdgrr","wewgtwe","ewwewfef",casts));
        lstSlide.add(new Movie( "Alpha","sauhfwehuhufhof", R.drawable.first, R.drawable.first,"rdgrr","wewgtwe","ewwewfef",casts));
        ///////////////////////////////////////
        // upcoming
        lstMovie.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovie.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovie.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovie.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //popular movies
        lstMoviePopular.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePopular.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePopular.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePopular.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //top rated movies
        lstMovieTop.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovieTop.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovieTop.add(new Movie("Blade Runner","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMovieTop.add(new Movie("Black widow","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //playing movies
        lstMoviePlaying.add(new Movie("Alpha", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePlaying.add(new Movie("Mockingly", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePlaying.add(new Movie("Blade Runner","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstMoviePlaying.add(new Movie("Black widow", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //top tv shows
        lstTvTop.add(new Movie("Alpha","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvTop.add(new Movie("Mockingly","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvTop.add(new Movie("Blade Runner", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvTop.add(new Movie("Black widow", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //popular tv shows
        lstTvPopular.add(new Movie("Alpha", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvPopular.add(new Movie("Mockingly","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvPopular.add(new Movie("Blade Runner","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvPopular.add(new Movie("Black widow","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        //on the air tv shows
        lstTvOnAir.add(new Movie("Alpha", "sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvOnAir.add(new Movie("Mockingly","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvOnAir.add(new Movie("Blade Runner","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        lstTvOnAir.add(new Movie("Black widow","sauhfwehuhufhof", R.drawable.eight, R.drawable.eight,"rdgrr","wewgtwe","ewwewfef",casts));
        /////////////////////////////////////////////////////////////
        SliderPagerAdapter adapter = new SliderPagerAdapter(getActivity(), lstSlide,this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 4000, 6000);
        sliderPager.setAdapter(adapter);
        indicators.setupWithViewPager(sliderPager, true);
        ////////////////////////////////////////////////////////////////
        //popular movies
        MovieAdapter adapter1 = new MovieAdapter(lstMovie, this);
        moviesRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moviesRV.setAdapter(adapter1);
        //top rated movies
        MovieAdapter adapter2 = new MovieAdapter(lstMovieTop, this);
        moviesTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moviesTop.setAdapter(adapter2);
        //playing movies
        MovieAdapter adapter3 = new MovieAdapter(lstMoviePlaying, this);
        moviesPlaying.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        moviesPlaying.setAdapter(adapter3);
        //top tv shows
        MovieAdapter adapter4 = new MovieAdapter(lstTvTop, this);
        tvTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        tvTop.setAdapter(adapter4);
        //popular tv
        MovieAdapter adapter5 = new MovieAdapter(lstTvPopular, this);
        tvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        tvPopular.setAdapter(adapter5);
        //on the air tv
        MovieAdapter adapter6 = new MovieAdapter(lstTvOnAir, this);
        tvOnAir.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        tvOnAir.setAdapter(adapter6);


        return view;
    }


    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgUrl", movie.getThumbnai());
        intent.putExtra("desc", "Alpha is a 2018 American prehistorical adventure film directed by Albert Hughes and written\n" +
                "        by Daniele Sebastian Wiesenthal, from a story by Hughes. The film stars Kodi Smit-McPhee as a young hunter who \n" +
                "        encounters and befriends an injured wolf during the last ice age, with Jóhannes Haukur Jóhannesson as his father.");
        intent.putExtra("imgCover", movie.getCover());

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }
    public  class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if(getActivity()!=null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sliderPager.getCurrentItem() < lstSlide.size() - 1)
                            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                        else
                            sliderPager.setCurrentItem(0);

                    }
                });
            }
        }
    }

}