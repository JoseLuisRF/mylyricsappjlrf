package com.example.joseramos.lyricsappjlrf.presentation.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.ActivityMainBinding
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import com.example.joseramos.lyricsappjlrf.presentation.adapters.ABOUT
import com.example.joseramos.lyricsappjlrf.presentation.adapters.HOME
import com.example.joseramos.lyricsappjlrf.presentation.adapters.HomePagerAdapter
import dagger.android.AndroidInjection

class MainActivity : ViewPager.OnPageChangeListener, BaseActivity() {

    var binding: ActivityMainBinding? = null
    var pagerAdapter: HomePagerAdapter? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                binding?.pager?.currentItem = HOME
            }
            R.id.navigation_about -> {
                binding?.pager?.currentItem = ABOUT
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        pagerAdapter = HomePagerAdapter(supportFragmentManager)
        binding?.navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        binding?.pager?.adapter = pagerAdapter
        binding?.pager?.currentItem = HOME
        binding?.pager?.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {

        when (position) {
            HOME -> {
                binding?.navigation?.selectedItemId = R.id.navigation_home
            }
            ABOUT -> {
                binding?.navigation?.selectedItemId = R.id.navigation_about
            }
        }
    }

}
