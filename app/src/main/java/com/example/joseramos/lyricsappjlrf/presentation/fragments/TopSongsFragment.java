package com.example.joseramos.lyricsappjlrf.presentation.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joseramos.lyricsappjlrf.R;
import com.example.joseramos.lyricsappjlrf.databinding.FragmentTopSongsBinding;
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel;
import com.example.joseramos.lyricsappjlrf.presentation.adapters.TracksAdapter;
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment;
import com.example.joseramos.lyricsappjlrf.presentation.presenters.TopSongsPresenter;
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView;

import java.util.List;

import javax.inject.Inject;

public class TopSongsFragment extends BaseFragment implements TopSongsView {

    public static TopSongsFragment newInstance() {
        return new TopSongsFragment();
    }

    @Inject
    TopSongsPresenter presenter;

    private TracksAdapter adapter;
    private FragmentTopSongsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_songs, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentComponent().inject(this);
        presenter.setView(this);
        presenter.loadTopSongs();
        binding.rvTopSongs.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvTopSongs.setAdapter(adapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccessSongsLoaded(List<? extends TrackModel> trackModels) {
        adapter = new TracksAdapter((List<TrackModel>) trackModels);
        adapter.setTrackAdapterListener(trackModelTracksAdapterListener);
        binding.rvTopSongs.setAdapter(adapter);
    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        Snackbar.make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private TracksAdapter.TracksAdapterListener<TrackModel> trackModelTracksAdapterListener = new TracksAdapter.TracksAdapterListener<TrackModel>() {
        @Override
        public void onItemClick(View view, int position, TrackModel item) {
            getNavigator().navigateToSecondLevelActivity(item.getTrackId());
        }
    };
}
