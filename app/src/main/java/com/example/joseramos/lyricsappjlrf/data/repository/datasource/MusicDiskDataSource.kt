package com.example.joseramos.lyricsappjlrf.data.repository.datasource

import android.arch.lifecycle.LiveData
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity

interface MusicDiskDataSource {


    fun insertTopSong2(entity: List<TopSongsEntity>): List<Long>

    fun insertLyrics2(entity: SongLyricsEntity): Long

    fun selectLyrics2(trackId: Long): LiveData<SongLyricsEntity>

    fun selectSong2(trackId: Int): LiveData<TopSongsEntity>
}