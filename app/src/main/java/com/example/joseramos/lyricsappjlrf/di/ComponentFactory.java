package com.example.joseramos.lyricsappjlrf.di;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication;
import com.example.joseramos.lyricsappjlrf.di.components.ActivityComponent;
import com.example.joseramos.lyricsappjlrf.di.components.ApplicationComponent;
import com.example.joseramos.lyricsappjlrf.di.components.DaggerActivityComponent;
import com.example.joseramos.lyricsappjlrf.di.components.DaggerApplicationComponent;
import com.example.joseramos.lyricsappjlrf.di.components.DaggerFragmentComponent;
import com.example.joseramos.lyricsappjlrf.di.components.FragmentComponent;
import com.example.joseramos.lyricsappjlrf.di.modules.ApplicationModule;
import com.example.joseramos.lyricsappjlrf.di.modules.NavigationModule;

public class ComponentFactory {

    /**
     * This method returns a Dagger Application Component instance {@link ApplicationComponent}
     * @param application {@link MyLyricsApplication}
     *
     * @return {@link ApplicationComponent} Dagger Component
     */
    public static ApplicationComponent createApplicationComponent(MyLyricsApplication application) {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .build();
    }

    /**
     * This method returns a Dagger Application Component instance {@link ActivityComponent}
     *
     * @param applicationComponent
     * @param fragmentManager
     * @param context
     * @return {@link ActivityComponent} Dagger Component
     */
    public static ActivityComponent createActivityComponent(ApplicationComponent applicationComponent,
                                                            FragmentManager fragmentManager,
                                                            Context context) {
        return DaggerActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .navigationModule(new NavigationModule(fragmentManager, context))
                .build();
    }

    /**
     *
     * @param component
     * @return
     */
    public static FragmentComponent createFragmentComponent(ApplicationComponent component) {
        return DaggerFragmentComponent.builder()
                .applicationComponent(component)
                .build();
    }
}
