package com.example.joseramos.lyricsappjlrf.data.database.entity

import android.arch.persistence.room.*
import android.support.annotation.NonNull

const val TABLE_NAME_SONG_LYRICS = "song_lyrics"
const val COLUMN_LYRICS_ID = "lyrics_id"
const val COLUMN_RESTRICTED = "restricted"
const val COLUMN_INSTRUMENTAL = "instrumental"
const val COLUMN_LYRICS_BODY = "lyrics_body"
const val COLUMN_LYRICS_LANGUAGE = "lyrics_language"
const val COLUMN_SCRIPT_TRACKING_URL = "script_tracking_url"
const val COLUMN_PIXEL_TRACKING_URL = "pixel_tracking_url"
const val COLUMN_HTML_TRACKING_URL = "html_tracking_url"
const val COLUMN_LYRICS_COPYRIGHT = "lyrics_copyright"
const val COLUMN_UPDATED_TIME = "updated_time"

@Entity(tableName =  TABLE_NAME_SONG_LYRICS,
        foreignKeys = arrayOf(ForeignKey(
                entity = TopSongsEntity::class,
                parentColumns = arrayOf(COLUMN_TRACK_ID),
                childColumns = arrayOf(COLUMN_TRACK_ID),
                onDelete = ForeignKey.CASCADE
        )),
        indices = arrayOf(Index(COLUMN_TRACK_ID))
)
data class SongLyricsEntity constructor(

        @NonNull
        @PrimaryKey
        @ColumnInfo(name = COLUMN_LYRICS_ID)
        val lyricsId: Int,

        @ColumnInfo(name = COLUMN_TRACK_ID)
        val trackId: Int,

        @ColumnInfo(name = COLUMN_RESTRICTED)
        val restricted: Int,

        @ColumnInfo(name = COLUMN_INSTRUMENTAL)
        val instrumental: Int,

        @ColumnInfo(name = COLUMN_LYRICS_BODY)
        val lyricsBody: String,

        @ColumnInfo(name = COLUMN_LYRICS_LANGUAGE)
        val lyricsLanguage: String,

        @ColumnInfo(name = COLUMN_SCRIPT_TRACKING_URL)
        val scriptTrackingUrl: String,

        @ColumnInfo(name = COLUMN_PIXEL_TRACKING_URL)
        val pixelTrackingUrl: String,

        @ColumnInfo(name = COLUMN_HTML_TRACKING_URL)
        val htmlTrackingUrl: String,

        @ColumnInfo(name = COLUMN_LYRICS_COPYRIGHT)
        val lyricsCopyRight: String,

        @ColumnInfo(name = COLUMN_UPDATED_TIME)
        val updatedTime: String
)