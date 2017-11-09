package com.example.joseramos.lyricsappjlrf.presentation.fragments.base


import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication
import com.example.joseramos.lyricsappjlrf.di.ComponentFactory
import com.example.joseramos.lyricsappjlrf.di.components.FragmentComponent
import com.example.joseramos.lyricsappjlrf.presentation.activities.base.BaseActivity
import com.example.joseramos.lyricsappjlrf.presentation.navigation.Navigator
import com.example.joseramos.lyricsappjlrf.presentation.utils.PermissionsManager

import javax.inject.Inject

const val WRITE_STORAGE_PERMISSION_INDEX = 0

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var permissionManager: PermissionsManager

    var fragmentComponent: FragmentComponent? = null
    protected lateinit var navigator: Navigator

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent = ComponentFactory.createFragmentComponent(MyLyricsApplication.getComponent(this.activity.application))
        if (activity is BaseActivity) {
            navigator = (activity as BaseActivity).navigator
        }

        fragmentComponent!!.inject(this)
    }

    override fun onStart() {
        super.onStart()
        requestStoragePermissionsIfNotGranted()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionsManager.MEDIA_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0
                    && grantResults[WRITE_STORAGE_PERMISSION_INDEX] != PackageManager.PERMISSION_GRANTED ) {
                permissionManager.showMediaPermissionsDialog(this, PermissionsManager.MEDIA_PERMISSION_REQUEST_CODE)
            }
        }
    }

    protected fun requestStoragePermissionsIfNotGranted() {
        if (!permissionManager.isStoragePermissionGranted()) {
            permissionManager.showMediaPermissionsDialog(this, PermissionsManager.MEDIA_PERMISSION_REQUEST_CODE)
        }
    }

}
