package com.example.joseramos.lyricsappjlrf.presentation.views;


import com.example.joseramos.lyricsappjlrf.presentation.models.TrackUIModel;
import com.example.joseramos.lyricsappjlrf.presentation.views.base.BaseView;

import java.util.List;

public interface TopSongsView extends BaseView {

    void onSuccessSongsLoaded(List<? extends TrackUIModel> trackModels);
}
