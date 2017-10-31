package com.example.joseramos.lyricsappjlrf.data.api.model

import com.google.gson.annotations.SerializedName

data class GetTopSongsResponse constructor(
        @SerializedName("track_list")
        val trackList: List<TrackWrapper>)

data class TrackWrapper constructor(
        @SerializedName("track")
        val track: TrackResponse)

data class TrackResponse constructor(
        @SerializedName("track_id")
        val trackId: Int,
        @SerializedName("track_name")
        val trackName: String,
        @SerializedName("track_rating")
        val trackRating: Int,
        @SerializedName("track_length")
        val trackLength: Int,
        @SerializedName("commontrack_id")
        val commonTrackId: Int,
        @SerializedName("instrumental")
        val instrumental: Int,
        @SerializedName("explicit")
        val explicit: Int,
        @SerializedName("has_lyrics")
        val hasLyrics: Int,
        @SerializedName("has_lyrics_crowd")
        val hasLyricsCrowd: Int,
        @SerializedName("has_subtitles")
        val hasSubtitles: Int,
        @SerializedName("has_richsync")
        val hasRichsync: Int,
        @SerializedName("num_favourite")
        val numFavourite: Int,
        @SerializedName("lyrics_id")
        val lyricsId: Int,
        @SerializedName("subtitle_id")
        val subtitleId: Int,
        @SerializedName("album_id")
        val albumId: Int,
        @SerializedName("artist_id")
        val artistId: Int,
        @SerializedName("restricted")
        val restricted: Int,
        @SerializedName("album_name")
        val albumName: String,
        @SerializedName("artist_mbid")
        val artistMbid: String,
        @SerializedName("artist_name")
        val artistName: String,
        @SerializedName("album_coverart_100x100")
        val albumCoverart100x100: String,
        @SerializedName("album_coverart_350x350")
        val albumCoverart350x350: String,
        @SerializedName("album_coverart_500x500")
        val albumCoverart500x500: String,
        @SerializedName("album_coverart_800x800")
        val albumCoverart800x800: String,
        @SerializedName("track_share_url")
        val trackShareUrl: String,
        @SerializedName("track_edit_url")
        val trackEditUrl: String,
        @SerializedName("commontrack_vanity_id")
        val commonTrackVanityId: String,
        @SerializedName("first_release_date")
        val firstReleaseDate: String,
        @SerializedName("updated_time")
        val updatedTime: String
)