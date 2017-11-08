package com.example.joseramos.lyricsappjlrf.domain.interactor

import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.BaseUseCaseFlowable
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseGetLyrics @Inject constructor(threadExecutor: ThreadExecutor,
                                           postExecutionThread: PostExecutionThread,
                                           private val musicRepository: MusicRepository,
                                           private val deviceUtils: DeviceUtils) : BaseUseCaseFlowable<LyricsModel, Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(trackId: Int): Flowable<LyricsModel> {

        if( deviceUtils.isNetworkAvailable) {
            return musicRepository.getSong(trackId)
                    .flatMap { model -> musicRepository.fetchLyrics(model.trackName, model.artistName, model.trackId) }
                    .flatMap { lyricsModel -> musicRepository.saveLyrics(lyricsModel) }
                    .flatMap {  musicRepository.getLyrics(trackId) }
        }

        return musicRepository.getLyrics(trackId)
    }
}