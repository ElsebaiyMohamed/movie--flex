package com.s3.movieflex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.s3.movieflex.R;
import com.s3.movieflex.model.TvModel;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {
    // arraylist to hold movies data
    static ArrayList<TvModel> fList;
    //our custom listener te check the item clicked or no
    static TvItemClickListener tvItemClickListener;
    private final String baseURL = "https://image.tmdb.org/t/p/original";
    private final Context context;

    public TvAdapter(Context context, ArrayList<TvModel> fList, TvItemClickListener listener) {
        TvAdapter.fList = fList;
        TvAdapter.tvItemClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        // return our view and the listener on it
        return new TvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the data from array list
        TvModel film = fList.get(position);
        //assign the data to each view element
        holder.fTitle.setText(film.getName());
        Glide.with(context).load(baseURL + film.getPoster_path()).into(holder.fImage);

    }

    // take the return value of above function as argument and get the position of it in recycler view
    // Note because of the recycler view indexing and Array list starts at zero index
    // then the position of any view on recycler view is same in array lis


    // to get number of elements on the list
    @Override
    public int getItemCount() {
        return fList.size();
    }

    // our inner class that holds the items which we want draw and implements the on click listener on them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //the first item
        private final TextView fTitle;
        //the second item
        private final ImageView fImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //make the class see each item that will be hold
            fTitle = itemView.findViewById(R.id.film_title);
            fImage = itemView.findViewById(R.id.film_img);
            //apply our listener to final view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvItemClickListener.onTvClick(fList.get(getBindingAdapterPosition()), fImage);
                }
            });
        }
    }

}
