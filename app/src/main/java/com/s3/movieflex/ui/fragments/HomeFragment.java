package com.s3.movieflex.ui.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.SliderPagerAdapter;
import com.s3.movieflex.adapters.asynclodar.MovieTaskLoader;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.Movie;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements MovieItemClickListener, LoaderManager.LoaderCallbacks {

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

    private final String movieTag = "movie/";
    private final String tvTag = "tv/";
    private final String movieUpcomingUrl = "upcoming";
    private final String moviepopularUrl = "popular";
    private final String movieTopRatedUrl = "top_rated";
    private final String movieNowPlayingUrl = "now_playing";
    private final String tvTopRatedUrl = "top_rated";
    private final String tvPopularUrl = "upcoming";
    private final String tvonAirUrl = "on_the_air";


    ArrayList<Movie> lstMovie = new ArrayList<>();
    ArrayList<Movie> lstMovieTop = new ArrayList<>();
    ArrayList<Movie> lstMoviePopular = new ArrayList<>();
    ArrayList<Movie> lstMoviePlaying = new ArrayList<>();
    ArrayList<Movie> lstTvTop = new ArrayList<>();
    ArrayList<Movie> lstTvPopular = new ArrayList<>();
    ArrayList<Movie> lstTvOnAir = new ArrayList<>();
    TabLayout indicators;
    DbController controller;

    RecyclerView moviesRV, moviesTop, moviesPlaying, tvTop, tvPopular, tvOnAir;
    ViewPager sliderPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        indicators = view.findViewById(R.id.indicators);
        sliderPager = view.findViewById(R.id.slider_pager);
        moviesRV = view.findViewById(R.id.Rv_movies);
        moviesTop = view.findViewById(R.id.Rv_movies_top);
        moviesPlaying = view.findViewById(R.id.Rv_movies_playing);
        tvTop = view.findViewById(R.id.Rv_tv_top);
        tvPopular = view.findViewById(R.id.Rv_tv_popular);
        tvOnAir = view.findViewById(R.id.Rv_tv_on_air);


        LoaderManager.getInstance(this).initLoader(1, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(2, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(3, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(4, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(5, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(6, null, this).forceLoad();
        LoaderManager.getInstance(this).initLoader(7, null, this).forceLoad();


        controller = new DbController(getContext());
        controller.open();






        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        ArrayList<Movie> m = new ArrayList<>();
        m = controller.selectAllMovie();
        for (Movie x : m) {

            if (x.getMovieId() == movie.getMovieId()) {
                movie.setMovieId(x.getMovieId());
                break;
            }
        }
        intent.putExtra("movie", movie);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
        startActivity(intent, options.toBundle());
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        switch (id) {
            case 1:
                return new MovieTaskLoader(getContext(), movieUpcomingUrl, movieTag);

            case 2:
                return new MovieTaskLoader(getContext(), moviepopularUrl, movieTag);
            case 3:
                return new MovieTaskLoader(getContext(), movieTopRatedUrl, movieTag);
            case 4:
                return new MovieTaskLoader(getContext(), movieNowPlayingUrl, movieTag);
            case 5:
                return new MovieTaskLoader(getContext(), tvTopRatedUrl, tvTag);
            case 6:
                return new MovieTaskLoader(getContext(), tvPopularUrl, tvTag);

            case 7:
                return new MovieTaskLoader(getContext(), tvonAirUrl, tvTag);
        }

        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        if (loader.getId() == 1) {
            lstMovie = (ArrayList<Movie>) data;
            SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(), lstMovie, this);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 3000, 5000);
            sliderPager.setAdapter(adapter);
            indicators.setupWithViewPager(sliderPager, true);
            adapter.notifyDataSetChanged();


        } else if (loader.getId() == 2) {
            lstMoviePopular = (ArrayList<Movie>) data;
            MovieAdapter adapter1 = new MovieAdapter(getContext(), lstMoviePopular, this);
            moviesRV.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            moviesRV.setAdapter(adapter1);
        } else if (loader.getId() == 3) {
            lstMovieTop = (ArrayList<Movie>) data;
            MovieAdapter adapter2 = new MovieAdapter(getContext(), lstMovieTop, this);
            moviesTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            moviesTop.setAdapter(adapter2);
        } else if (loader.getId() == 4) {
            lstMoviePlaying = (ArrayList<Movie>) data;
            MovieAdapter adapter3 = new MovieAdapter(getContext(), lstMoviePlaying, this);
            moviesPlaying.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            moviesPlaying.setAdapter(adapter3);
        } else if (loader.getId() == 5) {
            lstTvTop = (ArrayList<Movie>) data;
            MovieAdapter adapter4 = new MovieAdapter(getContext(), lstTvTop, this);
            tvTop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            tvTop.setAdapter(adapter4);
        } else if (loader.getId() == 6) {
            lstTvPopular = (ArrayList<Movie>) data;
            MovieAdapter adapter5 = new MovieAdapter(getContext(), lstTvPopular, this);
            tvPopular.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            tvPopular.setAdapter(adapter5);
        } else if (loader.getId() == 7) {
            lstTvOnAir = (ArrayList<Movie>) data;
            MovieAdapter adapter6 = new MovieAdapter(getContext(), lstTvOnAir, this);
            tvOnAir.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            tvOnAir.setAdapter(adapter6);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }


    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sliderPager.getCurrentItem() < lstMovie.size() - 1)
                            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                        else
                            sliderPager.setCurrentItem(0);
                    }
                });
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        controller.close();
    }
}