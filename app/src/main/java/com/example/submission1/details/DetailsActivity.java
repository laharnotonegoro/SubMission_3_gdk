package com.example.submission1.details;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.submission1.BuildConfig;
import com.example.submission1.R;
import com.example.submission1.model.movie.MovieResultsItem;
import com.example.submission1.model.tv.TvResultsItem;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView ivPoster = findViewById(R.id.iv_poster_list_detail);
        TextView tvjudul = findViewById(R.id.tv_judul_list_detail);
        TextView deskripsi = findViewById(R.id.tv_deskripsi_detail);

        if (getIntent().getExtras() != null) {
            int position = getIntent().getIntExtra("position", 0);

            switch (position) {
                case 0:
                    MovieResultsItem resultMovie = getIntent().getParcelableExtra("data");

                    Glide.with(this).load(BuildConfig.POSTER_URL + resultMovie.getPosterPath()).into(ivPoster);
                    tvjudul.setText(resultMovie.getTitle());
                    deskripsi.setText(resultMovie.getOverview());
                    break;
                case 1:
                    TvResultsItem resultTv = getIntent().getParcelableExtra("data");

                    Glide.with(this).load(BuildConfig.POSTER_URL + resultTv.getPosterPath()).into(ivPoster);
                    tvjudul.setText(resultTv.getName());
                    deskripsi.setText(resultTv.getOverview());
                    break;
            }
        }
    }
}