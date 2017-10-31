package com.example.joseramos.lyricsappjlrf.data.repository.mappers

import com.example.joseramos.lyricsappjlrf.data.api.model.TrackResponse
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import javax.inject.Inject


class MusicDataMapper @Inject constructor() {
    fun convert(response: TrackResponse) : TrackModel {
        return TrackModel(response.artistName, response.trackName, response.albumName, response.albumCoverart100x100)
    }

}