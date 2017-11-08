package com.example.joseramos.lyricsappjlrf.presentation.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.FragmentAboutBinding
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment


class AboutFragment : BaseFragment() {

    lateinit var binding: FragmentAboutBinding
    companion object {

        @JvmStatic
        fun newInstance() : Fragment {
            return AboutFragment()

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about,container, false );
        return binding.root
    }


}