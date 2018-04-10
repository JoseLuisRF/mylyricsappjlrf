package com.example.joseramos.lyricsappjlrf.test.presenter

import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetTopSongs
import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseSaveTopSongs
import com.example.joseramos.lyricsappjlrf.presentation.presenters.TopSongsPresenter
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class TopSongsPresenterTest {

    private val useCaseGetTopSongs = mock(UseCaseGetTopSongs::class.java)
    private val useCaseSaveTopSongs = mock(UseCaseSaveTopSongs::class.java)
    private val view = mock(TopSongsView::class.java)
    private var presenter: TopSongsPresenter? = null


    @Before
    fun setup() {
        presenter = TopSongsPresenter(useCaseGetTopSongs, useCaseSaveTopSongs)
    }

    @Test
    fun loadTopSongsTest() {
        // when
        presenter?.loadTopSongs()

        // verify
        verify(useCaseGetTopSongs).execute(any(), ArgumentMatchers.any())
    }
}