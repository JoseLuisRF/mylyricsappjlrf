package com.example.joseramos.lyricsappjlrf.presentation.navigation;

import android.content.Context;

import javax.inject.Inject;

public class NavigatorImpl implements Navigator {

    private NavigatorManager mNavigatorManager;
    private Context mContext;

    @Inject
    public NavigatorImpl(NavigatorManager navigatorManager, Context context) {
        this.mNavigatorManager = navigatorManager;
        this.mContext = context;
    }

    @Override
    public void navigateToMainActivity() {

    }
}