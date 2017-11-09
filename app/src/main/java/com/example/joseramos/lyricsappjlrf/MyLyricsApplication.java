package com.example.joseramos.lyricsappjlrf;


import android.app.Application;
import android.content.Context;

import com.example.joseramos.lyricsappjlrf.di.ComponentFactory;
import com.example.joseramos.lyricsappjlrf.di.components.ApplicationComponent;

public class MyLyricsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = ComponentFactory.createApplicationComponent(this);
    }

    /***********************************************************************************************
     * Public Methods
     ***********************************************************************************************/

    public static ApplicationComponent getComponent(Context context) {
        return ((MyLyricsApplication) context.getApplicationContext()).applicationComponent;
    }
}
