package com.tip.peliculas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tip.peliculas.R;
import com.tip.peliculas.model.Movie;
import com.tip.peliculas.utils.OnItemClickListener;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
private List<Movie> movies;
private int rowLayout;
private Context context;

private OnItemClickListener onItemClickListener;

public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        }
//A view holder inner class where we get reference to the views in the layout using their ID
public static class MovieViewHolder extends RecyclerView.ViewHolder {
    LinearLayout moviesLayout;
    TextView movieTitle;
    TextView data;
    ImageView movieImage;
    public MovieViewHolder(View v) {
        super(v);
        moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
        movieImage = (ImageView) v.findViewById(R.id.movie_image);
        movieTitle = (TextView) v.findViewById(R.id.title);
        data = (TextView) v.findViewById(R.id.date);
    }
}
    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String image_url = movies.get(position).getPoster();
        //https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getYear());

        //enlazamos el clicklistener al item
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(movies.get(position));
            }
        };
        holder.movieTitle.setOnClickListener(listener);
        holder.movieImage.setOnClickListener(listener);
        holder.data.setOnClickListener(listener);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //Declaramos el get y set para el clicklistener
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}