package com.example.joseramos.lyricsappjlrf.di.modules;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorImpl;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorManager;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {
    private FragmentManager mFragmentManager;
    private Context mContext;

    /**
     * Creates a Navigation Module
     *
     * @param fragmentManager FragmentManager
     * @param context         Context
     */
    public NavigationModule(FragmentManager fragmentManager, Context context) {
        this.mFragmentManager = fragmentManager;
        this.mContext = context;
    }

    @ActivityScope
    @Provides
    NavigatorManager providesNavigatorManager() {
        return new NavigatorManager(mFragmentManager);
    }

    @ActivityScope
    @Provides
    Navigator providesNavigator(NavigatorManager navigatorManager) {
        return new NavigatorImpl(navigatorManager, mContext);
    }
}
