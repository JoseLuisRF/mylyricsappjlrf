package com.example.joseramos.lyricsappjlrf.data.repository.datasource

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity
import io.reactivex.Flowable

interface MusicDiskDataSource {

    fun insertTopSong(entity: TopSongsEntity) : Flowable<Long>

    fun insertTopSong(entity: List<TopSongsEntity>) : Flowable<List<Long>>

    fun insertTopSong2(entity: List<TopSongsEntity>) : List<Long>

    fun selectTopSongs() : Flowable<List<TopSongsEntity>>

    fun insertLyrics(entity: SongLyricsEntity): Flowable<Int>

    fun insertLyrics2(entity: SongLyricsEntity): Long

    fun selectLyrics(trackId: Int): Flowable<SongLyricsEntity>

    fun selectLyrics2(trackId: Long): SongLyricsEntity

    fun selectSong(trackId: Int): Flowable<TopSongsEntity>

    fun selectSong2(trackId: Int): TopSongsEntity
}