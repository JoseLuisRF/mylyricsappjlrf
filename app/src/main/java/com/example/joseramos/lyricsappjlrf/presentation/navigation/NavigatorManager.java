package com.example.joseramos.lyricsappjlrf.presentation.navigation;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.joseramos.lyricsappjlrf.R;

import javax.inject.Inject;

public class NavigatorManager {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    /**********************************************************************************************
     * Constructors
     **********************************************************************************************/

    @Inject
    public NavigatorManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    /**********************************************************************************************
     * Public Methods
     **********************************************************************************************/

    public void clearBackStack() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        int entryCount = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < entryCount; i++) {
            String name = mFragmentManager.getBackStackEntryAt(i).getName();
            fragmentTransaction.remove(mFragmentManager.findFragmentByTag(name));
        }
        fragmentTransaction.commit();
        mCurrentFragment = null;
    }

    /**
     * Displays a Fragment with no stack
     *
     * @param fragment Fragment
     */
    public void navigateWithNoStack(Fragment fragment) {
        if (mCurrentFragment == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
        mCurrentFragment = fragment;
    }

    /**
     * Displays a Fragment and keeps the Stack
     *
     * @param fragment Fragment
     */
    public void navigateWithStack(Fragment fragment) {

        if (mCurrentFragment == null) {
            mFragmentManager.beginTransaction()
                    .add(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        } else {
            mFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }

        mCurrentFragment = fragment;
    }

    public void showDialog(DialogFragment dialogFragment) {
        mCurrentFragment = dialogFragment;
        dialogFragment.show(mFragmentManager, dialogFragment.getTag());
    }


    /**
     * Returns the current visible Fragment
     *
     * @return The current visible Fragment.
     */
    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    public Fragment getFragmentByTag(String tag) {
        return mFragmentManager.findFragmentByTag(tag);
    }

    public void popStack() {
        mFragmentManager.popBackStackImmediate();
        if (hasFragments()) {
            String fragmentTag = mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getName();
            mCurrentFragment = mFragmentManager.findFragmentByTag(fragmentTag);
        } else {
            mCurrentFragment = null;
        }
    }

    private boolean hasFragments() {
        return mFragmentManager.getBackStackEntryCount() > 0;
    }


}
