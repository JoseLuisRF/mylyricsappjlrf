package com.example.joseramos.lyricsappjlrf.di.components;


import com.example.joseramos.lyricsappjlrf.di.modules.NavigationModule;
import com.example.joseramos.lyricsappjlrf.di.scopes.ActivityScope;
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator;
import com.example.joseramos.lyricsappjlrf.presentation.navigation.NavigatorManager;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {NavigationModule.class})
public interface ActivityComponent {

    NavigatorManager navigatorManager();

    Navigator navigator();

    void inject(BaseActivity baseActivity);
}
