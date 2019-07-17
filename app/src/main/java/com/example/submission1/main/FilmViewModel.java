package com.example.submission1.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.submission1.BuildConfig;
import com.example.submission1.model.movie.MovieModel;
import com.example.submission1.model.movie.MovieResultsItem;
import com.example.submission1.model.tv.TvModel;
import com.example.submission1.model.tv.TvResultsItem;
import com.example.submission1.retrofit.KelasRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmViewModel extends AndroidViewModel {
    public MutableLiveData<List<MovieResultsItem>> movieList;
    public MutableLiveData<List<TvResultsItem>> tvList;

    private KelasRetrofit retrofit;

    public FilmViewModel(@NonNull Application application) {
        super(application);
        movieList = new MutableLiveData<>();
        tvList = new MutableLiveData<>();
        retrofit = new KelasRetrofit();

        getMovies();
        getTvShow();
    }

    private void getMovies() {
        Call<MovieModel> response = retrofit.getAntarmuka().getMovies(BuildConfig.API_KEY, "popularity.desc");
        response.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    MovieModel movieModel = response.body();

                    if (movieModel != null) {
                        movieList.setValue(movieModel.getResults());
                        Log.d(MainActivity.TAG, "Result (Repo): " + movieModel);
                    }
                } else {
                    Log.e(MainActivity.TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.e(MainActivity.TAG, "Failure: " + t.getLocalizedMessage());
            }
        });
    }

    private void getTvShow() {
        Call<TvModel> response = retrofit.getAntarmuka().getTvShow(BuildConfig.API_KEY, "popularity.desc");
        response.enqueue(new Callback<TvModel>() {
            @Override
            public void onResponse(Call<TvModel> call, Response<TvModel> response) {
                if (response.isSuccessful()) {
                    TvModel result = response.body();

                    if (result != null) {
                        tvList.postValue(result.getResults());
                        Log.d(MainActivity.TAG, "Result (Repo): " + result);
                    }
                } else {
                    Log.e(MainActivity.TAG, "Failure: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<TvModel> call, Throwable t) {
                Log.e(MainActivity.TAG, "Failure: " + t.getLocalizedMessage());
            }
        });
    }

    public LiveData<List<MovieResultsItem>> getMovieList() {
        return movieList;
    }

    public LiveData<List<TvResultsItem>> getTvList() {
        return tvList;
    }
}