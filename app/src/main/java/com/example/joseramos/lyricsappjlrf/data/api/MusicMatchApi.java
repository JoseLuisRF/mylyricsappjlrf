package com.example.joseramos.lyricsappjlrf.data.api;

import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse;
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicMatchApi {

    @GET("chart.tracks.get")
    Flowable<Response<BaseResponse<GetTopSongsResponse>>> getTopSongs(@Query("page") int page,
                                                                            @Query("page_size") int pageSize,
                                                                            @Query("country") String country,
                                                                            @Query("has_lyrics") int hasLyrics);
}
