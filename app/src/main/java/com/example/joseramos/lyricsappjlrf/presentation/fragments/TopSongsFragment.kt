package com.example.joseramos.lyricsappjlrf.presentation.fragments


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.joseramos.lyricsappjlrf.R
import com.example.joseramos.lyricsappjlrf.databinding.FragmentTopSongsBinding
import com.example.joseramos.lyricsappjlrf.domain.models.TrackModel
import com.example.joseramos.lyricsappjlrf.presentation.adapters.TracksAdapter
import com.example.joseramos.lyricsappjlrf.presentation.fragments.base.BaseFragment
import com.example.joseramos.lyricsappjlrf.presentation.navigation.OnBackPressListener
import com.example.joseramos.lyricsappjlrf.presentation.presenters.TopSongsPresenter
import com.example.joseramos.lyricsappjlrf.presentation.views.TopSongsView

import javax.inject.Inject

class TopSongsFragment : BaseFragment(), TopSongsView, OnBackPressListener {

    @Inject
    lateinit var presenter: TopSongsPresenter
    lateinit var binding: FragmentTopSongsBinding
    private var adapter: TracksAdapter? = null

    private val trackModelTracksAdapterListener = TracksAdapter.TracksAdapterListener<TrackModel> { view, position, (trackId) -> navigator.navigateToSecondLevelActivity(trackId) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_top_songs, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentComponent!!.inject(this)
        presenter.setView(this)
        presenter.loadTopSongs()
        binding.rvTopSongs.layoutManager = LinearLayoutManager(activity)
        binding.rvTopSongs.adapter = adapter
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onSuccessSongsLoaded(trackModels: List<TrackModel>) {
        adapter = TracksAdapter(trackModels)
        adapter!!.setTrackAdapterListener(trackModelTracksAdapterListener)
        binding.rvTopSongs.adapter = adapter
    }

    override fun showError(throwable: Throwable) {
        throwable.printStackTrace()
        Snackbar.make(binding.root, throwable.message as CharSequence, Snackbar.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        activity.finish()
    }

    companion object {

        fun newInstance(): TopSongsFragment {
            return TopSongsFragment()
        }
    }
}
