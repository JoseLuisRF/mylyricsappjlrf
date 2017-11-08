package com.example.joseramos.lyricsappjlrf.presentation.adapters


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.joseramos.lyricsappjlrf.presentation.fragments.AboutFragment
import com.example.joseramos.lyricsappjlrf.presentation.fragments.TopSongsFragment

const val HOME = 0
const val ABOUT = 1
const val COUNT = 2

class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            HOME -> return TopSongsFragment.newInstance()
            else -> {return AboutFragment.newInstance()}
        }
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            HOME -> return "Home"
            else -> {return "About"}
        }
    }
}
