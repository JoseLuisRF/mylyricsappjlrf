package com.example.joseramos.lyricsappjlrf.data.repository

import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicCloudDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.datasource.MusicDiskDataSource
import com.example.joseramos.lyricsappjlrf.data.repository.mappers.MusicDataMapper
import com.example.joseramos.lyricsappjlrf.domain.models.LyricsModel
import com.example.joseramos.lyricsappjlrf.domain.repository.MusicRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import retrofit2.Response

class MusicRepositoryImplTest {

    private val musicCloudDataSource = mock(MusicCloudDataSource::class.java)
    private val musicDiskDataSource = mock(MusicDiskDataSource::class.java)
    private val dataMapper = mock(MusicDataMapper::class.java)
    private lateinit var repository: MusicRepository

    @Before
    fun setUp() {
        repository = MusicRepositoryImpl(musicCloudDataSource, musicDiskDataSource, dataMapper)
    }

    @Test
    fun fetchTopSongs() {

        val mockData = mock(Response::class.java) as Response<BaseResponse<GetTopSongsResponse>>
        given(musicCloudDataSource.getTopSongs(
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt()
        )).willReturn(Flowable.just(mockData))


        //when
        repository.fetchTopSongs()

    }

    @Test
    fun saveTopSong() {
    }

    @Test
    fun getTopSongs() {
    }

    @Test
    fun fetchLyrics() {
        val mockData = mock(Response::class.java) as Response<BaseResponse<LyricsWrapperResponse>>
        given(musicCloudDataSource.getSongLyrics(anyString(), anyString())).willReturn(Flowable.just(mockData))

        repository.fetchLyrics(anyString(), anyString(),anyInt())
                .test()
                .assertNoErrors()
    }

    @Test
    fun saveLyrics() {
        val model = mock(LyricsModel::class.java)
        given(musicDiskDataSource.insertLyrics(dataMapper.convert(model))).willReturn(Flowable.just(anyInt()))

        repository.saveLyrics(model)

        verify(musicDiskDataSource,times(1)).insertLyrics(dataMapper.convert(model))
    }

    @Test
    fun getLyrics() {
    }

    @Test
    fun getSong() {
    }
}