package com.example.joseramos.lyricsappjlrf.presentation.presenters;


import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetTopSongs;
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel;
import com.example.joseramos.lyricsappjlrf.presentation.presenters.base.BasePresenter;
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

public class TopSongsPresenter implements BasePresenter<TopSongsView> {

    private final UseCaseGetTopSongs useCaseGetTopSongs;
    private TopSongsView view;

    @Inject
    public TopSongsPresenter(UseCaseGetTopSongs useCaseGetTopSongs) {
        this.useCaseGetTopSongs = useCaseGetTopSongs;
    }

    @Override
    public void destroy() {
        useCaseGetTopSongs.dispose();
        view = null;
    }

    @Override
    public void setView(TopSongsView view) {
        this.view = view;
    }

    public void loadTopSongs() {
        useCaseGetTopSongs.execute(new DisposableSubscriber<List<? extends TrackModel>>() {
            @Override
            public void onNext(List<? extends TrackModel> trackModels) {
                view.onSuccessSongsLoaded(trackModels);
            }

            @Override
            public void onError(Throwable t) {
                view.showError(t);
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }
}
