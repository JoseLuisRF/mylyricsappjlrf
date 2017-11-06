package com.example.joseramos.lyricsappjlrf.data.api.datasource


import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject


class MusicCloudDataSourceImpl @Inject constructor(val api: MusicMatchApi) : MusicCloudDataSource {

    override fun getTopSongs(country: String): Flowable<Response<BaseResponse<GetTopSongsResponse>>> {
        return api.getTopSongs(1, 5, "mx", 1)
    }

    override fun getSongLyrics(trackName: String, artistName: String): Flowable<Response<BaseResponse<LyricsResponse>>> {
        return api.getLyrics(trackName, artistName)
    }
}
