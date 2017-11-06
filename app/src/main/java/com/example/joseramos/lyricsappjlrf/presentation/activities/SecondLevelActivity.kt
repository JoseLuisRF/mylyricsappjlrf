package com.example.joseramos.lyricsappjlrf.presentation.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.ActivitySecondLevelBinding
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import com.example.joseramos.lyricsappjlrf.presentation.fragments.BUNDLE_TRACK_ID

class SecondLevelActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySecondLevelBinding>(this, R.layout.activity_second_level )
        navigator.navigateToLyricsScreen(intent.getIntExtra(BUNDLE_TRACK_ID, 0))
    }
}