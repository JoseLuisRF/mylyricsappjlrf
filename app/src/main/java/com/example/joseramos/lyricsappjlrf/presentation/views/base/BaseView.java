package com.example.joseramos.lyricsappjlrf.presentation.views.base;


public interface BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param throwable
     */
    void showError(Throwable throwable);
}
