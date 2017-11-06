package com.example.joseramos.lyricsappjlrf.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.joseramos.lyricsappjlrf.presentation.activities.MainActivity;
import com.example.joseramos.lyricsappjlrf.presentation.activities.SecondLevelActivity;
import com.example.joseramos.lyricsappjlrf.presentation.fragments.LyricsFragment;

import javax.inject.Inject;

import static com.example.joseramos.lyricsappjlrf.presentation.fragments.LyricsFragmentKt.BUNDLE_TRACK_ID;

public class NavigatorImpl implements Navigator {

    private NavigatorManager mNavigatorManager;
    private Context mContext;

    @Inject
    public NavigatorImpl(NavigatorManager navigatorManager, Context context) {
        this.mNavigatorManager = navigatorManager;
        this.mContext = context;
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void navigateToSecondLevelActivity(int trackId) {
        Intent intent = new Intent(mContext, SecondLevelActivity.class);
        intent.putExtra(BUNDLE_TRACK_ID, trackId);
        mContext.startActivity(intent);
    }

    @Override
    public void navigateToLyricsScreen(int trackId) {
        Fragment fragment = mNavigatorManager.getFragmentByTag(LyricsFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = LyricsFragment.newInstance(trackId);
        }
        mNavigatorManager.navigateWithStack(fragment);
    }
}