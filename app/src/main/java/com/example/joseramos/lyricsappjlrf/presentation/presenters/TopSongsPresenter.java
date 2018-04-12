package com.example.joseramos.lyricsappjlrf.presentation.presenters;


import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetTopSongs;
import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseSaveTopSongs;
import com.example.joseramos.lyricsappjlrf.presentation.presenters.base.BasePresenter;
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView;

import javax.inject.Inject;

@Deprecated
public class TopSongsPresenter implements BasePresenter<TopSongsView> {

    private final UseCaseGetTopSongs useCaseGetTopSongs;
    private final UseCaseSaveTopSongs useCaseSaveTopSongs;
    private TopSongsView view;

    @Inject
    public TopSongsPresenter(UseCaseGetTopSongs useCaseGetTopSongs,
                             UseCaseSaveTopSongs useCaseSaveTopSongs) {
        this.useCaseGetTopSongs = useCaseGetTopSongs;
        this.useCaseSaveTopSongs = useCaseSaveTopSongs;
    }

    @Override
    public void destroy() {
        useCaseGetTopSongs.dispose();
        useCaseSaveTopSongs.dispose();
        view = null;
    }

    @Override
    public void setView(TopSongsView view) {
        this.view = view;
    }

    public void loadTopSongs() {
//        useCaseGetTopSongs.execute(new DisposableSubscriber<List<? extends TrackModel>>() {
//            @Override
//            public void onNext(List<? extends TrackModel> trackModels) {
//                view.onSuccessSongsLoaded(trackModels);
//                useCaseSaveTopSongs.execute(new DisposableSubscriber<Void>() {
//                    @Override
//                    public void onNext(Void aVoid) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                }, trackModels);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                view.showError(t);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }, null);
    }
}
