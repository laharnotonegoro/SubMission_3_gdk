package com.example.submission1.main.konten;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission1.BuildConfig;
import com.example.submission1.details.DetailsActivity;
import com.example.submission1.R;
import com.example.submission1.model.movie.MovieResultsItem;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoloder> {
    private Context context;
    private List<MovieResultsItem> items;

    public MovieAdapter(Context context, List<MovieResultsItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MovieViewHoloder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieViewHoloder(LayoutInflater.from(context).inflate(R.layout.list_movie,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHoloder movieViewHoloder, int i) {
        movieViewHoloder.bindItem(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MovieViewHoloder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView judul;

        public MovieViewHoloder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.iv_poster_list);
            judul = itemView.findViewById(R.id.tv_judul_list);
        }

        void bindItem(final MovieResultsItem info) {
            Glide.with(context).load(BuildConfig.POSTER_URL + info.getPosterPath()).into(poster);
            judul.setText(info.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailsActivity.class);
                    // 0 for movies
                    i.putExtra("position", 0);
                    i.putExtra("data", info);
                    context.startActivity(i);
                }
            });
        }
    }
}