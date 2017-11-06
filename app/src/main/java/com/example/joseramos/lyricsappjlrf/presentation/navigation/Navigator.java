package com.example.joseramos.lyricsappjlrf.presentation.navigation;

public interface Navigator {

    void navigateToMainActivity();

    void navigateToSecondLevelActivity(int trackId);

    void navigateToLyricsScreen(int trackId);

}
