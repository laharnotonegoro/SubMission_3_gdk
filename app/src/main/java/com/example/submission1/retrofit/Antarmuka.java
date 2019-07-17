package com.example.submission1.retrofit;

import com.example.submission1.model.movie.MovieModel;
import com.example.submission1.model.tv.TvModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Antarmuka {
    @GET("discover/movie")
    Call<MovieModel> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);

    @GET("discover/tv")
    Call<TvModel> getTvShow(@Query("api_key") String api_Key, @Query("sort_by") String sort_By);
}
