package com.example.joseramos.lyricsappjlrf.presentation.activities.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication;
import com.example.joseramos.lyricsappjlrf.di.ComponentFactory;
import com.example.joseramos.lyricsappjlrf.di.components.ActivityComponent;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorManager;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Inject
    protected NavigatorManager navigatorManager;

    @Inject
    protected Navigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = ComponentFactory.createActivityComponent(
                MyLyricsApplication.getComponent(this),
                getSupportFragmentManager(),
                this);
        activityComponent.inject(this);
    }

    @Override
    public void onBackPressed() {
        if (navigatorManager.getCurrentFragment() != null && navigatorManager.getCurrentFragment() instanceof OnBackPressListener) {
            ((OnBackPressListener) navigatorManager.getCurrentFragment()).onBackPressed();
        } else {
            super.onBackPressed();
            navigatorManager.popStack();
        }
    }

}
