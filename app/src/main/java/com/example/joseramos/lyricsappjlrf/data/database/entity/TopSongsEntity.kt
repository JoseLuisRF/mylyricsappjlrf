package com.example.joseramos.lyricsappjlrf.data.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

const val TABLE_NAME_TOP_SONGS = "top_songs"
const val COLUMN_TRACK_ID = "track_id"
const val COLUMN_TRACK_NAME = "track_name"
const val COLUMN_ALBUM_NAME = "album_name"
const val COLUMN_ALBUM_ID = "album_id"
const val COLUMN_ARTIST_NAME = "artist_name"
const val COLUMN_ALBUM_URL = "album_url"
const val COLUMN_FAVORITE = "favorite"

@Entity(tableName = TABLE_NAME_TOP_SONGS)
data class TopSongsEntity(

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = COLUMN_TRACK_ID)
        val trackId: Int,

        @ColumnInfo(name = COLUMN_TRACK_NAME)
        val trackName: String,

        @ColumnInfo(name = COLUMN_ALBUM_NAME)
        val albumName: String,

        @ColumnInfo(name = COLUMN_ALBUM_ID)
        val albumId: Int,

        @ColumnInfo(name = COLUMN_ARTIST_NAME)
        val artistName: String,

        @ColumnInfo(name = COLUMN_ALBUM_URL)
        val albumUrl: String,

        @ColumnInfo(name = COLUMN_FAVORITE)
        val favorite: Int
)