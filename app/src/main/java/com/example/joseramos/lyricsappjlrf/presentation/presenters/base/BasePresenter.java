package com.example.joseramos.lyricsappjlrf.presentation.presenters.base;


public interface BasePresenter<T> {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    /**
     * Sets a view to the Presenter
     *
     * @param view
     */
    void setView(T view);
}
