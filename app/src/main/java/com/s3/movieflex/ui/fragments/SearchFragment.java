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

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieFavAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements MovieItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    MovieFavAdapter adapter;
    ArrayList<Movie> film = new ArrayList<>();
    ArrayList<Cast> casts=new ArrayList<>();
    RecyclerView search_result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        adapter = new MovieFavAdapter(getContext(), film, (MovieItemClickListener) this);
        search_result = view.findViewById(R.id.search_result);
        search_result.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        search_result.setAdapter(adapter);
        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        if(getActivity()!=null) {

            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie",movie);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
            startActivity(intent, options.toBundle());
        }
    }
}