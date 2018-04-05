package com.example.joseramos.lyricsappjlrf.test.presenter

import com.example.joseramos.lyricsappjlrf.domain.interactor.UseCaseGetLyrics
import com.example.joseramos.lyricsappjlrf.presentation.presenters.LyricsPresenter
import com.example.joseramos.lyricsappjlrf.presentation.utils.DeviceUtils
import com.example.joseramos.lyricsappjlrf.presentation.views.LyricsView
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.verify

class LyricsPresenterTest {


    private val useCaseGetLyrics = Mockito.mock(UseCaseGetLyrics::class.java)
    private val deviceUtils = Mockito.mock(DeviceUtils::class.java)
    private val view = Mockito.mock(LyricsView::class.java)
    private var presenter: LyricsPresenter? = null

    @Before
    fun setup() {
        presenter = LyricsPresenter(useCaseGetLyrics, deviceUtils)
    }

    @Test
    fun loadLyricsTest() {
        //values
        val trackId = 1

        //when
        presenter?.loadLyrics(trackId)

        //verify
        verify(useCaseGetLyrics).execute(any(), ArgumentMatchers.anyInt())
    }


}