package com.example.joseramos.lyricsappjlrf.presentation.fragments.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.joseramos.lyricsappjlrf.MyLyricsApplication
import com.example.joseramos.lyricsappjlrf.di.ComponentsFactory
import com.example.joseramos.lyricsappjlrf.di.Injectable
import com.example.joseramos.lyricsappjlrf.di.components.FragmentComponent
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator

abstract class BaseFragment : Fragment(), Injectable {

    protected lateinit var userComponent: FragmentComponent
    protected lateinit var navigator: Navigator

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent = ComponentsFactory.createUserComponent(MyLyricsApplication.getAppComponent(context))

        if (activity is BaseActivity) {
            navigator = (activity as BaseActivity).navigator
        }

    }
}
