package com.example.joseramos.lyricsappjlrf.di.modules

import android.content.Context
import android.support.v4.app.FragmentManager
import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorImpl
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorManager
import dagger.Module
import dagger.Provides

/**
 * Creates a Navigation Module
 *
 **/
@Module
class NavigationModule {

    @ActivityScope
    @Provides
    internal fun providesNavigatorManager(fragmentManager: FragmentManager): NavigatorManager {
        return NavigatorManager(fragmentManager)
    }

    @ActivityScope
    @Provides
    internal fun providesNavigator(navigatorManager: NavigatorManager, context: Context): Navigator {
        return NavigatorImpl(navigatorManager, context)
    }

}
