package com.example.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView tvOverView;
        ImageView tvPoster;
        RelativeLayout container;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            tvOverView = itemView.findViewById(R.id.tvOverview);
            tvPoster = itemView.findViewById(R.id.idPoster);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(final Movie movie) {
            title.setText(movie.getTitle());
            tvOverView.setText(movie.getOverview());

            String imageURL;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.getBackDropPath();
            } else {
                imageURL = movie.getPosterPath();

            }
            //if phone is in landscaper we want the backdrop image


            Glide.with(context).load(imageURL).into(tvPoster);


            // 1. Register click listener on the whole container
            // 2. Navigate to a new activity
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("MOVIE", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });
        }
    }
}
