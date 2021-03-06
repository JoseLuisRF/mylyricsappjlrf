package com.example.joseramos.lyricsappjlrf.domain.interactor

import com.example.joseramos.lyricsappjlrf.domain.executor.PostExecutionThread
import com.example.joseramos.lyricsappjlrf.domain.executor.ThreadExecutor
import com.example.joseramos.lyricsappjlrf.domain.interactor.base.BaseUseCaseFlowable
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import io.reactivex.Flowable
import javax.inject.Inject


class UseCaseGetTopSongs @Inject
constructor(threadExecutor: ThreadExecutor,
            postExecutionThread: PostExecutionThread,
            private val musicRepository: MusicRepository,
            private val deviceUtils: DeviceUtils) : BaseUseCaseFlowable<List<TrackModel>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(`object`: Void?): Flowable<List<TrackModel>> {
        if (deviceUtils.isNetworkAvailable) {
            return musicRepository.fetchTopSongs()
        }
        return musicRepository.getTopSongs();

    }
}