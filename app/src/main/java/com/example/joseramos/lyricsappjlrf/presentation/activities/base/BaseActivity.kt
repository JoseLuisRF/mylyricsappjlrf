package com.example.joseramos.lyricsappjlrf.presentation.activities.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        //FIXME: Remove when AppInjector is used to inject any android component ( For Example Activities and Fragments).
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (navigator.getNavigatorManager().currentFragment != null && navigator.getNavigatorManager().currentFragment is OnBackPressListener) {
            (navigator.getNavigatorManager().currentFragment as OnBackPressListener).onBackPressed()
        } else {
            super.onBackPressed()
            navigator.getNavigatorManager().popStack()
        }
    }
}
