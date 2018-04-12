package com.example.joseramos.lyricsappjlrf.presentation.navigation

interface Navigator {

    fun navigateToMainActivity()

    fun navigateToSecondLevelActivity(trackId: Int)

    fun navigateToLyricsScreen(trackId: Int)

    fun getNavigatorManager(): NavigatorManager

}
