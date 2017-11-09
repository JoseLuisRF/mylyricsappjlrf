package com.example.joseramos.lyricsappjlrf.domain.interactor

import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.BaseUseCaseFlowable
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseSaveTopSongs @Inject constructor(threadExecutor: ThreadExecutor?,
                                              postExecutionThread: PostExecutionThread?,
                                              private val repository: MusicRepository) : BaseUseCaseFlowable<Void, List<TrackModel>>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(models: List<TrackModel>): Flowable<Void> {
        return repository.saveTopSong(models)
    }
}