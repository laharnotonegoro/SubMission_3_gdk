package com.example.submission1.main.konten;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission1.R;
import com.example.submission1.main.FilmViewModel;
import com.example.submission1.model.tv.TvResultsItem;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private FilmViewModel viewModel;
    private ProgressBar progressBar;

    public TvShowFragment() {

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
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_tv_show);
        progressBar = view.findViewById(R.id.pb_tv_show);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ambilData();
    }

    private void ambilData() {
        viewModel.tvList.observe(this, new Observer<List<TvResultsItem>>() {
            @Override
            public void onChanged(List<TvResultsItem> tvResultsItems) {
                progressBar.setVisibility(View.GONE);

                TvAdapter adapter = new TvAdapter(requireContext(), tvResultsItems);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();
            }
        });
    }
}