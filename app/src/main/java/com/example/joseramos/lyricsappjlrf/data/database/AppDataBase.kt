package com.example.joseramos.lyricsappjlrf.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.joseramos.lyricsappjlrf.data.database.dao.LyricsDao

import com.example.joseramos.lyricsappjlrf.data.database.dao.TopSongsDao
import com.example.joseramos.lyricsappjlrf.data.database.entity.SongLyricsEntity
import com.example.joseramos.lyricsappjlrf.data.database.entity.TopSongsEntity


const val DATABASE_NAME = "my_lyrics_app.db"

@Database(entities = arrayOf(
        TopSongsEntity::class,
        SongLyricsEntity::class),
        version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun topSongsDao(): TopSongsDao
    abstract fun lyricsDao(): LyricsDao
}
