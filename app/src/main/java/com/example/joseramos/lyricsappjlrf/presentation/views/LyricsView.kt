package com.example.joseramos.lyricsappjlrf.presentation.views

import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.presentation.views.base.BaseView

interface LyricsView : BaseView {

    fun onLoadLyricsSuccessful(model: LyricsModel)
}