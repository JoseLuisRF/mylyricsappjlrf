package com.example.joseramos.lyricsappjlrf.presentation.presenters

import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetLyrics
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.presentation.presenters.base.BasePresenter
import com.example.joseramos.lyricsappjlrf.presentation.views.LyricsView
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class LyricsPresenter @Inject constructor(val useCaseGetLyrics: UseCaseGetLyrics) : BasePresenter<LyricsView> {

    private var view: LyricsView? = null

    fun loadLyrics(trackId: Int) {
        useCaseGetLyrics.execute(object : DisposableSubscriber<LyricsModel>() {
            override fun onNext(model: LyricsModel) {
                view?.onLoadLyricsSuccessful(model)
            }

            override fun onError(t: Throwable) {

            }

            override fun onComplete() {

            }
        }, trackId)
    }

    override fun destroy() {
        useCaseGetLyrics.dispose()
        view = null
    }

    override fun setView(view: LyricsView?) {
        this.view = view
    }


}