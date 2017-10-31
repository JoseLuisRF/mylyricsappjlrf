package com.example.joseramos.lyricsappjlrf.data.repository.datasource


import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import io.reactivex.Flowable
import retrofit2.Response

interface MusicCloudDataSource {

    fun getTopSongs(country: String): Flowable<Response<BaseResponse<GetTopSongsResponse>>>
}
