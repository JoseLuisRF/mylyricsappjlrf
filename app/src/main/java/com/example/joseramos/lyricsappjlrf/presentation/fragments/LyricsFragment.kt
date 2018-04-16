package com.example.joseramos.lyricsappjlrf.presentation.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.FragmentLyricsScreenBinding
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener
import com.example.joseramos.lyricsappjlrf.presentation.viewmodel.LyricsViewModel
import javax.inject.Inject

const val BUNDLE_TRACK_ID = "track_id"

class LyricsFragment : OnBackPressListener, BaseFragment() {

    lateinit var binding: FragmentLyricsScreenBinding
    private var trackId: Int = 0
    lateinit var viewModel: LyricsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
//        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentLyricsScreenBinding>(inflater, R.layout.fragment_lyrics_screen, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LyricsViewModel::class.java)

        if (arguments.getInt(BUNDLE_TRACK_ID, 0) != 0) {
            viewModel.setTrackId(arguments.getInt(BUNDLE_TRACK_ID, 0))
        }

        viewModel.getLyrics().observe(this, Observer { lyricsModel ->
            if (lyricsModel != null && lyricsModel.error) {
                binding.tvErrorMessage.text = lyricsModel.message
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.tvLyrics.visibility = View.GONE
            } else {
                binding.tvErrorMessage.visibility = View.GONE
                binding.tvLyrics.visibility = View.VISIBLE
                binding.tvLyrics.text = lyricsModel?.songLyrics
            }
        })
    }

    override fun onBackPressed() {
        activity.finish()
    }


    companion object {

        @JvmStatic
        fun newInstance(trackId: Int): Fragment {
            val fragment = LyricsFragment()
            val arguments = Bundle()
            arguments.putInt(BUNDLE_TRACK_ID, trackId)
            fragment.arguments = arguments
            return fragment
        }
    }

}