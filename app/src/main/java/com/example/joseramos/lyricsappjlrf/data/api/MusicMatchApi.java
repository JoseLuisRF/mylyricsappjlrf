package com.example.joseramos.lyricsappjlrf.data.api;

import android.arch.lifecycle.LiveData;

import com.example.joseramos.lyricsappjlrf.data.api.model.BaseResponse;
import com.example.joseramos.lyricsappjlrf.data.api.model.GetTopSongsResponse;
import com.example.joseramos.lyricsappjlrf.data.api.model.LyricsWrapperResponse;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicMatchApi {

    @GET("chart.tracks.get")
    Call<BaseResponse<GetTopSongsResponse>> getTopSongs(@Query("page") int page,
                                                                  @Query("page_size") int pageSize,
                                                                  @Query("country") String country,
                                                                  @Query("has_lyrics") int hasLyrics);


    @GET("matcher.lyrics.get")
    Flowable<Response<BaseResponse<LyricsWrapperResponse>>> getLyrics(@Query("q_track") String trackName,
                                                                      @Query("q_artist") String artistName);
}
