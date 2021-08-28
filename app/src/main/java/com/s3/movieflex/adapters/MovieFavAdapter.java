package com.s3.movieflex.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.model.Movie;

import java.util.ArrayList;

public class MovieFavAdapter extends RecyclerView.Adapter<MovieFavAdapter.ViewHolder> {
    // arraylist to hold movies data
    static ArrayList<Movie> fList;
    //our custom listener te check the item clicked or no
    static MovieItemClickListener movieItemClickListener;

    public MovieFavAdapter(ArrayList<Movie> fList, MovieItemClickListener listener) {
        MovieFavAdapter.fList = fList;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_recycler_view, parent, false);
        // return our view and the listener on it
        return new ViewHolder(view);
    }

    // take the return value of above function as argument and get the position of it in recycler view
    // Note because of the recycler view indexing and Array list starts at zero index
    // then the position of any view on recycler view is same in array lis
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data from array list
        Movie film = fList.get(position);
        //assign the data to each view element
        holder.fTitle.setText(film.getTitle());
        holder.fRate.setText(film.getRating() + "/10");
        holder.fStudio.setText(film.getStudio());
        holder.fImage.setImageResource(film.getThumbnai());


    }

    // to get number of elements on the list
    @Override
    public int getItemCount() {
        return fList.size();
    }

    // our inner class that holds the items which we want draw and implements the on click listener on them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //the first item
        private TextView fTitle;
        private TextView fStudio;
        private TextView fRate;

        //the second item
        private final ImageView fImage;
        private final LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //make the class see each item that will be hold
            fTitle = (TextView) itemView.findViewById(R.id.fav_name);
            fStudio = (TextView) itemView.findViewById(R.id.fav_studio);
            fRate = (TextView) itemView.findViewById(R.id.fav_rate);

            fImage = (ImageView) itemView.findViewById(R.id.fav_img);
            layout=(LinearLayout) itemView.findViewById(R.id.labeled);
            //apply our listener to final view
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);

                }
            });
            fImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.onMovieClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });

        }


    }
}

