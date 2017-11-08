package com.example.joseramos.lyricsappjlrf.domain.interactor

import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.BaseUseCaseFlowable
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetLyrics @Inject constructor(threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread,
                                           private val musicRepository: MusicRepository) : BaseUseCaseFlowable<LyricsModel, Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(trackId: Int): Flowable<LyricsModel> {
        return musicRepository.getSong(trackId)
                . flatMap {model ->musicRepository.fetchLyrics(model.trackName, model.artistName, model.trackId)}
                .flatMap { lyricsModel -> musicRepository.saveLyrics(lyricsModel) }
                .flatMap { lyricsId -> musicRepository.getLyrics(lyricsId) }
    }
}