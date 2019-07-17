package com.example.submission1.main.konten;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission1.R;
import com.example.submission1.main.FilmViewModel;
import com.example.submission1.model.movie.MovieModel;
import com.example.submission1.model.movie.MovieResultsItem;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmFragment extends Fragment {
    private FilmViewModel viewModel;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public FilmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_film);
        progressBar = view.findViewById(R.id.pb_film);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ambilData();
    }

    private void ambilData() {
        viewModel.movieList.observe(this, new Observer<List<MovieResultsItem>>() {
            @Override
            public void onChanged(List<MovieResultsItem> movieResultsItems) {
                progressBar.setVisibility(View.GONE);
                MovieAdapter adapter = new MovieAdapter(requireContext(), movieResultsItems);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
    }
}