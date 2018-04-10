package com.example.joseramos.lyricsappjlrf.test.presenter

import com.example.joseramos.lyricsappjlrf.data.api.MusicMatchApi
import com.example.joseramos.lyricsappjlrf.data.api.datasource.MusicCloudDataSourceImpl
import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.*
import retrofit2.Response
import org.mockito.Mockito.`when` as whenever


class MusicCloudDataSourceImplTest {

    private val api = Mockito.mock(MusicMatchApi::class.java)
    private lateinit var dataSource: MusicCloudDataSource

    @Before
    fun setUp() {

        dataSource = MusicCloudDataSourceImpl(api)
    }

    @Test
    @Throws(Exception::class)
    fun getTopSongs() {
        val response = mock(Response::class.java) as Response<BaseResponse<GetTopSongsResponse>>
        given(api.getTopSongs(
                anyInt(),
                anyInt(),
                anyString(),
                anyInt()))
                .willReturn(Flowable.just(response))

        //when
        dataSource.getTopSongs(
                anyInt(),
                anyInt(),
                anyString(),
                anyInt())

        verify(api, times(1))
                .getTopSongs(anyInt(), anyInt(), anyString(), anyInt())

    }

    @Test
    fun getSongLyrics() {
        val mockData = mock(Response::class.java) as Response<BaseResponse<LyricsWrapperResponse>>

        given(api.getLyrics(
                anyString(),
                anyString()
        )).willReturn(Flowable.just(mockData))

        dataSource.getSongLyrics(anyString(), anyString())

        verify(api, times(1))
                .getLyrics(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString())
    }
}