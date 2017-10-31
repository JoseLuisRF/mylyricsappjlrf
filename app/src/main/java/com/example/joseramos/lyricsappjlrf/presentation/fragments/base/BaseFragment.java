package com.example.joseramos.lyricsappjlrf.presentation.fragments.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.joseramos.lyricsappjlrf.MyLyricsApplication;
import com.example.joseramos.lyricsappjlrf.di.ComponentFactory;
import com.example.joseramos.lyricsappjlrf.di.components.FragmentComponent;

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentComponent = ComponentFactory.createFragmentComponent(MyLyricsApplication.getComponent(this.getActivity().getApplication()));
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }
}
