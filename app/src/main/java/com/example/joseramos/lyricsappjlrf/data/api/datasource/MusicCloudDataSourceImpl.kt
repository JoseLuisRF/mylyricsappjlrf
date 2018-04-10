package com.example.joseramos.lyricsappjlrf.data.api.datasource


import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import io.reactivex.Flowable
import retrofit2.Response
import javax.inject.Inject


class MusicCloudDataSourceImpl @Inject constructor(private val api: MusicMatchApi) : MusicCloudDataSource {

    override fun getTopSongs(page:Int,pageSize:Int,country: String,hasLyrics:Int): Flowable<Response<BaseResponse<GetTopSongsResponse>>> {
        return api.getTopSongs(page, pageSize, country, hasLyrics)
    }

    override fun getSongLyrics(trackName: String, artistName: String): Flowable<Response<BaseResponse<LyricsWrapperResponse>>> {
        return api.getLyrics(trackName, artistName)
    }
}
